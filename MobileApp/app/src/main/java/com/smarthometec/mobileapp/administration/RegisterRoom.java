package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.helpers.HttpsTrustManager;
import com.smarthometec.mobileapp.models.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.smarthometec.mobileapp.Login.dbHelper;
import static com.smarthometec.mobileapp.administration.ManageDevices.email;
/**
 * @class RegisterRoom
 * Define los diferentes aposentos en la casa donde se ubicarán los dispositivos adquiridos por el usuario.
 * @author JosephJimenez
 */
public class RegisterRoom extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_room);
        TextView roomNameText = findViewById(R.id.roomName);
        Button roomRegisterBut = findViewById(R.id.addRoomBottom);
        roomRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameRoom = roomNameText.getText().toString();
                Room newRoom = new Room(nameRoom, email);
                saveRoom(newRoom);
                Intent manageDevices = new Intent(RegisterRoom.this, ManageDevices.class);
                RegisterRoom.this.startActivity(manageDevices);
                RegisterRoom.this.finish();
            }
        });
    }
    /**
     * Metodo que guarda en localdatabase y en la base de datos REST
     * @param room es el cuarto por guardar
     */
    private void saveRoom(Room room){
        dbHelper.insertRoom(this, room.getName(), room.getUserEmail());
        String postUrl = DatabaseHelper.SERVER_URL + "api/Room";;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("name",room.getName());
            postData.put("userEmail",room.getUserEmail());
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
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
}
