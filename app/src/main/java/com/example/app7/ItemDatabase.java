package com.example.app7;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Items.class,version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;
    public abstract ItemsDAO itemsDAO();

    public  static synchronized ItemDatabase getInstance(Context context){
         if(instance==null){
             instance= Room.databaseBuilder(context.getApplicationContext(),ItemDatabase.class,"Items_database").fallbackToDestructiveMigration().build();

         }
        return  instance;
    }

}
