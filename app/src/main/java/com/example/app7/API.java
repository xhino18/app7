package com.example.app7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    String BASE_URL="https://develop.almotech.co/hcr-backend/public/api/";

            @GET("menu_categories")
            Call<ModelCategories> getCategories();


            @GET("category_products/{id}")
             Call<ModelItem> getItem(@Path("id")int id);


}
