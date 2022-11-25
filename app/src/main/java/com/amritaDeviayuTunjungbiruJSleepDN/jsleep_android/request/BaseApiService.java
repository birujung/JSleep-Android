package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request;

import android.widget.EditText;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
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
                           @Query ("email") String email,
                           @Query ("password") String password);

    //Renter
    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);
}
