package com.example.githubusers.Api;

import com.example.githubusers.Models.UserModels;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Users {

    String UserEndPoint = "search/users";

    @GET(UserEndPoint)
    Call<UserModels> GETUSERS(
            @Query("q") String q
    );

}
