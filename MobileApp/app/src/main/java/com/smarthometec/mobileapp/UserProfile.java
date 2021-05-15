package com.smarthometec.mobileapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.smarthometec.mobileapp.administration.ManageDevices;
import com.smarthometec.mobileapp.models.Client;

import static com.smarthometec.mobileapp.Login.db;
import static com.smarthometec.mobileapp.Login.dbHelper;

/**
 * @class UserProfile
 * Se obtiene los datos perfil del Usuario
 * @author JosephJimenez
 */
public class UserProfile extends AppCompatActivity {
    public TextView tvUserName;
    public TextView tvFLastName;
    public TextView tvSLastName;
    public TextView tvUserEmail;
    public TextView tvContinent;
    public TextView tvCountry;
    public TextView tvAddress;
    public String email;
    public Client clientInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        email = getIntent().getStringExtra("email");

        tvUserName = findViewById(R.id.profileName);
        tvFLastName = findViewById(R.id.profileFLastName);
        tvSLastName = findViewById(R.id.profileSLastName);
        tvUserEmail = findViewById(R.id.profileEmail);
        tvContinent = findViewById(R.id.profileContinent);
        tvCountry = findViewById(R.id.profileCountry);
        tvAddress = findViewById(R.id.profileAddress);

        clientInfo = getUserInformation(email);

        tvUserName.setText(clientInfo.getName());
        tvFLastName.setText(clientInfo.getPrimaryLastName());
        tvSLastName.setText(clientInfo.getSecondaryName());
        tvUserEmail.setText(clientInfo.getEmail());
        tvContinent.setText(clientInfo.getContinent());
        tvCountry.setText(clientInfo.getCountry());
        tvAddress.setText(clientInfo.getDeliveryAddress());

        Button btnManage =  findViewById(R.id.manageDevbutton);
        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageDevices = new Intent(UserProfile.this, ManageDevices.class);
                manageDevices.putExtra("email",email);
                UserProfile.this.startActivity(manageDevices);
                UserProfile.this.finish();
                Cursor cursor = dbHelper.deleteAllData("TABLE_CLIENT");
                cursor.close();
            }
        });
    }
    /**
     * Se obtiene los datos perfil del Usuario consultando a la base de datos interna
     */
    private Client getUserInformation(String email){
        @SuppressLint("Recycle") Cursor row = db.rawQuery("select name,primaryLastName,secondaryLastName,continent,country,deliveryAddress from TABLE_CLIENT where userEmail = '"+ email+ "'", null);
        Client user = new Client();
        try {
            if(row.moveToFirst()){
                String name= row.getString(0);
                String primaryLastName= row.getString(1);
                String secondaryLastName= row.getString(2);
                String continent= row.getString(3);
                String country= row.getString(4);
                String deliveryAddress= row.getString(5);
                user.setName(name);
                user.setPrimaryLastName(primaryLastName);
                user.setSecondaryName(secondaryLastName);
                user.setEmail(email);
                user.setContinent(continent);
                user.setCountry(country);
                user.setDeliveryAddress(deliveryAddress);
            }else {
                Toast.makeText(this,"Error setting User Data" ,Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return user;
    }
}