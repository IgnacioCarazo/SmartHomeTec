package com.smarthometec.mobileapp.helpers;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smarthometec.mobileapp.R;
import java.util.ArrayList;
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList nameRoom;
    private ArrayList<ArrayList> serialNumber;
    private ArrayList<ArrayList> deviceType ;
    private ArrayList<ArrayList> description;
    private ArrayList<ArrayList> consume ;
    private ArrayList<ArrayList> timeLeft;
    private ArrayList<ArrayList> brand;
    public RoomAdapter(Activity activity, Context context, ArrayList nameRoom,ArrayList<ArrayList> serialNumber, ArrayList<ArrayList> description,ArrayList<ArrayList> deviceType, ArrayList<ArrayList> brand, ArrayList<ArrayList>consume, ArrayList<ArrayList> timeLeft) {
        this.activity = activity;
        this.context = context;
        this.nameRoom = nameRoom;
        this.serialNumber = serialNumber;
        this.deviceType = deviceType;
        this.brand = brand;
        this.description = description ;
        this.consume = consume;
        this.timeLeft = timeLeft ;
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
        private ArrayList<ArrayList> serialNumber_pivot = new ArrayList<ArrayList>();
        private ArrayList<ArrayList> deviceType_pivot = new ArrayList<ArrayList>();
        private ArrayList<ArrayList> description_pivot = new ArrayList<ArrayList>();
        private ArrayList<ArrayList> consume_pivot = new ArrayList<ArrayList>();
        private ArrayList<ArrayList> timeLeft_pivot = new ArrayList<ArrayList>();
        private ArrayList<ArrayList> brand_pivot = new ArrayList<ArrayList>();
        private TextView tvRoomName;
        public RecyclerView recyclerViewDevices;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            recyclerViewDevices = itemView.findViewById(R.id.recyclerViewDevices);
            for (int i = 0; i<serialNumber.size();i++){
                //System.out.println("1 " + serialNumber.get(i) );
                //System.out.println("2 " + deviceType.get(i));
                //System.out.println("3 " + description.get(i));
                //System.out.println("4 " + consume.get(i));
                //System.out.println("5 " + timeLeft.get(i));
                //System.out.println("6 " + brand.get(i));
                serialNumber_pivot.add(serialNumber.get(i));
                deviceType_pivot.add((deviceType.get(i)));
                description_pivot.add((description.get(i)));
                consume_pivot.add(( consume.get(i)));
                timeLeft_pivot.add((timeLeft.get(i)));
                brand_pivot.add((brand.get(i)));
            }
            DevicesAdapter devicesAdapter = new DevicesAdapter(activity, context, serialNumber_pivot, description_pivot , deviceType_pivot , brand_pivot, consume_pivot, timeLeft_pivot);
            recyclerViewDevices.setAdapter(devicesAdapter);
            recyclerViewDevices.setLayoutManager(new LinearLayoutManager(activity));
        }
    }
}