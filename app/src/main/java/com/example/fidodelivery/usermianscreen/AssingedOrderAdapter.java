package com.example.fidodelivery.usermianscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;
import com.example.fidodelivery.maps.MapsActivity;
import com.example.fidodelivery.order_item_details.OrderItemDetailsActivity;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class AssingedOrderAdapter extends RecyclerView.Adapter<AssingedOrderViewHolder> {



    private Context context;
    private List<GetOrderAssigned> list;

    public AssingedOrderAdapter(Context context, List<GetOrderAssigned> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AssingedOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssingedOrderViewHolder(LayoutInflater.from(context).inflate(R.layout.assigend_orders_itemlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AssingedOrderViewHolder holder, int position) {
        final GetOrderAssigned model = list.get(position);
        holder.tv_userName.setText(model.getUsername());
        holder.tv_total.setText(model.getOhBill() + "$");
        holder.tv_orderID.setText(model.getOhId());
        holder.tv_datenadtime.setText(model.getOhDate() + " " + model.getOhTime());
        holder.tv_address.setText(model.getAddress());
        holder.tv_instrcutions.setText(model.getInstructions());
        SharedPreferences sharedPreferences=context.getSharedPreferences("ForThisApp",Context.MODE_PRIVATE);
        String orderId = sharedPreferences.getString("orderID", "");
        if (!orderId.equalsIgnoreCase(""))
        {
        if (model.getOhId().equalsIgnoreCase(orderId)){
        holder.view.setBackgroundColor(Color.BLUE);

        }

        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderItemDetailsActivity.class)

                        .putExtra("orderID", model.getOhId())
                );
            }
        });
        holder.btn_gotomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng latLng = getLocationFromAddress(context, model.getAddress());
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;

                context.startActivity(new Intent(context, MapsActivity.class)
                        .putExtra("latitude", latitude)
                        .putExtra("longitude", longitude)
                );

            }
        });

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCustomDialog(context, "Are you ready to start ridding?", "Start new job!!!", "Yes", "No",model.getOhId(),holder.view);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public void showCustomDialog(final Context context, String message, String title, String positive_button, String negative_button, final String orderID, final View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);

        builder.setPositiveButton(positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences=context.getSharedPreferences("ForThisApp",Context.MODE_PRIVATE);
                String orderId = sharedPreferences.getString("orderID", "");
                if (orderId.equalsIgnoreCase("")){
                    Toast.makeText(context, ""+orderId, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor  editor=sharedPreferences.edit();
                    editor.putString("orderID",orderID);
                    editor.apply();
                    view.setBackgroundColor(Color.BLUE);
                }else {
                    Toast.makeText(context, "Please complete remaining task first", Toast.LENGTH_SHORT).show();
                }



            }


        });
        builder.setNegativeButton(negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
