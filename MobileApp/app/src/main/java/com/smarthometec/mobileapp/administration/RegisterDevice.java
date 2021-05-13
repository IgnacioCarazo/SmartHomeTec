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
import android.widget.Toast;

import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.models.Device;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
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
                    Device device = new Device(valueSerialDevice,descriptionDevice,consumeDevice,brandDevice,typeDevice,roomDevice,dateFormat.format(date));
                    saveDevice(device);
                }
            }
        });
    }
    //Guarda el device y deviceType Insertado
    private void saveDevice(Device addingDevice){
        dbHelper.insertDevice(this,addingDevice.getSerialNumber(),addingDevice.getDescription(),addingDevice.getConsumption(),addingDevice.getBrand(), addingDevice.getType(), addingDevice.getRoom(),addingDevice.getDate_created());
        /*
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseHelper.SERVER_URL, new Response.Listener<String>() {
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
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

         */
        Intent manageDevices = new Intent(RegisterDevice.this, ManageDevices.class);
        RegisterDevice.this.startActivity(manageDevices);
        RegisterDevice.this.finish();
    }
    //Carga la data para elegir de cuartos en registro de dispositivo
    private void loadRoomData() {
        rooms = new ArrayList<>();
        Cursor cursor = dbHelper.readAllData("TABLE_ROOM");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                rooms.add(cursor.getString(1));
            }
        }else{
            Toast.makeText(this, "No data at room table, ", Toast.LENGTH_SHORT).show();
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