package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.helpers.RoomAdapter;
import com.smarthometec.mobileapp.models.Control;
import com.smarthometec.mobileapp.models.Device;
import com.smarthometec.mobileapp.models.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.smarthometec.mobileapp.Login.dbHelper;
 /**
 * @class ManageDevices
 * Establece la ventana con las posibles opciones a acceder por parte del Usuario
 * @author JosephJimenez
 */
public class ManageDevices extends AppCompatActivity {
    private RoomAdapter roomAdapter;
    private RecyclerView recyclerView;
    private ArrayList<String> nameRoom;
    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_devices);
        email = getIntent().getStringExtra("email");
        recyclerView = findViewById(R.id.recyclerView);
        TextView addRoom = findViewById(R.id.addRoom);
        TextView addDevice = findViewById(R.id.addDevice);
        Switch syncData = findViewById(R.id.syncSwitch);
        syncData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean switchState = syncData.isChecked();
                if (switchState) {
                    Cursor cursor = dbHelper.deleteAllData("TABLE_ROOM");
                    cursor.close();
                    Cursor cursor2 = dbHelper.deleteAllData("TABLE_DEVICE");
                    cursor2.close();
                    Cursor cursor3 = dbHelper.deleteAllData("TABLE_CONTROL");
                    cursor3.close();
                    updateDatabaseRoom();
                    updateDatabaseDevice();
                    updateDatabaseControl();
                }else{
                    Intent manage = new Intent(ManageDevices.this, ManageDevices.class);
                    ManageDevices.this.startActivity(manage);
                    ManageDevices.this.finish();
                }
            }
        });
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTableControl();
                Intent registerRoom = new Intent(ManageDevices.this, RegisterRoom.class);
                ManageDevices.this.startActivity(registerRoom);
                ManageDevices.this.finish();
            }
        });
        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerDevice = new Intent(ManageDevices.this, RegisterDevice.class);
                ManageDevices.this.startActivity(registerDevice);
                ManageDevices.this.finish();
            }
        });
        nameRoom = new ArrayList<>();
        storeDataRoom();
        roomAdapter = new RoomAdapter(ManageDevices.this,this,nameRoom);
        recyclerView.setAdapter(roomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ManageDevices.this));
    }
    //Llena la lista que mostrara los titulos de los cuartos y lo pasa al adapter
    private void storeDataRoom(){
        Cursor cursor = dbHelper.readAllData("TABLE_ROOM");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                nameRoom.add(cursor.getString(0));
            }
        }else{
            Toast.makeText(this, "No data at room table, ", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
     //Llena las listas que mostraran los titulos de los devices
     private void getTableControl(){
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> serialNumber = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
         Cursor cursor = dbHelper.readAllData("TABLE_CONTROL");
         if(cursor.getCount() != 0){
             while(cursor.moveToNext()){
                 id.add(cursor.getString(0));
                 serialNumber.add(cursor.getString(1));
                 date.add(cursor.getString(2));
                 time.add(cursor.getString(3));
             }
         }else{
             Toast.makeText(this, "No data at room table, ", Toast.LENGTH_SHORT).show();
         }
         cursor.close();
     }
     //Funcion que Actualiza la base de datos interna DEVICE
     private void updateDatabaseDevice(){
         String getURL = DatabaseHelper.SERVER_URL + "api/Device";
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         JsonArrayRequest arrayReq = new JsonArrayRequest(getURL,
                 new Response.Listener<JSONArray>() {
                     @Override
                     public void onResponse(JSONArray response) {
                         try {
                             JSONArray allDevices = response;
                             for (int i = 0; i <allDevices.length(); i++) {
                                 JSONObject device = allDevices.getJSONObject(i);
                                 int device_SerialNumber = device.getInt("serialNumber");
                                 String device_Description = device.getString("description");
                                 String device_consumption = device.getString("consumption");
                                 String device_brand = device.getString("brand");
                                 String device_type = device.getString("type");
                                 String device_room= device.getString("room_name");
                                 String device_createdDate = device.getString("createdDate");
                                 Device addDevice = new Device();
                                 addDevice.setSerialNumber(device_SerialNumber);
                                 addDevice.setDescription(device_Description);
                                 addDevice.setConsumption(device_consumption);
                                 addDevice.setBrand(device_brand);
                                 addDevice.setType(device_type);
                                 addDevice.setRoom(device_room);
                                 addDevice.setDate_created(device_createdDate);
                                 addDevice.setUserEmail(email);
                                 dbHelper.insertDevice(ManageDevices.this,addDevice.getSerialNumber(),addDevice.getDescription(),addDevice.getConsumption(),addDevice.getBrand(),addDevice.getType(),addDevice.getDate_created(),addDevice.getRoom(),addDevice.getUserEmail(),addDevice.isActive());
                             }
                         } catch (JSONException e) {
                             System.out.println("JSON ERROR getting Device info"+e);
                         }
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         arrayReq.setRetryPolicy(new RetryPolicy() { @Override public int getCurrentTimeout() { return 50000; } @Override public int getCurrentRetryCount() { return 50000; } @Override public void retry(VolleyError error) throws VolleyError { } });
         requestQueue.add(arrayReq);
         requestQueue.getCache().clear();
     }
     //Funcion que Actualiza la base de datos interna ROOM
     private void updateDatabaseRoom(){
         String getURL = DatabaseHelper.SERVER_URL + "api/Room";
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         JsonArrayRequest arrayReq = new JsonArrayRequest( getURL,
                 new Response.Listener<JSONArray>() {
                     @Override
                     public void onResponse(JSONArray response) {
                         System.out.println(response.toString());
                         try {
                             JSONArray allRooms = response;
                             for (int i = 0; i <allRooms.length(); i++) {
                                 JSONObject room = allRooms.getJSONObject(i);
                                 String room_Name = room.getString("name");
                                 String room_UserEmail = room.getString("userEmail");
                                 Room addRoom = new Room(room_Name,room_UserEmail);
                                 dbHelper.insertRoom(ManageDevices.this,addRoom.getName(),addRoom.getUserEmail());
                             }
                         } catch (JSONException e) {
                             System.out.println("JSON ERROR getting Room info"+e);
                         }
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         arrayReq.setRetryPolicy(new RetryPolicy() { @Override public int getCurrentTimeout() { return 50000; } @Override public int getCurrentRetryCount() { return 50000; } @Override public void retry(VolleyError error) throws VolleyError { } });
         requestQueue.add(arrayReq);
         requestQueue.getCache().clear();
     }
     //Funcion que Actualiza la base de datos interna CONTROL
     private void updateDatabaseControl(){
         String getURL = DatabaseHelper.SERVER_URL + "api/Control";
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         JsonArrayRequest arrayReq = new JsonArrayRequest( getURL,
                 new Response.Listener<JSONArray>() {
                     @Override
                     public void onResponse(JSONArray response) {
                         try {
                             JSONArray allRegister = response;
                             for (int i = 0; i <allRegister.length(); i++) {
                                 JSONObject device = allRegister.getJSONObject(i);
                                 int device_serial = device.getInt("serialNumber");
                                 String device_date = device.getString("date");
                                 int device_time = device.getInt("time");
                                 Control addControl = new Control();
                                 addControl.setSerialNumber(device_serial);
                                 addControl.setDate(device_date);
                                 addControl.setTime(device_time);
                                 dbHelper.insertControl(ManageDevices.this,addControl.getSerialNumber(),addControl.getDate(),addControl.getTime());
                             }
                         } catch (JSONException e) {
                             System.out.println("JSON ERROR getting Control info"+e);
                         }
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         arrayReq.setRetryPolicy(new RetryPolicy() { @Override public int getCurrentTimeout() { return 50000; } @Override public int getCurrentRetryCount() { return 50000; } @Override public void retry(VolleyError error) throws VolleyError { } });
         requestQueue.add(arrayReq);
         requestQueue.getCache().clear();
     }
}