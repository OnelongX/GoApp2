package com.ways2u.android.goapp.meizi.adapter;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ways2u.android.goapp.meizi.view.BaseRecyclerView;


public abstract class BaseRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 1;
    int visibleItemCount, totalItemCount;
    int[] staggerLastVisibleItemPosition = new int[BaseRecyclerView.LIN_NUM];
    int lastVisibleItemPosition = -1;

    private int currentPage = 2;

    private RecyclerView.LayoutManager layoutManager;

    public BaseRecyclerOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        totalItemCount = layoutManager.getItemCount();
        visibleItemCount = recyclerView.getChildCount();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(staggerLastVisibleItemPosition);
            lastVisibleItemPosition = findMax(staggerLastVisibleItemPosition);
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        if (loading) {
            if (totalItemCount - 1 > previousTotal) { //加载完数据而不是footer
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && visibleItemCount > 0
                && lastVisibleItemPosition
                >= (totalItemCount - visibleThreshold)) {
            currentPage++;
            loading = true;
            onLoadMore(currentPage);
        }
    }

    public abstract void onLoadMore(int currentPage);

    public void clearPage() {
        currentPage = 1;
        loading = true;
        previousTotal = 0;
    }

    public void loadingError() {
        loading = false;
        currentPage--;
        previousTotal = 0;
    }

    private int findMax(int[] array) {
        int max = 0;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}