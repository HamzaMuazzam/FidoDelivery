package com.example.fidodelivery.usermianscreen;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderAssignedResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderAssigned")
    @Expose
    private List<GetOrderAssigned> getOrderAssigned = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderAssignedResponse() {
    }

    /**
     *
     * @param getOrderAssigned
     * @param status
     */
    public GetOrderAssignedResponse(Boolean status, List<GetOrderAssigned> getOrderAssigned) {
        super();
        this.status = status;
        this.getOrderAssigned = getOrderAssigned;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderAssigned> getGetOrderAssigned() {
        return getOrderAssigned;
    }

    public void setGetOrderAssigned(List<GetOrderAssigned> getOrderAssigned) {
        this.getOrderAssigned = getOrderAssigned;
    }

}