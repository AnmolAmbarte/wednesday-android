package com.sample.wednesday.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sample.wednesday.model.Data;

import java.util.List;

@Dao
public interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Data> dataModel);

    @Query("Select * from details")
    List<Data> getDetails();

    @Query("Select * from details")
    LiveData<List<Data>> getDetailsLive();

    @Update()
    void update(List<Data> dataModel);

}