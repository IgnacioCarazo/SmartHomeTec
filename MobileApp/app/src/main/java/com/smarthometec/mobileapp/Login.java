package com.smarthometec.mobileapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.helpers.HttpsTrustManager;
import com.smarthometec.mobileapp.models.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/**
 * @class Login
 * Clase obtiene valores de la vista login y envia los datos para su procesamiento
 * @author JosephJimenez
 */
public class Login extends AppCompatActivity {
    public static SQLiteDatabase db;
    public static DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText userText = findViewById(R.id.loginUserID);
        EditText passwordText = findViewById(R.id.loginPassword);
        dbHelper= new DatabaseHelper(this);
        db=dbHelper.getWritableDatabase();
        updateDatabaseLogin();
        Button btnLogin =  findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userText.getText().toString();
                String password = passwordText.getText().toString();
                getCredentials(user,password);

            }
        });
    }
    /**
     * Por medio de este metodo se consulta si el usuario y la contraseña son correctas si es asi se procede a abrir el Perfil del usuario.
     * @param EmailUser compara con user de la db
     * @param UserPassword ccmpara con el password de la db
     */
    private void getCredentials(String EmailUser,String UserPassword){
        @SuppressLint("Recycle") Cursor row = db.rawQuery("SELECT userEmail,password FROM TABLE_CLIENT WHERE userEmail ='" + EmailUser + "' AND password='" + UserPassword + "'", null);
        try {
            if(row.moveToFirst()){
                String userEmail= row.getString(0);
                String password= row.getString(1);
                if (EmailUser.equals(userEmail)&&UserPassword.equals(password)){
                    Toast.makeText(getApplicationContext(),"Login SuccessFull", Toast.LENGTH_LONG).show();
                    Intent userProfile = new Intent(Login.this, UserProfile.class);
                    userProfile.putExtra("email",EmailUser);
                    Login.this.startActivity(userProfile);
                    Login.this.finish();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Login UnSuccessFull", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private void updateDatabaseLogin(){
        String getURL = DatabaseHelper.SERVER_URL + "api/Client";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayReq = new JsonArrayRequest(Request.Method.GET, getURL,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    try {
                        JSONArray allCLients = response;
                            for (int i = 0; i <allCLients.length(); i++) {
                                JSONObject client = allCLients.getJSONObject(i);
                                String client_name = client.getString("name");
                                String client_FLastName = client.getString("primaryLastName");
                                String client_SLastName = client.getString("secondaryLastName");
                                String client_email = client.getString("email");
                                String client_password = client.getString("password");
                                String client_continent = client.getString("continent");
                                String client_country = client.getString("country");
                                String client_address = client.getJSONArray("deliveryAdresses").getString(0);
                                Client addClient = new Client();
                                addClient.setName(client_name);
                                addClient.setPrimaryLastName(client_FLastName);
                                addClient.setSecondaryName(client_SLastName);
                                addClient.setEmail(client_email);
                                addClient.setPassword(client_password);
                                addClient.setContinent(client_continent);
                                addClient.setCountry(client_country);
                                addClient.setDeliveryAddress(client_address);
                                dbHelper.insertClient(Login.this,addClient.getEmail(),addClient.getName(),addClient.getPrimaryLastName(),addClient.getSecondaryName(),addClient.getPassword(),addClient.getContinent(),addClient.getCountry(),addClient.getDeliveryAddress());
                            }
                        } catch (JSONException e) {
                            System.out.println("JSON ERROR getting Client info"+e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        arrayReq.setRetryPolicy(new RetryPolicy() { @Override public int getCurrentTimeout() { return 50000; } @Override public int getCurrentRetryCount() { return 50000; } @Override public void retry(VolleyError error) throws VolleyError { } });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(arrayReq);
        requestQueue.getCache().clear();
    }
}