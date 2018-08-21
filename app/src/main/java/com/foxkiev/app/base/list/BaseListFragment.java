package com.foxkiev.app.base.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.ui.custom_views.ErrorView;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;

/**
 * Created by lipcha on 26.02.18.
 */

public abstract class BaseListFragment<MODEL extends IdModel, ADAPTER extends BaseAdapter> extends BaseFragment implements MvpListView, BaseAdapter.OnItemClickListener<MODEL> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progressBar)
    View progressView;

    @BindView(R.id.ivEmptyState)
    ImageView ivEmptyState;

    @BindView(R.id.tvEmptyStateMessage)
    TextView tvEmptyStateMessage;

    @BindView(R.id.llEmptyStateContainer)
    View emptyStateContainer;

    @BindView(R.id.errorView)
    ErrorView errorView;

    private SearchView searchView;

    protected ADAPTER mListAdapter;

    private ListConfiguration mListConfiguration;

    protected abstract BaseListPresenter<MODEL, ?> getListPresenter();

    protected abstract ADAPTER createListAdapter(DataProvider<MODEL> dataProvider);

    protected abstract ListConfiguration createListConfiguration();

    @Override
    protected void init(View rootView) {
        super.init(rootView);
        mListConfiguration = createListConfiguration();
        updateListLayoutManager();
        swipeRefreshLayout.setOnRefreshListener(() -> getListPresenter().refreshList());
        mListAdapter = createListAdapter(getListPresenter().getDataProvider());
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnClickListener(this);
        errorView.setOnReloadClickListener(v -> {
            errorView.setVisibility(View.GONE);
            showLoadingProgress();
            getListPresenter().refreshList();
        });
    }

    protected void updateListLayoutManager(){
        recyclerView.setLayoutManager(mListConfiguration.getLayoutManager());
    }

    protected RecyclerView getRecyclerView(){
        return recyclerView;
    }

    protected ADAPTER getAdapter(){
        return mListAdapter;
    }

    @Override
    public void hideLoadingProgress() {
        progressView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showLoadingProgress(){
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyDataSetChange() {
        mListAdapter.notifyDataSetChanged();
        updateEmptyState();
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void notifyItemChanged(int position) {
        mListAdapter.notifyItemChanged(position);
    }

    @Override
    public void notifyDataRemoved(int position) {
        mListAdapter.notifyItemRemoved(position);
        updateEmptyState();
    }

    @Override
    public void showLoadingError(final Throwable throwable) {
        mListAdapter.notifyDataSetChanged();
        errorView.show(throwable);
        emptyStateContainer.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(MODEL model, int position) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final MenuItem searchMenu = menu.findItem(R.id.actionSearch);
        if (searchMenu != null) {
            searchView = (SearchView) searchMenu.getActionView();
            RxUtils.queryTextChangeEvents(searchView, this::onSearchTextChange);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    protected void onSearchTextChange(String text){

    }

    public ListConfiguration getListConfiguration(){
        if (mListConfiguration == null)
            mListConfiguration = createListConfiguration();
        return mListConfiguration;
    }

    protected void updateEmptyState(){
        if (getListPresenter().getDataProvider().getData().isEmpty()){
            emptyStateContainer.setVisibility(View.VISIBLE);
            if (mListConfiguration.getEmptyStateIcon() != 0)
                ivEmptyState.setImageResource(mListConfiguration.getEmptyStateIcon());
            if (mListConfiguration.getEmptyStateMessage() != 0)
                tvEmptyStateMessage.setText(mListConfiguration.getEmptyStateMessage());
        }else emptyStateContainer.setVisibility(View.GONE);
    }
}

