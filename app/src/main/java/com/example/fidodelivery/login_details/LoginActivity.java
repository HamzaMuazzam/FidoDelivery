package com.example.fidodelivery.login_details;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fidodelivery.R;
import com.example.fidodelivery.network.RestApis;
import com.example.fidodelivery.signup.SignUpActivity;
import com.example.fidodelivery.usermianscreen.UserMainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText et_uuidLogin, et_loginPassword;
    private RestApis restApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initviews();

    }

    private void initviews() {
        restApis = RestApis.retrofit.create(RestApis.class);
        et_loginPassword = findViewById(R.id.et_loginPassword);
        et_uuidLogin = findViewById(R.id.et_uuidLogin);
    }

    public void loginUser(View view) {
        final String uuid = et_uuidLogin.getText().toString().trim();
        String password = et_loginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(uuid)) {
            et_uuidLogin.setError("can't be empty");
        } else if (TextUtils.isEmpty(password)) {
            et_uuidLogin.setError(null);
            et_loginPassword.setError("can't be empty");
        } else {
            et_uuidLogin.setError(null);
            et_loginPassword.setError(null);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            restApis.getLogin(uuid, password).enqueue(new Callback<GetLoginResponse>() {
                @Override
                public void onResponse(Call<GetLoginResponse> call, Response<GetLoginResponse> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getStatus()) {


                            showCustomDialog(LoginActivity.this, "Do you want to save login details for future auto login?"
                                    , "Save Login Details!!!", "Yes", "No", uuid);

                        } else {
                            String message = response.body().getGetLogin();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetLoginResponse> call, Throwable t) {
                    progressDialog.dismiss();

                }
            });

        }
    }

    public void gotoSignUpActivity(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void showCustomDialog(final Context context, String message, String title, String positive_button, String negative_button, final String uuid) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveDetailsForAutologin(uuid, true);

            }


        });
        builder.setNegativeButton(negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveDetailsForAutologin(uuid, false);
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void saveDetailsForAutologin(final String uuid, final boolean isAutoLogin) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        restApis.getUserDetails(uuid).enqueue(new Callback<GetUserDetailResponse>() {
            @Override
            public void onResponse(Call<GetUserDetailResponse> call, Response<GetUserDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();
                    if (response.body().getStatus()) {
                        List<GetUserDetail> list = response.body().getGetUserDetail();
                        String dId = list.get(0).getDId();
                        String dName = list.get(0).getDName();
                        String dAddress = list.get(0).getDAddress();
                        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("UUID", uuid);
                        editor.putString("dId", dId);
                        editor.putString("dName", dName);
                        editor.putString("dAddress", dAddress);
                        editor.putBoolean("isAutoLogin", isAutoLogin);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, UserMainActivity.class)
                                .putExtra("UUID", uuid)
                        );
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Some Error", Toast.LENGTH_SHORT).show();        //
                    }

                }
            }

            @Override
            public void onFailure(Call<GetUserDetailResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
