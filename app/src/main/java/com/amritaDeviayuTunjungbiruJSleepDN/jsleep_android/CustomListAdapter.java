package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import androidx.annotation.*;

import java.lang.*;
import java.util.ArrayList;

import android.view.*;
import android.content.*;
import android.widget.*;

/**
 * This class is an adapter used to display a list of rooms in a ListView.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class CustomListAdapter extends ArrayAdapter<Room> {
    /**
     * Constructor for OrderListAdapter.
     *
     * @param context The context in which the adapter is being used.
     * @param rooms The list of rooms to be displayed.
     */
    public CustomListAdapter(@NonNull Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }

    /**
     * This method is used to create the view for an item in the list of rooms.
     *
     * @param position The position of the item in the list.
     * @param convertView The view to be converted.
     * @param parent The parent view group.
     * @return The view for the item at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View currentItemView = convertView;
        if(currentItemView == null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_layout,parent,false);
        }

        Room currentRoom = getItem(position);

        TextView name = currentItemView.findViewById(R.id.room_name);
        name.setText(currentRoom.name);

        TextView address = currentItemView.findViewById(R.id.room_address);
        address.setText(currentRoom.address);

        TextView price = currentItemView.findViewById(R.id.room_price);
        price.setText("Rp. " + String.valueOf(currentRoom.price.price));

        return currentItemView;
    }
}
