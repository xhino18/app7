package com.example.app7;

import android.app.Application;
import android.content.ClipData;
import android.os.AsyncTask;
import android.telephony.ims.ImsMmTelManager;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsRepository {

    private ItemsDAO itemsDAO ;
    private LiveData<List<Items>> allItems;
    public ItemsRepository(Application application) {
        ItemDatabase database = ItemDatabase.getInstance(application);
        itemsDAO = database.itemsDAO();
        allItems = itemsDAO.getAllItems();
    }
    public void insert(Items items) {
        new InsertItemAsyncTask(itemsDAO).execute(items);
    }
    public void update(Items items) {
        new UpdateItemAsyncTask(itemsDAO).execute(items);
    }
    public void delete(Items items) {
        new DeleteItemAsyncTask(itemsDAO).execute(items);
    }
    public void deleteAll() {
        new DeleteAllItemsAsyncTask(itemsDAO).execute();
    }
    public LiveData<List<Items>> getAllItems() {
        return allItems;
    }
    private static class InsertItemAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemsDAO noteDao;
        private InsertItemAsyncTask(ItemsDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Items... items) {
            noteDao.insert(items[0]);
            return null;
        }
    }
    private static class UpdateItemAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemsDAO noteDao;
        private UpdateItemAsyncTask(ItemsDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Items... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteItemAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemsDAO noteDao;
        private DeleteItemAsyncTask(ItemsDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Items... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemsDAO noteDao;
        private DeleteAllItemsAsyncTask(ItemsDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
