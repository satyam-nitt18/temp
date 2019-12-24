package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccessTokenInt {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/client_credential/accesstoken?grant_type=client_credentials")
    Call<AccessToken> getAccessToken(@Header("Authorization") String authClient);


    @Headers("Content-Type: application/x-www-form-urlencoded")
     @GET("ministatement")
    Call<MiniStatement> getMiniStatement(@Header("Authorization") String authCode,
                                         @Query("AccountNumber") long accountNo,
                                         @Query("NoOfTxn") int noOfTxn);

}
