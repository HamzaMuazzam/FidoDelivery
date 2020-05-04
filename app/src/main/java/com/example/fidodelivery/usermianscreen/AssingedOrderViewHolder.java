package com.example.fidodelivery.usermianscreen;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;

public class AssingedOrderViewHolder extends RecyclerView.ViewHolder {
    TextView tv_total, tv_orderID, tv_userName,tv_datenadtime, tv_quantity, tv_address, tv_instrcutions;
    Button btn_gotomap;
    View view;

    public AssingedOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        tv_userName=itemView.findViewById(R.id.tv_userName);
        tv_total=itemView.findViewById(R.id.tv_total);
        tv_orderID=itemView.findViewById(R.id.tv_orderID);
        tv_datenadtime=itemView.findViewById(R.id.tv_datenadtime);
        tv_quantity=itemView.findViewById(R.id.tv_quantity);
        tv_address=itemView.findViewById(R.id.tv_address);
        tv_instrcutions=itemView.findViewById(R.id.tv_instrcutions);
        btn_gotomap=itemView.findViewById(R.id.btn_gotomap);
    }
}
