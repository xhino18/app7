package com.example.app7;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "OrderItem",


        indices = {@Index("id")}
)
public class OrderItemModel {

    @PrimaryKey(autoGenerate = true)
    private Long unique_id;

    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;


    @ColumnInfo(name = "url_image")
    private String urlImage;

    @ColumnInfo(name = "price")
    private Integer price;


    @ColumnInfo(name = "quantity")
    private Integer quantity;


    public OrderItemModel(Long unique_id, @NonNull Integer id, String name,String description, String urlImage, Integer price, Integer quantity) {
        this.unique_id = unique_id;
        this.id = id;
        this.name = name;
        this.description=description;
        this.urlImage = urlImage;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(Long unique_id) {
        this.unique_id = unique_id;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void reductionQuantity() {
        this.quantity--;
    }

}
