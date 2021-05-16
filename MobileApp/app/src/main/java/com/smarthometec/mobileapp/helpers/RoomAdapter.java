package com.smarthometec.mobileapp.helpers;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarthometec.mobileapp.R;
import java.util.ArrayList;

import static com.smarthometec.mobileapp.Login.dbHelper;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList nameRoom;
    private ArrayList<Integer> serialNumber;
    private ArrayList<String> deviceType ;
    private ArrayList<String> description;
    private ArrayList<String> consume ;
    private ArrayList<String> timeLeft;
    private ArrayList<String> brand;
    public RoomAdapter(Activity activity, Context context, ArrayList nameRoom) {
        this.activity = activity;
        this.context = context;
        this.nameRoom = nameRoom;
    }
    @NonNull
    @Override
    public RoomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_room_adapter, parent, false);
        return new RoomAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final RoomAdapter.MyViewHolder holder, final int position) {
        holder.tvRoomName.setText(String.valueOf(nameRoom.get(position)));
    }
    @Override
    public int getItemCount() {
        return nameRoom.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRoomName;
        public RecyclerView recyclerViewDevices;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            recyclerViewDevices = itemView.findViewById(R.id.recyclerViewDevices);
            serialNumber = new ArrayList<>();
            deviceType = new ArrayList<>() ;
            description = new ArrayList<>();
            consume = new ArrayList<>();
            timeLeft = new ArrayList<>();
            brand = new ArrayList<>();
            storeDataDevice();
            DevicesAdapter devicesAdapter = new DevicesAdapter(activity, context, serialNumber, description , deviceType , brand, consume, timeLeft);
            recyclerViewDevices.setAdapter(devicesAdapter);
            recyclerViewDevices.setLayoutManager(new LinearLayoutManager(activity));
        }
    }
    private void storeDataDevice(){
        Cursor cursor = dbHelper.readAllData("TABLE_DEVICE");
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                description.add(cursor.getString(1));
                consume.add(cursor.getString(2));
                brand.add(cursor.getString(3));
                deviceType.add(cursor.getString(4));
                timeLeft.add(cursor.getString(5));
                serialNumber.add(cursor.getInt(0));
            }
        }else{
            System.out.println("Error getting data of room");
        }
        cursor.close();
    }
}