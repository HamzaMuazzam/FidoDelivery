package com.example.fidodelivery.order_item_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCFbyID {

    @SerializedName("cf_id")
    @Expose
    private String cfId;
    @SerializedName("cf_name")
    @Expose
    private String cfName;
    @SerializedName("cf_price")
    @Expose
    private String cfPrice;
    @SerializedName("cf_boolean")
    @Expose
    private Boolean cfBoolean;
    @SerializedName("res_id_fk")
    @Expose
    private String resIdFk;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetCFbyID() {
    }

    /**
     *
     * @param cfId
     * @param cfPrice
     * @param cfBoolean
     * @param resIdFk
     * @param cfName
     */
    public GetCFbyID(String cfId, String cfName, String cfPrice, Boolean cfBoolean, String resIdFk) {
        super();
        this.cfId = cfId;
        this.cfName = cfName;
        this.cfPrice = cfPrice;
        this.cfBoolean = cfBoolean;
        this.resIdFk = resIdFk;
    }

    public String getCfId() {
        return cfId;
    }

    public void setCfId(String cfId) {
        this.cfId = cfId;
    }

    public String getCfName() {
        return cfName;
    }

    public void setCfName(String cfName) {
        this.cfName = cfName;
    }

    public String getCfPrice() {
        return cfPrice;
    }

    public void setCfPrice(String cfPrice) {
        this.cfPrice = cfPrice;
    }

    public Boolean getCfBoolean() {
        return cfBoolean;
    }

    public void setCfBoolean(Boolean cfBoolean) {
        this.cfBoolean = cfBoolean;
    }

    public String getResIdFk() {
        return resIdFk;
    }

    public void setResIdFk(String resIdFk) {
        this.resIdFk = resIdFk;
    }

}