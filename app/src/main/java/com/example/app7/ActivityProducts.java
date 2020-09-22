package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app7.RoomDatabase.MakeOrderClicked;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProducts extends AppCompatActivity implements MakeOrderClicked {
    Gson gson;
    TextView nameItem, descriptionItem, priceItem, textBackButton,textQuantity;
    ImageView imageView, imageError, imageCart;
    List<ModelItemData> itemList = new ArrayList<>();
    AdapterItem adapterItem;
    RecyclerView recyclerItem;
    SearchView search_view;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        final ViewDialog alertDialoge = new ViewDialog(this);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerItem = findViewById(R.id.recyclerItem);
        recyclerItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        nameItem = findViewById(R.id.nameItem);
        descriptionItem = findViewById(R.id.descriptionItem);
        priceItem = findViewById(R.id.priceItem);
        textQuantity=findViewById(R.id.textQuantity);
        imageView = findViewById(R.id.image);
        imageError = findViewById(R.id.imageError);
        imageCart = findViewById(R.id.imageCart);
        gson = new GsonBuilder().create();
        dialog = new ProgressDialog(ActivityProducts.this);
        textBackButton = findViewById(R.id.textBackButton);
        textBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCategory(newText);
                return true;
            }
        });
        int catId = getIntent().getIntExtra("cat_id", -1);
        getItem(catId);

        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialoge.showDialog(ActivityProducts.this, "TITLE");

            }
        });


    }


    private void getItem(int id) {
        loading();
        API apiClient = ApiClient.createApiNoToken();
        Call<ModelItem> call = apiClient.getItem(id);
        call.enqueue(new Callback<ModelItem>() {
            @Override
            public void onResponse(Call<ModelItem> call, Response<ModelItem> response) {
                dialog.dismiss();
                if (!gson.toJson(response.body()).equalsIgnoreCase("null")) {
                    if (!response.body().getError()) {
                        itemList.addAll(response.body().getData());
                        if (itemList.size() < 1) {
                            imageError.setVisibility(View.VISIBLE);
                        } else {
                            adapterItem = new AdapterItem(ActivityProducts.this, itemList);
                            recyclerItem.setAdapter(adapterItem);
                            imageError.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(ActivityProducts.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityProducts.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ModelItem> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ActivityProducts.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loading() {
        dialog.setMessage("Loading...");
        dialog.show();
    }

    private void searchCategory(String s) {
        List<ModelItemData> data = new ArrayList<>();
        data.addAll(itemList);
        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).getName().toUpperCase().contains(s.toUpperCase())) {
                data.remove(i);
                i--;
            }
        }
        adapterItem = new AdapterItem(ActivityProducts.this, data);
        recyclerItem.setAdapter(adapterItem);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void productClicked(ModelItemData itemData) {
       OrderItemModel orderItemModel= parseProductToOrderItem(itemData);
        boolean found = false;
        for (OrderItemModel basketOrderItem : ItemDatabase.getInstance(this).orderItemDao().getAllItems()) {
            if (basketOrderItem.getId().equals(orderItemModel.getId())) {
                found = true;
                basketOrderItem.incrementQuantity();
                ItemDatabase.getInstance(this).orderItemDao().update(basketOrderItem);
                Toast.makeText(this, "Quantity ++", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (!found) {
            ItemDatabase.getInstance(this).orderItemDao().insert(orderItemModel);
            Toast.makeText(this, "U shtuaaaa", Toast.LENGTH_SHORT).show();
        }


    }

    public OrderItemModel parseProductToOrderItem(ModelItemData data){
        return new OrderItemModel(
               new Long(data.getId()),
                data.getId(),
                data.getName(),
                data.getDescription(),
                data.getImage(),
                data.getPrice(),
                1

        );
        }
    }
