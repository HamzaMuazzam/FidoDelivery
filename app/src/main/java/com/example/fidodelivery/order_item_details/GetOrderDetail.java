package com.example.fidodelivery.order_item_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderDetail {

    @SerializedName("od_id")
    @Expose
    private String odId;
    @SerializedName("i_name")
    @Expose
    private String iName;
    @SerializedName("i_price")
    @Expose
    private String iPrice;
    @SerializedName("i_quantity")
    @Expose
    private String iQuantity;
    @SerializedName("menu_item_id_fk")
    @Expose
    private String menuItemIdFk;
    @SerializedName("oh_id_fk")
    @Expose
    private String ohIdFk;
    @SerializedName("oh_id")
    @Expose
    private String ohId;
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
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("get_order_details")
    @Expose
    private String get_order_details;
    @SerializedName("choose_fillings")
    @Expose
    private String choose_fillings;

    public String getChoose_fillings() {
        return choose_fillings;
    }

    public void setChoose_fillings(String choose_fillings) {
        this.choose_fillings = choose_fillings;
    }

    public GetOrderDetail() {
    }

    public String getGet_order_details() {
        return get_order_details;
    }

    public void setGet_order_details(String get_order_details) {
        this.get_order_details = get_order_details;
    }

    public GetOrderDetail(String odId, String iName, String iPrice, String iQuantity, String menuItemIdFk, String ohIdFk, String ohId, String ohDate, String ohTime, String ohQuantity, String ohBill, String ohUsrIdFk, String deliveredBy, String oStatus, String assingedTo, String oOfRes, String phone, String get_order_details, String choose_fillings) {
        this.odId = odId;
        this.iName = iName;
        this.iPrice = iPrice;
        this.iQuantity = iQuantity;
        this.menuItemIdFk = menuItemIdFk;
        this.ohIdFk = ohIdFk;
        this.ohId = ohId;
        this.ohDate = ohDate;
        this.ohTime = ohTime;
        this.ohQuantity = ohQuantity;
        this.ohBill = ohBill;
        this.ohUsrIdFk = ohUsrIdFk;
        this.deliveredBy = deliveredBy;
        this.oStatus = oStatus;
        this.assingedTo = assingedTo;
        this.oOfRes = oOfRes;
        this.phone = phone;
        this.get_order_details = get_order_details;
        this.choose_fillings = choose_fillings;
    }

    public String getOdId() {
        return odId;
    }

    public void setOdId(String odId) {
        this.odId = odId;
    }

    public String getIName() {
        return iName;
    }

    public void setIName(String iName) {
        this.iName = iName;
    }

    public String getIPrice() {
        return iPrice;
    }

    public void setIPrice(String iPrice) {
        this.iPrice = iPrice;
    }

    public String getIQuantity() {
        return iQuantity;
    }

    public void setIQuantity(String iQuantity) {
        this.iQuantity = iQuantity;
    }

    public String getMenuItemIdFk() {
        return menuItemIdFk;
    }

    public void setMenuItemIdFk(String menuItemIdFk) {
        this.menuItemIdFk = menuItemIdFk;
    }

    public String getOhIdFk() {
        return ohIdFk;
    }

    public void setOhIdFk(String ohIdFk) {
        this.ohIdFk = ohIdFk;
    }

    public String getOhId() {
        return ohId;
    }

    public void setOhId(String ohId) {
        this.ohId = ohId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}