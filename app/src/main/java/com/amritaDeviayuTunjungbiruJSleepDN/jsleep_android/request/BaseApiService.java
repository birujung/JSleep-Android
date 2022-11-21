package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request;

import com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.model.*;
import retrofit2.http.*;
import retrofit2.*;

public interface BaseApiService {
    //Account
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String Password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    //@POST("account/{id}/topUp")
    //boolean topUp(@Path("id") int id, @Path("balance") double balance);
}
