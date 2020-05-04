package com.example.fidodelivery.login_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLoginResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetLogin")
    @Expose
    private String getLogin;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLoginResponse() {
    }

    /**
     *
     * @param getLogin
     * @param status
     */
    public GetLoginResponse(Boolean status, String getLogin) {
        super();
        this.status = status;
        this.getLogin = getLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGetLogin() {
        return getLogin;
    }

    public void setGetLogin(String getLogin) {
        this.getLogin = getLogin;
    }

}
