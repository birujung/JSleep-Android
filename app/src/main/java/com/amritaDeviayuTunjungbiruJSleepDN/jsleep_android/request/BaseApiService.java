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
    Call<Account> login(@Query("email") String email, @Query("password") String password);

    @POST("account/register")
    Call<Account> register(@Part("name") String name, @Part ("email") String email, @Part ("password") String password);

//    @PUT("account/{id}")
//    Call<Account> putAccount(@Path("id") int id);
//
//    @DELETE("account/{id}")
//    Call<Account> deleteAccount(@Path("id") int id);
}
