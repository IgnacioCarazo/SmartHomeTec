package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.UserProfile;
/**
 * @class ManageDevices
 * Establece la ventana con las posibles opciones a acceder por parte del Usuario
 * @author JosephJimenez
 */
public class ManageDevices extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_devices);
        TextView addRoom = (TextView)findViewById(R.id.roomTitle);
        TextView addDevice = (TextView)findViewById(R.id.deviceTitle);
        Switch syncData = (Switch) findViewById(R.id.syncSwitch);
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
}