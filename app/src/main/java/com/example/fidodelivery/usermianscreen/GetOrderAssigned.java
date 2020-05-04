package com.example.fidodelivery.usermianscreen;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderAssigned {

    @SerializedName("oh_id")
    @Expose
    private String ohId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("oh_date")
    @Expose
    private String ohDate;
    @SerializedName("oh_time")
    @Expose
    private String ohTime;
    @SerializedName("oh_quantity")
    @Expose
    private String ohQuantity;
    @SerializedName("oh_bill")
    @Expose
    private String ohBill;
    @SerializedName("oh_usr_id_fk")
    @Expose
    private String ohUsrIdFk;
    @SerializedName("delivered_by")
    @Expose
    private String deliveredBy;
    @SerializedName("o_status")
    @Expose
    private String oStatus;
    @SerializedName("assinged_to")
    @Expose
    private String assingedTo;
    @SerializedName("o_of_res")
    @Expose
    private String oOfRes;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderAssigned() {
    }

    /**
     *
     * @param ohId
     * @param instructions
     * @param address
     * @param ohQuantity
     * @param ohBill
     * @param deliveredBy
     * @param ohDate
     * @param ohUsrIdFk
     * @param oStatus
     * @param oOfRes
     * @param assingedTo
     * @param ohTime
     * @param username
     */
    public GetOrderAssigned(String ohId, String address, String username, String instructions, String ohDate, String ohTime, String ohQuantity, String ohBill, String ohUsrIdFk, String deliveredBy, String oStatus, String assingedTo, String oOfRes) {
        super();
        this.ohId = ohId;
        this.address = address;
        this.username = username;
        this.instructions = instructions;
        this.ohDate = ohDate;
        this.ohTime = ohTime;
        this.ohQuantity = ohQuantity;
        this.ohBill = ohBill;
        this.ohUsrIdFk = ohUsrIdFk;
        this.deliveredBy = deliveredBy;
        this.oStatus = oStatus;
        this.assingedTo = assingedTo;
        this.oOfRes = oOfRes;
    }

    public String getOhId() {
        return ohId;
    }

    public void setOhId(String ohId) {
        this.ohId = ohId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getOhDate() {
        return ohDate;
    }

    public void setOhDate(String ohDate) {
        this.ohDate = ohDate;
    }

    public String getOhTime() {
        return ohTime;
    }

    public void setOhTime(String ohTime) {
        this.ohTime = ohTime;
    }

    public String getOhQuantity() {
        return ohQuantity;
    }

    public void setOhQuantity(String ohQuantity) {
        this.ohQuantity = ohQuantity;
    }

    public String getOhBill() {
        return ohBill;
    }

    public void setOhBill(String ohBill) {
        this.ohBill = ohBill;
    }

    public String getOhUsrIdFk() {
        return ohUsrIdFk;
    }

    public void setOhUsrIdFk(String ohUsrIdFk) {
        this.ohUsrIdFk = ohUsrIdFk;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public String getOStatus() {
        return oStatus;
    }

    public void setOStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public String getAssingedTo() {
        return assingedTo;
    }

    public void setAssingedTo(String assingedTo) {
        this.assingedTo = assingedTo;
    }

    public String getOOfRes() {
        return oOfRes;
    }

    public void setOOfRes(String oOfRes) {
        this.oOfRes = oOfRes;
    }

}