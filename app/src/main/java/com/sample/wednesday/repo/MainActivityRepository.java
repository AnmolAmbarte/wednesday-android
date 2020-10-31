package com.sample.wednesday.repo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sample.wednesday.data.APIServiceInterface;
import com.sample.wednesday.data.ApiUtils;
import com.sample.wednesday.model.Data;
import com.sample.wednesday.model.Details;
import com.sample.wednesday.room.PostRoomDBRepository;
import com.sample.wednesday.viewmodel.MainActivityViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRepository {

    private APIServiceInterface apiServiceInterface;
    private MutableLiveData<List<Data>> data = new MutableLiveData<>();


    Context context;

    public MainActivityRepository(Context context) {
        this.context = context;
    }

    private static OkHttpClient providesOkHttpClientBuilder() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).build();

    }

    public LiveData<List<Data>> getData(int page, int perPage) {
        final PostRoomDBRepository postRoomDBRepository = new PostRoomDBRepository(context);
        apiServiceInterface = ApiUtils.getAPIService(providesOkHttpClientBuilder());
        apiServiceInterface.getDetails(page, perPage).enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                if (response.isSuccessful()) {
                    Details details = response.body();
                    Log.d("Wednesday ", ":" + call.request().url());
                    data.setValue(details.getData());
                    postRoomDBRepository.insertPosts(details.getData());
                    System.out.println("Wednesday Data" + response.body().getTotal());
                    Log.d("Wednesday ", ":" + call.request().url());
                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                System.out.println("Wednesday Data error " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

}
