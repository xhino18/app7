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
    void insert(Items items);

    @Delete
    void delete(Items items);

    @Update
    void update(Items items);

    @Query("DELETE FROM SelectedItems_table")
    void deleteAll();

    @Query("SELECT * FROM SelectedItems_table")
    LiveData<List<Items>> getAllItems();

}
