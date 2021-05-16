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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.helpers.HttpsTrustManager;
import com.smarthometec.mobileapp.models.Device;
import com.smarthometec.mobileapp.models.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.smarthometec.mobileapp.Login.dbHelper;
import static com.smarthometec.mobileapp.administration.ManageDevices.email;
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
        deviceSerialText = findViewById(R.id.deviceSerial);
        deviceBrandText = findViewById(R.id.deviceBrand);
        deviceTypeText = findViewById(R.id.deviceType);
        deviceConsumeText = findViewById(R.id.deviceConsume);
        deviceDescriptionText = findViewById(R.id.deviceDescription);
        deviceRoomText = findViewById(R.id.spinnerRoom);
        Button deviceRegisterBut = findViewById(R.id.addDeviceBottom);
        deviceRoomText.setOnItemSelectedListener(this);
        loadRoomData();
        deviceRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serialDevice = deviceSerialText.getText().toString();
                int valueSerialDevice = 0;
                if (!"".equals(serialDevice)) {
                    valueSerialDevice = Integer.parseInt(serialDevice);
                }
                String brandDevice = deviceBrandText.getText().toString();
                String typeDevice = deviceTypeText.getText().toString();
                String consumeDevice = deviceConsumeText.getText().toString();
                String descriptionDevice = deviceDescriptionText.getText().toString();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                if (roomDevice != null) {
                    Device device = new Device(valueSerialDevice, descriptionDevice, consumeDevice, brandDevice, typeDevice, roomDevice, dateFormat.format(date), email);
                    saveDevice(device);
                }
            }
        });
    }
    /**
     * Funcion que guarda el dispositivo en la base de datos local y REST
     *
     * @param addingDevice
     */
    private void saveDevice(Device addingDevice) {
        String postUrl = DatabaseHelper.SERVER_URL + "api/AppDevice";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("serialNumber", addingDevice.getSerialNumber());
            postData.put("description", addingDevice.getDescription());
            postData.put("consumption", addingDevice.getConsumption());
            postData.put("brand", addingDevice.getBrand());
            postData.put("type", addingDevice.getType());
            postData.put("room", addingDevice.getRoom());
            postData.put("createdDate", addingDevice.getDate_created());
            postData.put("emailOwner", addingDevice.getUserEmail());
            postData.put("active", addingDevice.isActive());

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
    //Carga los titulos de los cuartos en registro de dispositivos
    private void loadRoomData() {
        rooms = new ArrayList<>();
        Cursor cursor = dbHelper.readAllData("TABLE_ROOM");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                rooms.add(cursor.getString(0));
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