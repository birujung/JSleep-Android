package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;

import android.content.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.*;

/**
 * This class is an adapter used to display a list of orders in a ListView.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class OrderListAdapter extends ArrayAdapter<Payment> {
    /**
     * Constructor for OrderListAdapter.
     *
     * @param context The context in which the adapter is being used.
     * @param order The list of orders to be displayed.
     */
    public OrderListAdapter(@NonNull Context context, ArrayList<Payment> order) {
        super(context, 0, order);
    }

    /**
     * This method is used to create the view for an item in the list of orders.
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_layout,parent,false);
        }

        Payment currentOrder = getItem(position);

        TextView date = currentItemView.findViewById(R.id.payment_date);
        TextView status = currentItemView.findViewById(R.id.payment_status);

        String dateText = currentOrder.to.toString() + " - " + currentOrder.from.toString();
        date.setText(dateText);

        String statusText = "Status: " + currentOrder.status;
        status.setText(statusText);

        return currentItemView;
    }
}
