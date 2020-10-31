package com.sample.wednesday.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.sample.wednesday.R;
import com.sample.wednesday.adapter.MainActivityAdapter;
import com.sample.wednesday.model.Data;
import com.sample.wednesday.utils.CheckConnection;
import com.sample.wednesday.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private MainActivityViewModel mainActivityViewModel;
    RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;
    Context context;
    private ShimmerFrameLayout mShimmerViewContainer;
    List<Data> mDetails = new ArrayList<Data>();
    private boolean isScrolling = false;
    private boolean isOffline = false;

    ShowHideProgress showHideProgress;
    private int initPage = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckConnection checkConnection = new CheckConnection();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(checkConnection, intentFilter);

        context = MainActivity.this;
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        showHideProgress = new ShowHideProgress(context);

        swipeLayout = findViewById(R.id.swipe_site);
        swipeLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.list_recyclerView);
        mainActivityViewModel = new MainActivityViewModel();
        mainActivityViewModel.init(context);
        mainActivityViewModel.getDataRepository().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> matches) {
                if (matches != null) {
                    mDetails.addAll(matches);
                    mAdapter.notifyDataSetChanged();
                    isOffline = false;
                    swipeLayout.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    showHideProgress.hideDialog();
                    swipeLayout.setRefreshing(false);
                } else {
                    Toast.makeText(context, "NO Connection", Toast.LENGTH_SHORT).show();
                    observeStorage();
                }
            }
        });
        initRecyclerView();
    }


    void observeStorage() {

        mainActivityViewModel.getRepositoryRoom();
        mainActivityViewModel.getDataRepositoryRoom().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                if (data != null) {
                    mDetails.addAll(data);
                    mAdapter.notifyDataSetChanged();
                    isOffline = true;
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    swipeLayout.setVisibility(View.VISIBLE);
                    showHideProgress.hideDialog();
                    swipeLayout.setRefreshing(false);
                }
            }
        });
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new MainActivityAdapter(context, mDetails);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true;
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    int currentItems = linearLayoutManager.getChildCount();
                    int scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                    int totalItems = linearLayoutManager.getItemCount();


                    System.out.println("PAGE  currentItems" + currentItems);
                    System.out.println("PAGE  scrolledOutItems " + scrolledOutItems);
                    System.out.println("PAGE  totalItems" + totalItems);
                    System.out.println("PAGE  Data List Size" + mDetails.size());

                    System.out.println("-----PAGE----  ");
                    if (isScrolling && !isOffline) {
                        if ((currentItems + scrolledOutItems == totalItems)) {
                            if (mDetails.size() < 12) {
                                mainActivityViewModel.findTerm(++initPage, 5);
                                showHideProgress.showDialog("Loading..");
                            }
                        }
                    }
                }

            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        showHideProgress.showDialog("Loading..");
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        showHideProgress.hideDialog();
        super.onPause();
    }

    @Override
    public void onRefresh() {
        mainActivityViewModel.init(context);
        swipeLayout.setRefreshing(false);
    }
}
