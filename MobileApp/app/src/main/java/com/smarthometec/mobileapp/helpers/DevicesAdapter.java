package com.smarthometec.mobileapp.helpers;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import com.smarthometec.mobileapp.R;
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
    private final ArrayList<String> nameDevice;
    private final ArrayList<String> consume;
    private final ArrayList<String> timeLeft;
    public static int time ;
    public DevicesAdapter(Activity activity, Context context, ArrayList<Integer>  serialNumber, ArrayList<String> deviceType, ArrayList<String> nameDevice, ArrayList<String> consume, ArrayList<String> timeLeft){
        this.activity = activity;
        this.context = context;
        this.serialNumber = serialNumber;
        this.deviceType = deviceType;
        this.nameDevice = nameDevice;
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
        holder.tvNameDevice.setText(String.valueOf(nameDevice.get(position)));
        holder.tvConsume.setText(String.valueOf(consume.get(position)));
        holder.tvTimeLeft.setText(String.valueOf(timeLeft.get(position)));
        turnOnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        dbHelper.addControl(context,serialNumber,objSDF.format(objDate),time);
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
        private final TextView tvNameDevice;
        private final TextView tvConsume;
        private final TextView tvTimeLeft;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSerialNumber = itemView.findViewById(R.id.tvserialNumber);
            tvTypeDevice = itemView.findViewById(R.id.tvTypeDevice);
            tvNameDevice = itemView.findViewById(R.id.tvNameDevice);
            tvConsume = itemView.findViewById(R.id.tvConsume);
            tvTimeLeft = itemView.findViewById(R.id.tvTimeLeft);
            turnOnData = itemView.findViewById(R.id.TurnOnSwitch);
        }
    }

}