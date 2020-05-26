package com.sample.wednesday.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sample.wednesday.data.MainActivityRepository;
import com.sample.wednesday.model.Example;
import com.sample.wednesday.model.Result;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Result>> mutableLiveData;
    private MainActivityRepository repository;


    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        repository = MainActivityRepository.getInstance();
        mutableLiveData = repository.getData("bollywood");

    }

    public LiveData<List<Result>> getDataRepository() {
        return mutableLiveData;
    }

    public void findTerm(String term) {
        mutableLiveData = repository.getData(term);
    }


    /*
      private class AsyncData extends AsyncTask<String, Void, MutableLiveData<List<Result>>> {

        @Override
        protected MutableLiveData<List<Result>> doInBackground(String... params) {
            mutableLiveData = repository.getData(params[0]);
            return mutableLiveData;
        }

        @Override
        protected void onPostExecute(MutableLiveData<List<Result>> listMutableLiveData) {
            mutableLiveData = (listMutableLiveData);
            super.onPostExecute(listMutableLiveData);
        }
    }

     */
}
