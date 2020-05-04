package com.example.fidodelivery.login_details;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserDetail {

    @SerializedName("d_id")
    @Expose
    private String dId;
    @SerializedName("d_name")
    @Expose
    private String dName;
    @SerializedName("d_uuid")
    @Expose
    private String dUuid;
    @SerializedName("d_address")
    @Expose
    private String dAddress;
    @SerializedName("d_bio")
    @Expose
    private String dBio;
    @SerializedName("affliate_res")
    @Expose
    private String affliateRes;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetUserDetail() {
    }

    /**
     *
     * @param password
     * @param dAddress
     * @param dUuid
     * @param affliateRes
     * @param dName
     * @param dBio
     * @param dId
     * @param status
     */
    public GetUserDetail(String dId, String dName, String dUuid, String dAddress, String dBio, String affliateRes, String status, String password) {
        super();
        this.dId = dId;
        this.dName = dName;
        this.dUuid = dUuid;
        this.dAddress = dAddress;
        this.dBio = dBio;
        this.affliateRes = affliateRes;
        this.status = status;
        this.password = password;
    }

    public String getDId() {
        return dId;
    }

    public void setDId(String dId) {
        this.dId = dId;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDUuid() {
        return dUuid;
    }

    public void setDUuid(String dUuid) {
        this.dUuid = dUuid;
    }

    public String getDAddress() {
        return dAddress;
    }

    public void setDAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getDBio() {
        return dBio;
    }

    public void setDBio(String dBio) {
        this.dBio = dBio;
    }

    public String getAffliateRes() {
        return affliateRes;
    }

    public void setAffliateRes(String affliateRes) {
        this.affliateRes = affliateRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}