package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.models.Room;
import static com.smarthometec.mobileapp.Login.dbHelper;
/**
 * @class RegisterRoom
 * Define los diferentes aposentos en la casa donde se ubicarán los dispositivos adquiridos por el usuario.
 * @author JosephJimenez
 */
public class RegisterRoom extends AppCompatActivity {
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_room);
        email = getIntent().getStringExtra("email");

        TextView roomNameText = findViewById(R.id.roomName);
        Button roomRegisterBut = findViewById(R.id.addRoomBottom);
        roomRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameRoom = roomNameText.getText().toString();
                Room newRoom = new Room(nameRoom,email);
                registerRoom(newRoom);
            }
        });
    }
    //Añade el cuarto a la base de datos
    public void registerRoom(Room newRoom){
        dbHelper.insertRoom(this,newRoom.getName(),newRoom.getUserEmail());
        Intent manageDevices = new Intent(RegisterRoom.this, ManageDevices.class);
        RegisterRoom.this.startActivity(manageDevices);
        RegisterRoom.this.finish();
    }
}
