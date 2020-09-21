package com.example.app7;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.jar.Attributes;

@Entity(tableName = "SelectedItems_table")
public class Items {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private int price;

    public Items(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
