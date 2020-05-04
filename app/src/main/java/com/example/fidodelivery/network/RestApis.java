package com.example.fidodelivery.network;

import com.example.fidodelivery.InsertionResponse;
import com.example.fidodelivery.login_details.GetLoginResponse;
import com.example.fidodelivery.login_details.GetUserDetailResponse;
import com.example.fidodelivery.order_item_details.GetOrderDetailResponse;
import com.example.fidodelivery.signup.SignUpResponse;
import com.example.fidodelivery.usermianscreen.GetOrderAssignedResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApis {

    String BASE_URL = "http://sourceinflow.com/fido/";
    String GET_LOGIN = "delivery_boy_login.php";
    String GET_ASSINED_ORDERS = "get_assigned_orders.php";

    String GET_ORDER_DETAILS_BY_ID = "get_order_details.php";
    String GET_USER_DETAILS = "getuser_details_by_uuid.php";
    String MARK_ORDER_COMPLETE = "order_completed.php";
    String SIGN_UP = "delivery_boy_signup.php";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();


    @FormUrlEncoded
    @POST(GET_LOGIN)
    Call<GetLoginResponse> getLogin(@Field("uuid") String uuid, @Field("password") String password);

    @FormUrlEncoded
    @POST(GET_ASSINED_ORDERS)
    Call<GetOrderAssignedResponse> getAssignedOrders(@Field("uuid") String uuid);


    @FormUrlEncoded
    @POST(GET_ORDER_DETAILS_BY_ID)
    Call<GetOrderDetailResponse> getOrderDetails(@Field("o_id") String o_id);


    @FormUrlEncoded
    @POST(MARK_ORDER_COMPLETE)
    Call<InsertionResponse> markOrderAsCompleted(@Field("orderID") String orderID,
                                                 @Field("status") String status,
                                                 @Field("uuid") String uuid);


    @FormUrlEncoded
    @POST(SIGN_UP)
    Call<SignUpResponse> signUp(@Field("name") String name,
                                @Field("status") String status,
                                @Field("uuid") String uuid
            , @Field("address") String address
            , @Field("bio") String bio
            , @Field("affliatedRes") String affliatedRes
            , @Field("password") String password

    );

    @FormUrlEncoded
    @POST(GET_USER_DETAILS)
    Call<GetUserDetailResponse> getUserDetails(@Field("uuid") String uuid);

//    @GET(GET_RESTAURANT)
//    Call<GetRestaurantNameIDResponse> getNameID_of_Restaurant();
//
//



//
//    @Multipart
//    @POST(INSERT_FEATURED_POST)
//    Call<UploadResponse> insertFeaturedPost(@Part("name") RequestBody name,
//                                            @Part MultipartBody.Part file,
//                                            @Part("posttitle") RequestBody posttitle,
//                                            @Part("description") RequestBody description,
//                                            @Part("d_time") RequestBody d_time,
//                                            @Part("d_fee") RequestBody d_fee,
//                                            @Part("resID") RequestBody resID,
//                                            @Part("vID") RequestBody vID
//    );
//
//
//    @Multipart
//    @POST(UPDATE_FEATURED_POST)
//    Call<UploadResponse> updateFeaturedPostWithPicture(@Part("name") RequestBody name,
//                                                       @Part MultipartBody.Part file,
//                                                       @Part("posttitle") RequestBody posttitle,
//                                                       @Part("description") RequestBody description,
//                                                       @Part("d_time") RequestBody d_time,
//                                                       @Part("d_fee") RequestBody d_fee,
//                                                       @Part("vID") RequestBody vID,
//                                                       @Part("fp_id") RequestBody fp_id);
//
//
//    @FormUrlEncoded
//    @POST(UPDATE_FEATURED_POST_WITHOUT_IMAGE)
//    Call<UploadResponse> updateFeaturedPost_Without_Picture(
//            @Field("posttitle") String posttitle,
//            @Field("description") String description,
//            @Field("d_time") String d_time,
//            @Field("d_fee") String d_fee,
//            @Field("vID") String vID,
//            @Field("fp_id") String fp_id);
//
//
//    @FormUrlEncoded
//    @POST(GET_FEATUREDPOSTLST)
//    Call<GetFeaturedPostResponse> getFeaturedPostList(@Field("resID") String resID
//    );
//
//
//    @FormUrlEncoded
//    @POST(GET_FEATUREDPOSTLSTCOMPLETE)
//    Call<GetFeaturedPostCompleteResponse> getFeaturedPostCOpleteList(@Field("resID") String resID
//    );
//
//    @FormUrlEncoded
//    @POST(INSERT_MENU)
//    Call<InsertionResponse> inserNewMenu(@Field("resID") String resID,
//                                         @Field("menudesc") String menudesc,
//                                         @Field("menuName") String menuName
//    );
//
//
//    @FormUrlEncoded
//    @POST(GET_MENU_LIST)
//    Call<GetMenuIDNAMEResponse> getMenuID_Name(@Field("resID") String resID);
//
//
//    @FormUrlEncoded
//    @POST(UPDATE_MENU)
//    Call<UpdateResponse> update_Menu(@Field("menuID") String menuID
//            , @Field("menuName") String menuName,
//                                     @Field("menuDescription") String menuDescription);
//
//    @Multipart
//    @POST(INSERT_MENU_ITEM)
//    Call<UploadResponse> insertMenuItem(@Part("name") RequestBody name,
//                                        @Part MultipartBody.Part file,
//                                        @Part("itemName") RequestBody itemName,
//                                        @Part("itemDescription") RequestBody itemDescription,
//                                        @Part("itemPrice") RequestBody itemPrice,
//                                        @Part("menuID_FK") RequestBody menuID_FK,
//                                        @Part("topicname") RequestBody topicname
//    );
//
//    @Multipart
//    @POST(UPDATE_MENU_ITEM)
//    Call<UploadResponse> updateMenuItem(@Part("name") RequestBody name,
//                                        @Part MultipartBody.Part file,
//                                        @Part("itemName") RequestBody itemName,
//                                        @Part("itemDescription") RequestBody itemDescription,
//                                        @Part("itemPrice") RequestBody itemPrice,
//                                        @Part("itemID") RequestBody itemID,
//                                        @Part("topicname") RequestBody topicname
//    );
//
//
//    @FormUrlEncoded
//    @POST(GET_MENU_ITEM_LIST)
//    Call<GetMenuItemListResponse> getMenuItemList(@Field("menuID") String menuID
//    );
//
//    @FormUrlEncoded
//    @POST(GET_MENU_ITEM_LIST_BY_ITEM_ID)
//    Call<GetMenuItemListResponse> getMenuItemListByItemID(@Field("itemID") String itemID
//    );
//
//
//    @FormUrlEncoded
//    @POST(DELL_RESTAURANT)
//    Call<InsertionResponse> deleteRestaurant(
//            @Field("resID") String resID
//    );
//
//
//    @FormUrlEncoded
//    @POST(DELL_MENU)
//    Call<InsertionResponse> deleteMenu(
//            @Field("menuID") String menuID);
//
//
//    @FormUrlEncoded
//    @POST(DELL_MENU_ITEM)
//    Call<InsertionResponse> deleteMenuItem(@Field("itemID") String itemID);
//
//
//
//    @FormUrlEncoded
//    @POST(GET_ORDER_DETAILS_BY_ID)
//    Call<GetOrderDetailResponse> getOrderDetails(@Field("o_id") String o_id);
//
//
//
//
//    @FormUrlEncoded
//    @POST(GET_ORDER_DETAILS_BY_NUMBER)
//    Call<GetOrderDetailResponse> getOrderDetailsBynumber(@Field("phoneNumber") String phoneNumber, @Field("orderStatus") String orderStatus);

//     * @GET(GET_CATS_DIAMOND) Call<GetCatsDiamondResponse> getCatsDiamond();
//     * @GET(GET_CATS_GOLD) Call<GetGoldResponse> getCatsGOld();
//     * @GET(GET_CATS_SILVER) Call<GetCatsSilverResponse> getCatsSilver();
//     * @GET(GET_CATS_ARTIFICIAL) Call<GetCatsArtificialResponse> getCatsArtificial();
//     **/
//
//    @GET(GETT_ALL_MATERIALS)
//    Call<GetAllMeterialCatResponse> getAllMaterials();
//    @FormUrlEncoded
//    @POST(NOTIFY_ADMIN)
//    Call<ModelToNotify> notifyAdmin(/*@Field("title") String title, @Field("message") String message, */@Field("topicname") String topicname,
//                                                                                                        @Field("o_id") String o_id, @Field("o_date") String o_date, @Field("c_location") String c_location,
//                                                                                                        @Field("c_number") String c_number);
//
//
//    @FormUrlEncoded
//    @POST(GET_ALLCATS)
//    Call<GetAllCatResponse> getAllCats(@Field("mc_id") String id);
//
//
//    @FormUrlEncoded
//    @POST(GET_ALL_SOS_CATS)
//    Call<GetAllSubofSubCatResponse> getAllSubOfSubCats(@Field("sc_id") String sc_id);
//
//    @FormUrlEncoded
//    @POST(INSERT_ORDERDETAILS)
//    Call<InsertOrderDetails> insertOrderDetails(
//            @Field("name") String name, @Field("size") String size, @Field("price") String price,
//            @Field("image") String image, @Field("quantity") String quantity, @Field("meterial") String meterial,
//            @Field("otime") String otime, @Field("odate") String odate, @Field("paymenttype_id") String paymenttype_id, @Field("jcd_id_fk") String jcd_id_fk,
//            @Field("ordermianid") String ordermainid);
//
//    @FormUrlEncoded
//    @POST(INSERT_CLIENTDETAILS)
//    Call<InsertClient> insertClientdetails(
//            @Field("name") String name
//            , @Field("phone") String phone_No);
//
//    @FormUrlEncoded
//    @POST(CHECKUSEREXISTORNOT)
//    Call<CheckUserExistOrNotResponse> checkUserExistsOrNot(@Field("username") String username,
//                                                           @Field("userphone") String userphone);
//
//    @FormUrlEncoded
//    @POST(INSERTMAINORDERDETAILS)
//    Call<GetMainOrderIdAfterInsertionResponse> insertMainOrderDetails(@Field("uid") String uniqueid,
//                                                                      @Field("datetime") String dateAndTime,
//                                                                      @Field("location") String location,
//                                                                      @Field("lat") String latitude,
//                                                                      @Field("lng") String longitude,
//                                                                      @Field("jdb_id") String jdb_id,
//                                                                      @Field("instructions") String instructions,
//                                                                      @Field("orderstatus") String orderstatus
//    );
//
//
//
//
//    @FormUrlEncoded
//    @POST(GETOFFERS)
//    Call<GetOfferResponse> getAllOffers(@Field("offer_status") String offer_status);
//
//    @FormUrlEncoded
//    @POST(GETALLSOS)
//    Call<GetAllProductByMResponse> getAllProductsByMaterial(@Field("id") String material_id);


}
