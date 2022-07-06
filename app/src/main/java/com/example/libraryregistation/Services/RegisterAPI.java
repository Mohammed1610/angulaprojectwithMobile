package com.example.libraryregistation.Services;

import com.example.libraryregistation.Model.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterAPI {

    @POST("/api/register/")
    Call<Register> save(@Body Register register);

}
