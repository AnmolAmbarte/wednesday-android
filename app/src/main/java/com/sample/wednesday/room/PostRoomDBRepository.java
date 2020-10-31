package com.sample.wednesday.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sample.wednesday.model.Data;

import java.util.List;

public class PostRoomDBRepository {
    private DetailsDao detailsDao;
    LiveData<List<Data>> mAllPosts;

    public PostRoomDBRepository(Context context) {
        DetailsRoomDatabase db = DetailsRoomDatabase.getDatabase(context);
        detailsDao = db.dataDao();
    }

    public LiveData<List<Data>> getAllPosts() {
        return detailsDao.getDetailsLive();

    }

    public void insertPosts(List<Data> dataModel) {
        new insertAsyncTask(detailsDao).execute(dataModel);
    }

    private static class insertAsyncTask extends AsyncTask<List<Data>, Void, Void> {

        private DetailsDao mAsyncTaskDao;

        insertAsyncTask(DetailsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<Data>... data) {
            synchronized (this) {
                List<Data> itemsFromDB = mAsyncTaskDao.getDetails();
                if (itemsFromDB.isEmpty()) {
                    mAsyncTaskDao.insert(data[0]);
                    System.out.println("I was here to insert");
                } else {
                    mAsyncTaskDao.update(data[0]);
                    System.out.println("I was here to update");
                }

            }
            return null;
        }
    }
}
