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
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.helpers.RoomAdapter;
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
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_devices);
        email = getIntent().getStringExtra("email");

        recyclerView = findViewById(R.id.recyclerView);
        TextView addRoom = (TextView)findViewById(R.id.addRoom);
        TextView addDevice = (TextView)findViewById(R.id.addDevice);
        Switch syncData = (Switch) findViewById(R.id.syncSwitch);

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTableControl();
                Intent registerRoom = new Intent(ManageDevices.this, RegisterRoom.class);
                dbHelper.getReadableDatabase();

                registerRoom.putExtra("email",email);
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
        storeDataArray();
        roomAdapter = new RoomAdapter(ManageDevices.this,this,nameRoom);
        recyclerView.setAdapter(roomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ManageDevices.this));
    }
    //Llena la lista que mostrara los titulos de los cuartos y lo pasa al adapter
    private void storeDataArray(){
        Cursor cursor = dbHelper.readAllData("TABLE_ROOM");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                nameRoom.add(cursor.getString(1));
            }
        }else{
            Toast.makeText(this, "No data at room table, ", Toast.LENGTH_SHORT).show();
        }
    }
     //Llena las listas que mostraran los titulos de los devices
     private void getTableControl(){
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> serialNumber = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> time = new ArrayList<String>();
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
         System.out.println(id.toString());
         System.out.println(serialNumber.toString());
         System.out.println(date.toString());
         System.out.println(time.toString());
     }
}