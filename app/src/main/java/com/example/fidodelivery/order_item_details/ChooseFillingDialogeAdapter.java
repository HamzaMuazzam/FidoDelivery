package com.example.fidodelivery.order_item_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;

import java.util.ArrayList;

public class ChooseFillingDialogeAdapter extends RecyclerView.Adapter<ChooseFillingDialogeViewHolder> {
    private ArrayList<GetCFbyID> list;
    private Context context;

    public ChooseFillingDialogeAdapter(ArrayList<GetCFbyID> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ChooseFillingDialogeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseFillingDialogeViewHolder(LayoutInflater.from(context).inflate(R.layout.choose_filling_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseFillingDialogeViewHolder holder, int position) {
        GetCFbyID model=list.get(position);
        holder.tv_choosefillingName.setText(model.getCfName());
        holder.tv_nestedChooseFillingPrice.setText(model.getCfPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
