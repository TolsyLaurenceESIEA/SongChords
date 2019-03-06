package com.example.songchords;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private MainActivity view;

    public Controller(MainActivity view) {
        this.view = view;
    }

    public void onCreate() {
        //Pour ceux qui veulent aller plus loin
        //Il faut créer ces objets avec des singletons.
        // Voir le cours de Génie Logiciel -> Singleton
        //Pour ceux qui veulent encore aller plus loin
        //Voir Injection de dépendances
        //On crée un objet gson

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/TolsyLaurenceESIEA/TolsyLaurenceESIEA.github.io/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre instance de notre RestAPI Pokemon.
        SongsApi restApi = retrofit.create(SongsApi.class);


        Call<SongsResponse> call = restApi.getAllSongs();
        call.enqueue(new Callback<SongsResponse>() {
            @Override
            public void onResponse(Call<SongsResponse> call, Response<SongsResponse> response) {
                SongsResponse songsResponse = response.body();
                List<Songs> listSongs = songsResponse.getSongs();
                view.showList(listSongs);
            }

            @Override
            public void onFailure(Call<SongsResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
