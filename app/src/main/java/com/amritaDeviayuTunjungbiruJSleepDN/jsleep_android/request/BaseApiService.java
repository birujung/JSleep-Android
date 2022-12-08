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

    @POST("room/create")
    Call<Room> create(@Path("id") int id,
                            @Query("name") String name,
                            @Query("size") int size,
                            @Query("price") int price,
                            @Query("facility") ArrayList<Facility> facility,
                            @Query("city") City city,
                            @Query("address") String address,
                            @Query("bedType") BedType bedType);
}
