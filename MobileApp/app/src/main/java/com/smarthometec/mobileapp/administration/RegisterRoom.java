package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.models.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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
    private void saveRoom(Room room) {
        System.out.println("1 "+room.getName());
        System.out.println("2 "+ room.getUserEmail());
        JSONObject jsonObject = new JSONObject();
        final String mRequestBody = jsonObject.toString();
        try {
            jsonObject.put("name",room.getName());
            jsonObject.put("userEmail",room.getUserEmail());
            System.out.println(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dbHelper.insertRoom(this, room.getName(), room.getUserEmail());
        String postURL = DatabaseHelper.SERVER_URL + "api/Room";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String answer = response;
                if (answer.equals("Inserted")) {
                    Intent manageDevices = new Intent(RegisterRoom.this, ManageDevices.class);
                    RegisterRoom.this.startActivity(manageDevices);
                    RegisterRoom.this.finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
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
        stringRequest.setRetryPolicy(new RetryPolicy() { @Override public int getCurrentTimeout() { return 50000; } @Override public int getCurrentRetryCount() { return 50000; } @Override public void retry(VolleyError error) throws VolleyError { } });
        requestQueue.add(stringRequest);
    }
}
