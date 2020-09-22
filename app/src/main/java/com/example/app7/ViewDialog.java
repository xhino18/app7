package com.example.app7;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app7.RoomDatabase.AddRemoveQuantityClickInterfce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDialog implements AddRemoveQuantityClickInterfce {
    Gson gson;
    List <OrderItemModel>list=new ArrayList<>();
    AdapterItemsSelected adapterItem;
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
        list=ItemDatabase.getInstance(context).orderItemDao().getAllItems();

            recyclerView=(RecyclerView) dialog.findViewById(R.id.recyclerCartItem);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity,RecyclerView.VERTICAL,false));


        Button buttonConfirm = (Button) dialog.findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        adapterItem = new AdapterItemsSelected(context,list);
        recyclerView.setAdapter(adapterItem);

    }
     public void addClicked(ModelItemData data){


     }

    @Override
    public void removeClicked(OrderItemModel data) {

    }

    @Override
    public void addClicked(OrderItemModel data) {

    }
}
