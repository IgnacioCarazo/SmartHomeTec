package com.smarthometec.mobileapp.helpers;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthometec.mobileapp.R;
import com.smarthometec.mobileapp.administration.ManageDevices;
import com.smarthometec.mobileapp.administration.RegisterRoom;
import com.smarthometec.mobileapp.database.DatabaseHelper;
import com.smarthometec.mobileapp.models.Control;
import com.smarthometec.mobileapp.models.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.smarthometec.mobileapp.Login.dbHelper;
/**
 * @class DevicesAdapter
 * Clase que permite llenar las caracteristicas de los dispositivos a mostrar
 * @author JosephJimenez
 */
public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.MyViewHolder> {
    private Activity activity;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch turnOnData ;
    private Context context;
    private final ArrayList<Integer> serialNumber;
    private final ArrayList<String> deviceType;
    private final ArrayList<String> description;
    private final ArrayList<String> brand;
    private final ArrayList<String> consume;
    private final ArrayList<String> timeLeft;
    public static int time ;
    public DevicesAdapter(Activity activity, Context context, ArrayList<Integer>  serialNumber, ArrayList<String> description, ArrayList<String> deviceType, ArrayList<String> brand , ArrayList<String> consume, ArrayList<String> timeLeft){
        this.activity = activity;
        this.context = context;
        this.serialNumber = serialNumber;
        this.deviceType = deviceType;
        this.description = description;
        this.brand = brand;
        this.consume = consume;
        this.timeLeft = timeLeft;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_devices_adapter, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvSerialNumber.setText(String.valueOf(serialNumber.get(position)));
        holder.tvTypeDevice.setText(String.valueOf(deviceType.get(position)));
        holder.tvConsume.setText(String.valueOf(consume.get(position)));
        holder.tvTimeLeft.setText(String.valueOf(timeLeft.get(position)));
        turnOnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Control control = new Control();
                Date objDate = new Date();
                Time timeThread = new Time();
                Thread nuevoh = new Thread();
                boolean switchState = turnOnData.isChecked();
                if (switchState) {
                    time=0;
                    timeThread.setTurnOn();
                    nuevoh= new Thread(timeThread);
                    nuevoh.start();
                }else{
                    timeThread.setTurnOff();
                    nuevoh.interrupt();
                    if(time>0){
                        String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
                        String serialText = String.valueOf(serialNumber.get(position));
                        int serialNumber = Integer.parseInt(serialText);
                        control.setTime(time);
                        control.setDate(objSDF.format(objDate));
                        control.setSerialNumber(serialNumber);
                        saveControl(control);
                    }
                }
            }
        });
    }
    @Override
    public int getItemCount() { return serialNumber.size(); }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSerialNumber;
        private final TextView tvTypeDevice;
        private final TextView tvConsume;
        private final TextView tvTimeLeft;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSerialNumber = itemView.findViewById(R.id.tvserialNumber);
            tvTypeDevice = itemView.findViewById(R.id.tvTypeDevice);
            tvConsume = itemView.findViewById(R.id.tvConsume);
            tvTimeLeft = itemView.findViewById(R.id.tvTimeLeft);
            turnOnData = itemView.findViewById(R.id.TurnOnSwitch);
        }
    }
    /**
     * Funcion que guarda el tiempo encendido de los dispositivos en local database y REST
     * @param control es el objeto con la informacion
     */
    private void saveControl(Control control) {
        JSONObject jsonObject = new JSONObject();
        final String mRequestBody = jsonObject.toString();
        try {
            jsonObject.put("time", control.getTime());
            jsonObject.put("date", control.getDate());
            jsonObject.put("serialNumber", control.getSerialNumber());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dbHelper.insertControl(context, control.getSerialNumber(), control.getDate(), control.getTime());
        String postURL = DatabaseHelper.SERVER_URL + "api/Control";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject postData = new JSONObject();
        try {
            postData.put("time", control.getTime());
            postData.put("date", control.getDate());
            postData.put("serialNumber", control.getSerialNumber());

        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postURL, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
}