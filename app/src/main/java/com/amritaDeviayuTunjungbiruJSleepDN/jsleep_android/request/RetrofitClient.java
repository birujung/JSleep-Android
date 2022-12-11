package com.amritaDeviayuTunjungbiruJSleepDN.jsleep_android.request;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The {@code RetrofitClient} class provides a Retrofit client for making HTTP requests.
 *
 * @author Amrita Deviayu Tunjungbiru
 * @version 1.0
 */
public class RetrofitClient {
    /**
     * The Retrofit client instance.
     */
    private static Retrofit retrofit = null;

    /**
     * Returns a Retrofit client with the specified base URL.
     *
     * @param baseUrl the base URL for the client
     * @return a Retrofit client with the specified base URL
     */
    public static Retrofit getClient(String baseUrl) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
