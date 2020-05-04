package com.example.fidodelivery.order_item_details;

import com.example.fidodelivery.order_item_details.GetOrderDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderDetailResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderDetail")
    @Expose
    private List<GetOrderDetail> getOrderDetail = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderDetailResponse() {
    }

    /**
     *
     * @param getOrderDetail
     * @param status
     */
    public GetOrderDetailResponse(Boolean status, List<GetOrderDetail> getOrderDetail) {
        super();
        this.status = status;
        this.getOrderDetail = getOrderDetail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderDetail> getGetOrderDetail() {
        return getOrderDetail;
    }

    public void setGetOrderDetail(List<GetOrderDetail> getOrderDetail) {
        this.getOrderDetail = getOrderDetail;
    }

}