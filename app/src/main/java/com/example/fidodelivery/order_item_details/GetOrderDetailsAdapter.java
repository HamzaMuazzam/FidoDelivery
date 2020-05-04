package com.example.fidodelivery.order_item_details;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetOrderDetailsAdapter extends RecyclerView.Adapter<GetOrderDetailsViewHolder> {
    private Context context;
    private List<GetOrderDetail> list;

    public GetOrderDetailsAdapter(Context context, List<GetOrderDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetOrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetOrderDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item_details_itemlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetOrderDetailsViewHolder holder, int position) {
        final GetOrderDetail model = list.get(position);
        if (!model.getChoose_fillings().equalsIgnoreCase("")) {
            holder.tv_itemName.setText(model.getIName());
            holder.tv_itemQuantity.setText(model.getIQuantity() + "x");
            holder.tv_priceTotal.setText(model.getIPrice() + "$");
            holder.dot.setVisibility(View.VISIBLE);
        } else {
            holder.tv_itemName.setText(model.getIName());
            holder.tv_itemQuantity.setText(model.getIQuantity() + "x");
            holder.tv_priceTotal.setText(model.getIPrice() + "$");

        }


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.getChoose_fillings().equalsIgnoreCase("")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
                    //convert string back to array
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetCFbyID>>() {
                    }.getType();
                    ChooseFillingDialogeAdapter chooseFillingDialogeAdapter;
                    ArrayList<GetCFbyID> finalOutputString = gson.fromJson(model.getChoose_fillings(), type);
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    assert inflater != null;
                    View dialogView = inflater.inflate(R.layout.dialoge_layout, null);
                    dialogBuilder.setView(dialogView);
                    dialogView.getRootView().setBackground(new ColorDrawable(Color.TRANSPARENT));
                    RecyclerView rv_dialode_choosefilling = (RecyclerView) dialogView.findViewById(R.id.rv_dialode_choosefilling);
                    chooseFillingDialogeAdapter = new ChooseFillingDialogeAdapter(finalOutputString, context);
                    rv_dialode_choosefilling.setLayoutManager(new LinearLayoutManager(context));
                    rv_dialode_choosefilling.setAdapter(chooseFillingDialogeAdapter);
                    chooseFillingDialogeAdapter.notifyDataSetChanged();
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(context, "No Extras", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
