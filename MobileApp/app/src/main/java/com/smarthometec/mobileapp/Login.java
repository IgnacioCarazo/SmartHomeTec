package com.smarthometec.mobileapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.helpers.HttpsTrustManager;
import org.json.JSONException;
import org.json.JSONObject;
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
        //updateDatabaseLogin();

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
        @SuppressLint("Recycle") Cursor row = db.rawQuery("SELECT email_user,password_user FROM TABLE_CLIENT WHERE email_user ='" + EmailUser + "' AND password_user='" + UserPassword + "'", null);
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DatabaseHelper.SERVER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //dbHelper.addClient();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}