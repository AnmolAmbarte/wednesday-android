package com.sample.wednesday.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sample.wednesday.model.Data;
import com.sample.wednesday.model.Details;


@Database(entities = {Data.class}, version = 1)

public abstract class DetailsRoomDatabase extends RoomDatabase {

    public abstract DetailsDao dataDao();


    private static volatile DetailsRoomDatabase detailsRoomDatabase;


    public static DetailsRoomDatabase getDatabase(final Context context) {
        if (detailsRoomDatabase == null) {
            synchronized (DetailsRoomDatabase.class) {
                if (detailsRoomDatabase == null) {
                    detailsRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            DetailsRoomDatabase.class, "site_database")
                            .build();
                }
            }
        }
        return detailsRoomDatabase;
    }

}
