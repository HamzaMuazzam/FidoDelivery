package com.example.fidodelivery.login_details;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserDetailResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetUserDetail")
    @Expose
    private List<GetUserDetail> getUserDetail = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetUserDetailResponse() {
    }

    /**
     *
     * @param getUserDetail
     * @param status
     */
    public GetUserDetailResponse(Boolean status, List<GetUserDetail> getUserDetail) {
        super();
        this.status = status;
        this.getUserDetail = getUserDetail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetUserDetail> getGetUserDetail() {
        return getUserDetail;
    }

    public void setGetUserDetail(List<GetUserDetail> getUserDetail) {
        this.getUserDetail = getUserDetail;
    }

}