package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.core.widget.ListViewAutoScrollHelper;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomListAdapter extends ArrayAdapter<Room> {
    public CustomListAdapter(@NonNull Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }

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

        return currentItemView;
    }
}
