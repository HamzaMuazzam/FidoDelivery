package com.example.fidodelivery.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fidodelivery.R;
import com.example.fidodelivery.login_details.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private TextView tv_accountname, tv_acountphone, tv_accountAddress, tv_signOut;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        initviews();
        logOut();
        return view;
    }

    private void logOut() {
        tv_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("UUID", null);
                editor.putString("dId", null);
                editor.putString("dAddress", null);
                editor.putString("dName", null);
                editor.putBoolean("isAutoLogin",false);
                editor.apply();


                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void initviews() {
        tv_signOut = view.findViewById(R.id.tv_signOut);
        tv_accountname = view.findViewById(R.id.tv_accountname);
        tv_acountphone = view.findViewById(R.id.tv_acountphone);
        tv_accountAddress = view.findViewById(R.id.tv_accountAddress);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("ForThisApp",Context.MODE_PRIVATE);
        String dAddress = sharedPreferences.getString("dAddress", "");
        String dName = sharedPreferences.getString("dName", "");
        String uuid = sharedPreferences.getString("UUID", "");
        tv_accountname.setText(dName);
        tv_accountAddress.setText(dAddress);
        tv_acountphone.setText(uuid);
    }
}
