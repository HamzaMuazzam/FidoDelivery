package com.example.fidodelivery.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fidodelivery.InsertionResponse;
import com.example.fidodelivery.R;
import com.example.fidodelivery.network.RestApis;
import com.example.fidodelivery.services.MusicPlayerService;
import com.example.fidodelivery.usermianscreen.AssingedOrderAdapter;
import com.example.fidodelivery.usermianscreen.GetOrderAssigned;
import com.example.fidodelivery.usermianscreen.GetOrderAssignedResponse;
import com.example.fidodelivery.usermianscreen.UserMainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveriesFragment extends Fragment {
    View view;
    RestApis restApis;
    private TextView tv_ordercount;
    private AssingedOrderAdapter assingedOrderAdapter;
    private RecyclerView recyclerAssingedOrders;
    List<GetOrderAssigned> orderAssignedList;
    private MusicPlayerService musicPlayerService;
    private boolean mBound = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deliveries, container, false);

        initviews();
        getAssignedOrders(UserMainActivity.uuid);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
        String orderID = sharedPreferences.getString("orderID", "");
        if (!orderID.equalsIgnoreCase("")) {
//            Toast.makeText(this, "Service Run", Toast.LENGTH_SHORT).show();
            playmusic();
        }
        return view;
    }
    private void initviews() {
        // u can give any node reference of database here


        restApis = RestApis.retrofit.create(RestApis.class);
        tv_ordercount = view.findViewById(R.id.tv_ordercount);
        recyclerAssingedOrders = view.findViewById(R.id.recyclerAssingedOrders);

    }



    private void getAssignedOrders(String uuid) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        restApis.getAssignedOrders(uuid).enqueue(new Callback<GetOrderAssignedResponse>() {
            @Override
            public void onResponse(Call<GetOrderAssignedResponse> call, Response<GetOrderAssignedResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    Boolean status = response.body().getStatus();
                    progressDialog.dismiss();
                    if (status) {
                        orderAssignedList = response.body().getGetOrderAssigned();
                        setRecyclerValues(orderAssignedList);
                    } else {

                        try {
                            List<GetOrderAssigned> list = response.body().getGetOrderAssigned();
                            String message = list.get(0).getOhId();
                            Toast.makeText(getContext(), "Message : " + message, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOrderAssignedResponse> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void setRecyclerValues(List<GetOrderAssigned> getOrderAssigned) {
        int size = getOrderAssigned.size();
        tv_ordercount.setText("Total Orders Found : " + String.valueOf(size));
        assingedOrderAdapter = new AssingedOrderAdapter(getContext(), getOrderAssigned);
        recyclerAssingedOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAssingedOrders.setAdapter(assingedOrderAdapter);
        assingedOrderAdapter.notifyDataSetChanged();
        new ItemTouchHelper(itemTouchHelperSimpleCallback).attachToRecyclerView(recyclerAssingedOrders);

    }


    ItemTouchHelper.SimpleCallback itemTouchHelperSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

//            Toast.makeText(UserMainActivity.this, "On Moved Called", Toast.LENGTH_SHORT).show();
            return false;
        }

/*
        @Override
        public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
        float dX, float dY, int actionState, boolean isCurrentlyActive) {
            try {



                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;

                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 5;
                    viewHolder.itemView.setTranslationX(dX / 5);


                    Paint paint = new Paint();

                    paint.setColor(Color.parseColor("#F4511E"));
//                    RectF background = new RectF((float) itemView.getRight() + dX / 5, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());

                    RectF icon_dest = new RectF((float) (itemView.getRight() + dX /7), (float) itemView.getTop()+width, (float) itemView.getRight()+dX/20, (float) itemView.getBottom()-width);

                    c.drawBitmap(icon, null, icon_dest, paint);

                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }





                }
*/

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            showCustomDialog(getContext(), viewHolder, "Are you sure to want to mark orders as selivered?", "Order Delievered?", "Yes", "No");
//            if (integer > 0) {
//                Toast.makeText(CartActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
//                list.remove(viewHolder.getAdapterPosition());
//                adapter.notifyDataSetChanged();
//                list.clear();
//
//                tv_ttoal.setText("0");
//                tv_subtotal.setText("0");
//                tv_totalitemscart.setText("0");
//                tv_extras_cart.setText("0");
//
//                chooseFillingTotal = 0;
//                subtotal = 0;
//                totalItems = 0;
//
//                getFrom_DB();
//                getChooseFillingTotal();
//                getSetSubTotal();
//            }


        }
    };

    public void showCustomDialog(final Context context, final RecyclerView.ViewHolder viewHolder, String message, String title, String positive_button, String negative_button) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);

        builder.setPositiveButton(positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Processing...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                final SharedPreferences sharedPreferences = getContext().getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
                String uuid = sharedPreferences.getString("UUID", "");
                String orderID = sharedPreferences.getString("orderID", "");
                if (orderID.equalsIgnoreCase(orderAssignedList.get(viewHolder.getAdapterPosition()).getOhId())) {
                    restApis.markOrderAsCompleted(orderAssignedList.get(viewHolder.getAdapterPosition()).getOhId(), "1", uuid).enqueue(new Callback<InsertionResponse>() {
                        @Override
                        public void onResponse(Call<InsertionResponse> call, Response<InsertionResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                progressDialog.dismiss();
                                if (response.body().getStatus()) {
                                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("orderID", null);
                                    editor.apply();
                                    orderAssignedList.remove(viewHolder.getAdapterPosition());
                                    setRecyclerValues(orderAssignedList);
                                }
                            } else {
                                Toast.makeText(context, "Some Error ", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<InsertionResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {


                    progressDialog.dismiss();
                    setRecyclerValues(orderAssignedList);
                    Toast.makeText(context, "Please Complete old task or select job first", Toast.LENGTH_LONG).show();
                }


            }


        });
        builder.setNegativeButton(negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setRecyclerValues(orderAssignedList);

                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private ServiceConnection mServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicPlayerService.MyServiceBiner myServiceBiner =
                    (MusicPlayerService.MyServiceBiner) iBinder;

            musicPlayerService = myServiceBiner.getservice();
            mBound = true;
            Log.d("MyTag", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mBound) {
                getActivity().unbindService(mServiceCon);
                mBound = false;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), MusicPlayerService.class);
        getContext().bindService(intent, mServiceCon, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter("MUSIC"));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
        if (mBound) {
            getActivity().unbindService(mServiceCon);
            mBound = false;
        }
    }

    public void playmusic() {

//        if (mBound) {
//            if (musicPlayerService.isPlaying()) {
//                musicPlayerService.pause();
//            } else {
//                musicPlayerService.play();
        final Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        getActivity().startService(intent);
//            }
//
//        }

    }



    //i used for intent service
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String key = intent.getStringExtra("KEY");
//            Toast.makeText(UserMainActivity.this, "" + key, Toast.LENGTH_LONG).show();


            String music = intent.getStringExtra("KEY");
            if (music == "done") {
            }
        }
    };
}
