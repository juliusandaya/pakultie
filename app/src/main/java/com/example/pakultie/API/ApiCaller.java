package com.example.pakultie.API;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCaller {
    @POST("v2/5eafbdb13300007e829f455c")
    Call<List<response>> getData(@Body Fields fields);

}