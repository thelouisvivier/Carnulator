package fr.yncrea.carnulator.api;

import java.util.List;

import fr.yncrea.carnulator.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface CarnutesApiService {
    @Headers("x-apikey: " + "652ff42837707657b360b0640a90043c48566")
    @GET("beers")
    Call<List<Beer>> getBeers();
}
