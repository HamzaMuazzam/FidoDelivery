package com.example.fidodelivery.order_item_details;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;

public class ChooseFillingDialogeViewHolder extends RecyclerView.ViewHolder {
TextView tv_choosefillingName,tv_nestedChooseFillingPrice;
    public ChooseFillingDialogeViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_nestedChooseFillingPrice=itemView.findViewById(R.id.tv_nestedChooseFillingPrice);
        tv_choosefillingName=itemView.findViewById(R.id.tv_choosefillingName);
    }
}
