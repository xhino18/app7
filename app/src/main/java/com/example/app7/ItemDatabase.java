package com.example.app7;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                OrderItemModel.class,

        },
        exportSchema = false,
        version = 2
)

public abstract class ItemDatabase extends RoomDatabase {
    private static final String DB_NAME = "app7_db";
    private static ItemDatabase instance;
    public abstract ItemsDAO orderItemDao();

    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ItemDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }



}


