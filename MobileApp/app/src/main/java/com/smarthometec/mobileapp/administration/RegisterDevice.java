package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.models.Device;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.smarthometec.mobileapp.Login.dbHelper;
/**
 * @class RegisterDevice
 * Permite agregar un dispositivo de forma manual al usuario
 * @author JosephJimenez
 */
public class RegisterDevice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText deviceSerialText;
    private EditText deviceBrandText;
    private EditText deviceTypeText;
    private EditText deviceConsumeText;
    private EditText deviceDescriptionText;
    private Spinner deviceRoomText;
    private String roomDevice = null;
    private ArrayList<String> rooms;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        email = getIntent().getStringExtra("email");
        deviceSerialText =  findViewById(R.id.deviceSerial);
        deviceBrandText = findViewById(R.id.deviceBrand);
        deviceTypeText =  findViewById(R.id.deviceType);
        deviceConsumeText =  findViewById(R.id.deviceConsume);
        deviceDescriptionText =  findViewById(R.id.deviceDescription);
        deviceRoomText =  findViewById(R.id.spinnerRoom);
        Button deviceRegisterBut = findViewById(R.id.addDeviceBottom);
        deviceRoomText.setOnItemSelectedListener(this);
        loadRoomData();
        deviceRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serialDevice = deviceSerialText.getText().toString();
                int valueSerialDevice=0;
                if (!"".equals(serialDevice)){
                    valueSerialDevice=Integer.parseInt(serialDevice);
                }
                String brandDevice = deviceBrandText.getText().toString();
                String typeDevice = deviceTypeText.getText().toString();
                String consumeDevice = deviceConsumeText.getText().toString();
                String descriptionDevice = deviceDescriptionText.getText().toString();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                if(roomDevice!=null){
                    Device device = new Device(valueSerialDevice,descriptionDevice,consumeDevice,brandDevice,typeDevice,roomDevice,dateFormat.format(date),email);
                    saveDevice(device);
                }
            }
        });
    }
    /**
     * Funcion que guarda el dispositivo en la base de datos local y REST
     * @param addingDevice
     */
    private void saveDevice(Device addingDevice){
        dbHelper.insertDevice(this,addingDevice.getSerialNumber(),addingDevice.getDescription(),addingDevice.getConsumption(),addingDevice.getBrand(), addingDevice.getType(),addingDevice.getDate_created(),addingDevice.getRoom(),addingDevice.getUserEmail(),addingDevice.isActive());
        String postURL = DatabaseHelper.SERVER_URL + "api/Device";
        JSONObject jsonObject = new JSONObject();
        final String mRequestBody = jsonObject.toString();
        try {
            jsonObject.put("serialNumber",addingDevice.getSerialNumber());
            jsonObject.put("econsumption",addingDevice.getConsumption());
            jsonObject.put("brand",addingDevice.getBrand());
            jsonObject.put("type",addingDevice.getType());
            jsonObject.put("room",addingDevice.getRoom());
            jsonObject.put("date_created",addingDevice.getDate_created());
            jsonObject.put("userEmail",addingDevice.getUserEmail());
            jsonObject.put("active",addingDevice.isActive());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String answer = jsonObject.getString("response");
                    if(answer.equals("OK")){
                        Intent manageDevices = new Intent(RegisterDevice.this, ManageDevices.class);
                        RegisterDevice.this.startActivity(manageDevices);
                        RegisterDevice.this.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
    //Carga los titulos de los cuartos en registro de dispositivos
    private void loadRoomData() {
        rooms = new ArrayList<>();
        Cursor cursor = dbHelper.readAllData("TABLE_ROOM");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                rooms.add(cursor.getString(1));
            }
        }
        cursor.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deviceRoomText.setAdapter(dataAdapter);
    }
    //Asigana el cuarto para el dispositivo
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        roomDevice= parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}