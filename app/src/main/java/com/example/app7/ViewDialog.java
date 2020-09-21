package com.example.app7;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDialog {
    Gson gson;
    List <ModelItemData>list=new ArrayList<>();
    AdapterItem adapterItem;
    RecyclerView recyclerView;
    Context context;

    public ViewDialog(Context context) {
        this.context = context;
    }

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_cart_items);

            recyclerView=(RecyclerView) dialog.findViewById(R.id.recyclerCartItem);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity,RecyclerView.VERTICAL,false));


        Button buttonConfirm = (Button) dialog.findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        getItem(7);

        dialog.show();

    }
    private void getItem(int id) {
        gson=new GsonBuilder().create();
        //loading();
        API apiClient = ApiClient.createApiNoToken();
        Call<ModelItem> call = apiClient.getItem(id);
        call.enqueue(new Callback<ModelItem>() {
            @Override
            public void onResponse(Call<ModelItem> call, Response<ModelItem> response) {
               // dialog.dismiss();
                if (!gson.toJson(response.body()).equalsIgnoreCase("null")) {
                    if (!response.body().getError()) {
                        list.addAll(response.body().getData());
                            adapterItem = new AdapterItem(context, list);
                        recyclerView.setAdapter(adapterItem);


                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ModelItem> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
