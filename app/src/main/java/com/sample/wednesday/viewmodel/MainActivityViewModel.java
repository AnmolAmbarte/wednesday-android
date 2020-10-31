package com.sample.wednesday.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sample.wednesday.model.Data;
import com.sample.wednesday.repo.MainActivityRepository;
import com.sample.wednesday.room.PostRoomDBRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private LiveData<List<Data>> mainLiveData;
    private LiveData<List<Data>> mainLiveDataRoom;
    private MainActivityRepository repository;
    private PostRoomDBRepository postRoomDBRepository;

    public void init(Context context) {
        repository = new MainActivityRepository(context);
        postRoomDBRepository = new PostRoomDBRepository(context);
        mainLiveData = repository.getData(1, 5);
    }

    public LiveData<List<Data>> getDataRepository() {
        return mainLiveData;
    }


    public void getRepositoryRoom() {
        mainLiveDataRoom = postRoomDBRepository.getAllPosts();
    }

    public LiveData<List<Data>> getDataRepositoryRoom() {
        return mainLiveDataRoom;
    }




    public void findTerm(int page, int perPage) {
        mainLiveData = repository.getData(page, perPage);
    }


}
