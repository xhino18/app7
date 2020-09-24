package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickInterface{
RecyclerView recycleCategories;
TextView textIndex,textCategories;
Gson gson;
List<ModelCategoriesData> categoriesList= new ArrayList<>();
AdapterCategories adapterCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textIndex=findViewById(R.id.textIndex);
        textCategories=findViewById(R.id.textCategories);
        gson= new GsonBuilder().create();
        recycleCategories=findViewById(R.id.recyclerMenu);
        recycleCategories.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        getCategories();



    }

    private void getCategories() {
        API apiClient = ApiClient.createApiNoToken();
        Call<ModelCategories> call = apiClient.getCategories();
        call.enqueue(new Callback<ModelCategories>() {
            @Override
            public void onResponse(Call<ModelCategories> call, Response<ModelCategories> response) {
                if (!gson.toJson(response.body()).equalsIgnoreCase("null")) {
                    if (!response.body().getError()) {
                        categoriesList.addAll(response.body().getData());
                        adapterCategories = new AdapterCategories(MainActivity.this, categoriesList);
                        recycleCategories.setAdapter(adapterCategories);
                    } else {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelCategories> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void categoryClicked(ModelCategoriesData data) {
        Intent intent=new Intent(this,ActivityProducts.class);
        intent.putExtra("cat_id",data.getId());
        startActivity(intent);

    }
}