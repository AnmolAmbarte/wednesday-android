package com.sample.wednesday.data;

import androidx.lifecycle.MutableLiveData;

import com.sample.wednesday.model.Example;
import com.sample.wednesday.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRepository {

    private static MainActivityRepository repository;
    private APIServiceInterface apiServiceInterface;
    private MutableLiveData<List<Result>> data = new MutableLiveData<>();

    public static MainActivityRepository getInstance() {
        if (repository == null) {
            repository = new MainActivityRepository();
        }
        return repository;
    }


    public MutableLiveData<List<Result>> getData(String term) {
        apiServiceInterface = ApiUtils.getAPIService();
        apiServiceInterface.getNewsList(term).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Example example = response.body();
                    data.setValue(example.getResults());
                    System.out.println("Wednesday Data" + response.body().getResultCount());

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                System.out.println("Wednesday Data" + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

}
