package com.example.fidodelivery.signup;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("SignUp")
    @Expose
    private List<SignUp> signUp = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SignUpResponse() {
    }

    /**
     *
     * @param signUp
     * @param status
     */
    public SignUpResponse(Boolean status, List<SignUp> signUp) {
        super();
        this.status = status;
        this.signUp = signUp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SignUp> getSignUp() {
        return signUp;
    }

    public void setSignUp(List<SignUp> signUp) {
        this.signUp = signUp;
    }

}