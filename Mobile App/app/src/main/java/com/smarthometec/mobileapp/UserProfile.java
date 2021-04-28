package com.smarthometec.mobileapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.smarthometec.mobileapp.administration.ManageDevices;
/**
 * @class UserProfile
 * Se obtiene los datos perfil del Usuario
 * @author JosephJimenez
 */
public class UserProfile extends AppCompatActivity {
    private TextView tvUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        String userID = getIntent().getStringExtra("userID");
        TextView tvUserID = findViewById(R.id.profileUserName);
        tvUserID.setText(userID);
        tvUserEmail = findViewById(R.id.profileEmail);
        Button btnManage =  (Button)findViewById(R.id.manageDevbutton);
        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageDevices = new Intent(UserProfile.this, ManageDevices.class);
                UserProfile.this.startActivity(manageDevices);
                UserProfile.this.finish();
            }
        });
        getUserInformation();
    }
    private void getUserInformation(){
        tvUserEmail.setText("Se obtuvo X email");
    }
}