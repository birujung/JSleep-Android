package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.*;
import retrofit2.*;

public interface BaseApiService {
    //Account
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login(@Query("email") String email,
                        @Query("password") String password);

    @POST("account/register")
    Call<Account> register(@Query("name") String name,
                           @Query("email") String email,
                           @Query("password") String password);

    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                  @Query("balance") double balance);

    //Renter
    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);

    //Room
    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page,
                                @Query("pageSize") int pageSize);

    @GET("room/getRoomByRenter")
    Call<List<Room>> getRoomByRenter(@Path("id") int id,
                                     @Query("page") int page,
                                     @Query("pageSize") int pageSize);

    @POST("room/create")
    Call<Room> createRoom(@Query("accountId") int accountId,
                      @Query("name") String name,
                      @Query("size") int size,
                      @Query("price") int price,
                      @Query("facility") ArrayList<Facility> facility,
                      @Query("city") City city,
                      @Query("address") String address,
                      @Query("bedType") BedType bedType);

    //Payment
    @POST("payment/create")
    Call<Payment> createPayment(@Query("buyerId") int buyerId,
                         @Query("renterId") int renterId,
                         @Query("roomId") int roomId,
                         @Query("from") String from,
                         @Query("to") String to);

    @POST("payment/{id}/accept")
    boolean accept(@Path("id") int id);

    @POST("payment/{id}/cancel")
    boolean cancel(@Path("id") int id);

    @POST("payment/{id}/submit")
    boolean submit(@Path("id") int id);
}
