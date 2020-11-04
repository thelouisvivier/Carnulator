package fr.yncrea.carnulator.api;

import fr.yncrea.carnulator.model.CarnutesAPIBeers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface CarnutesApiService {
    @Headers("x-apikey: " + "652ff42837707657b360b0640a90043c48566")
    @GET("beers/")
    Call<CarnutesAPIBeers> getBeers();
}
