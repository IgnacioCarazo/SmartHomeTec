package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.models.Device;
import com.smarthometec.mobileapp.models.DeviceType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static com.smarthometec.mobileapp.Login.dbHelper;
/**
 * @class RegisterDevice
 * Permite agregar un dispositivo de forma manual al usuario
 * @author JosephJimenez
 */
public class RegisterDevice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText deviceNameText;
    private EditText deviceSerialText;
    private EditText deviceBrandText;
    private EditText deviceTypeText;
    private EditText deviceConsumeText;
    private EditText deviceDescriptionText;
    private Spinner deviceRoomText;
    private String roomDevice = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        deviceNameText =  findViewById(R.id.deviceName);
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
                String nameDevice = deviceNameText.getText().toString();
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
                    DeviceType deviceType = new DeviceType(typeDevice,descriptionDevice,dateFormat.format(date));
                    Device device = new Device(nameDevice,valueSerialDevice,consumeDevice,brandDevice,"defAssociated",typeDevice,"defOwner", 0,0,roomDevice,dateFormat.format(date));
                    saveDevice(device,deviceType);
                }
            }
        });
    }
    //Guarda el device y deviceType Insertado
    private void saveDevice(Device addingDevice, DeviceType deviceType){
        dbHelper.addDevice(this,addingDevice.getSerialNumber(),addingDevice.getConsumption(),addingDevice.getBrand(), addingDevice.getType(), addingDevice.getRoom(), addingDevice.getRoom(),"Joseph",addingDevice.getDate_created());
        dbHelper.addDeviceType(this,deviceType.getName(),deviceType.getDescription(),deviceType.getWarrantyTime());
        Intent manageDevices = new Intent(RegisterDevice.this, ManageDevices.class);
        RegisterDevice.this.startActivity(manageDevices);
        RegisterDevice.this.finish();
    }
    //Carga la data para elegir de cuartos en registro de dispositivo
    private void loadRoomData() {
        List<String> rooms = dbHelper.getRooms();
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