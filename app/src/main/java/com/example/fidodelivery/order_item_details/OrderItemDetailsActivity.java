package com.example.fidodelivery.order_item_details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.R;
import com.example.fidodelivery.network.RestApis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderItemDetailsActivity extends AppCompatActivity {
    private RecyclerView recycler_orderdetails;
    RestApis restApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item_details);
        String orderID = getIntent().getStringExtra("orderID");
        initviews();
        getOrderItemDetails(orderID);
    }

    private void getOrderItemDetails(String orderID) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        restApis.getOrderDetails(orderID).enqueue(new Callback<GetOrderDetailResponse>() {
            @Override
            public void onResponse(Call<GetOrderDetailResponse> call, Response<GetOrderDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();
                    if (response.body().getStatus()) {
                        GetOrderDetailsAdapter getOrderDetailsAdapter = new GetOrderDetailsAdapter(OrderItemDetailsActivity.this
                                , response.body().getGetOrderDetail());
                        recycler_orderdetails.setLayoutManager(new LinearLayoutManager(OrderItemDetailsActivity.this));
                        recycler_orderdetails.setAdapter(getOrderDetailsAdapter);
                        getOrderDetailsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(OrderItemDetailsActivity.this, "No item found", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<GetOrderDetailResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OrderItemDetailsActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initviews() {
        recycler_orderdetails = findViewById(R.id.recycler_orderdetails);
        restApis = RestApis.retrofit.create(RestApis.class);
    }
}
