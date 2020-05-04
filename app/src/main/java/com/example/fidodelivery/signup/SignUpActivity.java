package com.example.fidodelivery.signup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fidodelivery.R;
import com.example.fidodelivery.network.RestApis;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText et_Name_signup, et_phone_signup, et_address, et_loginPassword, et_loginPasswordConfirm;

    private RestApis restApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initviews();
    }

    private void initviews() {
        restApis = RestApis.retrofit.create(RestApis.class);
        et_Name_signup = findViewById(R.id.et_Name_signup);
        et_phone_signup = findViewById(R.id.et_phone_signup);
        et_address = findViewById(R.id.et_address);
        et_loginPassword = findViewById(R.id.et_loginPassword);
        et_loginPasswordConfirm = findViewById(R.id.et_loginPasswordConfirm);

    }

    public void createAccount(View view) {
        String name = et_Name_signup.getText().toString().trim();
        String phone = et_phone_signup.getText().toString().trim();
        String address = et_address.getText().toString().trim();
        String password = et_loginPassword.getText().toString().trim();
        String confirmPassword = et_loginPasswordConfirm.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            et_Name_signup.setError("can't be empty");
        } else if (TextUtils.isEmpty(phone)) {
            et_Name_signup.setError(null);
            et_phone_signup.setError("can't be empty");

        } else if (TextUtils.isEmpty(address)) {
            et_Name_signup.setError(null);
            et_phone_signup.setError(null);
            et_address.setError("can't be empty");
        } else if (TextUtils.isEmpty(password)) {
            et_Name_signup.setError(null);
            et_phone_signup.setError(null);
            et_address.setError(null);
            et_loginPassword.setError("can't be empty");
        } else if (TextUtils.isEmpty(confirmPassword)) {
            et_Name_signup.setError(null);
            et_phone_signup.setError(null);
            et_address.setError(null);
            et_loginPassword.setError(null);
            et_loginPasswordConfirm.setError("can't be empty");
        } else if (!password.equalsIgnoreCase(confirmPassword)) {
            et_loginPasswordConfirm.setError("Password does not match");
        } else if (phone.length() < 11) {
            et_phone_signup.setError("Invalid number");
        } else {
            et_Name_signup.setError(null);
            et_phone_signup.setError(null);
            et_address.setError(null);
            et_loginPassword.setError(null);
            et_loginPasswordConfirm.setError(null);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Processing....");
            progressDialog.show();
            restApis.signUp(name, "0", phone, address, null, "0", password).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getStatus()) {
                            Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignUpActivity.this, "Login Now", Toast.LENGTH_SHORT).show();
                            SignUpActivity.super.onBackPressed();
                            finish();
                        } else {
                            List<SignUp> signUp = response.body().getSignUp();
                            String message = signUp.get(0).getMessage();
                            Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void backToLogin(View view) {
        super.onBackPressed();
        finish();

    }
}
