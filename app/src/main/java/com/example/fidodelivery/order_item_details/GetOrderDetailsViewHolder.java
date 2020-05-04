package com.example.fidodelivery.order_item_details;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;

public class GetOrderDetailsViewHolder extends RecyclerView.ViewHolder {
    TextView tv_itemQuantity,tv_priceTotal,tv_itemName;
    ImageView dot;
    View view;

    public GetOrderDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
    view=itemView;
        tv_itemName=itemView.findViewById(R.id.tv_itemName);
        tv_itemQuantity=itemView.findViewById(R.id.tv_itemQuantity);
        tv_priceTotal=itemView.findViewById(R.id.tv_priceTotal);
        dot=itemView.findViewById(R.id.dot);
    }
}
