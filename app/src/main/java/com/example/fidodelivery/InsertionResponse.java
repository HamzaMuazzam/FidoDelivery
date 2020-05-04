package com.example.fidodelivery;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertionResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public InsertionResponse() {
    }

    /**
     *
     * @param status
     */
    public InsertionResponse(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}