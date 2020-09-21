package com.example.app7;

import android.app.Application;
import android.content.ClipData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsVIewModel {

    public class NoteViewModel extends AndroidViewModel {
        private ItemsRepository repository;
        private LiveData<List<Items>> allNotes;

        public NoteViewModel(@NonNull Application application) {
            super(application);
            repository = new ItemsRepository(application);
            allNotes = repository.getAllItems();
        }

        public void insert(Items items) {
            repository.insert(items);
        }

        public void update(Items items) {
            repository.update(items);
        }

        public void delete(Items items) {
            repository.delete(items);
        }

        public void deleteAllNotes() {
            repository.deleteAll();
        }

        public LiveData<List<Items>> getAllNotes() {
            return allNotes;
        }
    }
}
