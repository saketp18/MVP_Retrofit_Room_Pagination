package com.lite.application.neworking;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lite.application.neworking.networkrequest.models.Article;
import com.lite.application.neworking.utils.CustomRecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IPresenter.NetworkResponse{

    private MainPresenter mainPresenter = null;
    private RecyclerView mRecyclerView = null;
    private LinearLayoutManager mLinearLayoutManager = null;
    private CustomRecyclerAdapter mCustomRecyclerAdapter = null;
    private EditText searchText = null;
    private Button searchButton = null;
    private ProgressBar mProgress = null;
    private int PAGE_COUNT = -1;
    private int PAGE_SIZE = 20;
    private int visibleItemCount, totalItemCount, pastVisibleItems, viewThreshold = 30, previous_total = 0;
    private boolean isLoading = true;

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.searchbtn){
                if(searchText != null){
                    PAGE_COUNT = 1;
                    mProgress.setVisibility(View.VISIBLE);
                    mCustomRecyclerAdapter.clear();
                    mCustomRecyclerAdapter.notifyDataSetChanged();
                    mainPresenter.fetchNews(searchText.getText().toString(), PAGE_COUNT, PAGE_SIZE);
                }
            }
        }
    };
    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            visibleItemCount = mLinearLayoutManager.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();

            if(dy>0){
                if(isLoading && totalItemCount>previous_total){
                    isLoading = false;
                    previous_total = totalItemCount;
                }
                if(!isLoading && totalItemCount-visibleItemCount <= pastVisibleItems + viewThreshold){
                    isLoading = true;
                    PAGE_COUNT++;
                    mainPresenter.fetchNews(searchText.getText().toString(), PAGE_COUNT, PAGE_SIZE);
                }
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layoutanim);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mCustomRecyclerAdapter = new CustomRecyclerAdapter(this);
        mRecyclerView.setLayoutAnimation(animation);
        mRecyclerView.addOnScrollListener(mScrollListener);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mCustomRecyclerAdapter);
        searchText = (EditText)findViewById(R.id.search);
        searchButton = (Button)findViewById(R.id.searchbtn);
        mProgress = (ProgressBar)findViewById(R.id.progress);
        searchButton.setOnClickListener(mClickListener);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }


    @Override
    public void onSuccess(List<Article> response) {
        mProgress.setVisibility(View.GONE);
        mCustomRecyclerAdapter.setData(response);
        mCustomRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Please connect to internet!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        Toast.makeText(this, "All items loaded!!!", Toast.LENGTH_SHORT).show();
    }
}
