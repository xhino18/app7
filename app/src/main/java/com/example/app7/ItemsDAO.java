package com.example.app7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemsDAO {

    @Insert
    void insert(OrderItemModel items);


    @Update
    void update(OrderItemModel items);

    @Query("DELETE FROM OrderItem")
    void deleteAll();

    @Query("SELECT * FROM OrderItem ORDER BY id")
    List<OrderItemModel> getAllItems();

    @Delete
    void delete(OrderItemModel itemModel);
}
