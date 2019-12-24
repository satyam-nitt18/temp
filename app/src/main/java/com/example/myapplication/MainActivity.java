package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String clientId="p11WZjARgYjOrxU8PCYb8O82iBGaW2pY";
    String clientSecret="RbUy1KjFOVLu7Tn8";

    private EditText textView;
    private Retrofit retrofit;
    private AccessTokenInt accessTokenInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.text);
        String base = clientId+":"+clientSecret;
        String authClient="Basic "+ android.util.Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        getMiniStatement();

         /* retrofit=new Retrofit.Builder().baseUrl("https://api/yesdeveloper.in/")
                .addConverterFactory(GsonConverterFactory.create()).build();

          accessTokenInt=retrofit.create(AccessTokenInt.class);

          final Call<AccessToken> accessTokenCall= accessTokenInt.getAccessToken(authClient);

        Toast.makeText(MainActivity.this, authClient, Toast.LENGTH_SHORT).show();

        accessTokenCall.enqueue(new Callback<AccessToken>() {
              @Override
              public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                  Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                  AccessToken token=response.body();

                  Toast.makeText(MainActivity.this,token.getAccessToken(), Toast.LENGTH_SHORT).show();

              }

              @Override
              public void onFailure(Call<AccessToken> call, Throwable t) {
                  Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

              }
          });*/
    }

    public void getMiniStatement(){

        retrofit=new Retrofit.Builder().baseUrl("https://api.yesdeveloper.in/accountmanagementserice/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        accessTokenInt=retrofit.create(AccessTokenInt.class);

        final Call<MiniStatement> miniStatementCall= accessTokenInt.getMiniStatement("Bearer 9EfLCdavT5AFXuAedHR3WOlAhoMm", 000000000000002, 1);

        miniStatementCall.enqueue(new Callback<MiniStatement>() {
            @Override
            public void onResponse(@NonNull Call<MiniStatement> call, @NonNull Response<MiniStatement> response) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                MiniStatement statement=new MiniStatement();
                statement=response.body();
                if(response.body().txnDetails.getTxnAmount()==null)
                    Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_LONG).show();


                //Toast.makeText(MainActivity.this,miniStatement.getTxnDetails().getTxnAmount(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(@NonNull Call<MiniStatement> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                textView.setText(t.getMessage());

            }
        });
    }
}
