package com.sample.wednesday.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.wednesday.R;
import com.sample.wednesday.adapter.MainActivityAdapter;
import com.sample.wednesday.data.MainActivityRepository;
import com.sample.wednesday.model.Example;
import com.sample.wednesday.model.Result;
import com.sample.wednesday.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private MainActivityViewModel mainActivityViewModel;
    RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;
    Context context;
    MainActivityRepository repository = MainActivityRepository.getInstance();
    List<Result> mDetails = new ArrayList<Result>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.home_tool_bar);
        getSupportActionBar().setElevation(1);
        View view = getSupportActionBar().getCustomView();
        final EditText ed_searchTerm = view.findViewById(R.id.ed_find_term);
        ImageView iv_searchTerm = view.findViewById(R.id.iv_find_term);

        iv_searchTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivityViewModel != null) {
                    swipeLayout.setRefreshing(true);
                    mDetails.clear();
                    String data = ed_searchTerm.getText().toString();
                    mainActivityViewModel.findTerm(data);
                }
            }
        });


        swipeLayout = findViewById(R.id.swipe_site);
        swipeLayout.setRefreshing(true);
        swipeLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.list_recyclerView);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getDataRepository().observe(this,
                new Observer<List<Result>>() {
                    @Override
                    public void onChanged(List<Result> matches) {
                        System.out.println("Matches" + matches);
                        System.out.println("Matches " + matches.size());
                        mDetails.addAll(matches);
                        mAdapter.notifyDataSetChanged();
                        swipeLayout.setRefreshing(false);
                    }
                });
        initRecyclerView();
        swipeLayout.setRefreshing(false);

    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new MainActivityAdapter(context, mDetails);
            RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onRefresh() {
        mainActivityViewModel.init();
        swipeLayout.setRefreshing(false);
    }
}
