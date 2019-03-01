package com.unionbankassociation.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * ApiInterface.java
 * This class act as an interface between Retrofit and Classes used using Retrofit in Application
 *
 * @author Appinvetiv
 * @version 1.0
 * @since 1.0
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@FieldMap HashMap<String, String> map);


    @GET("notice")
    Call<ResponseBody> getNotice(@Header("access_token") String accessToken, @Query("notice_type") String noticeType, @Query("page_no") int pageNumber);

    @Multipart
    @POST("signup")
    Call<ResponseBody> signUpMultipaty(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("change-password")
    Call<ResponseBody> changePassword(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("forget-password")
    Call<ResponseBody> forgotPassword(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("reset-password")
    Call<ResponseBody> reset(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("refresh")
    Call<ResponseBody> refreshToken(@QueryMap HashMap<String, String> map);


    @GET("banners")
    Call<ResponseBody> getPhotoGallery(@Header("access_token") String accessToken, @Query("page_no") int pageNumber);

    @FormUrlEncoded
    @PUT("logout")
    Call<ResponseBody> logout(@Header("access_token") String accessToken, @FieldMap HashMap<String, String> map);

    @GET("contacts")
    Call<ResponseBody> getContactList(@Header("access_token") String accessToken, @Query("page_no") String pageNumber);

}
