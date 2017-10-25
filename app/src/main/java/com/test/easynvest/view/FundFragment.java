package com.test.easynvest.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.util.typeface.FontButton;
import com.test.easynvest.view.adapter.FundAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FundFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnInvest)
    FontButton mBtnInvest;
    private Unbinder unbinder;

    private static final String FUND_MODEL_KEY = "FUND_MODEL_KEY";
    private FundModel mFunds;
    private FundAdapter mAdapter;

    public static FundFragment newInstance(FundModel fundModel) {
        Bundle args = new Bundle();
        args.putSerializable(FUND_MODEL_KEY, fundModel);
        FundFragment fragment = new FundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fund, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null)
            mFunds = (FundModel) getArguments().getSerializable(FUND_MODEL_KEY);

        mAdapter = new FundAdapter((AppCompatActivity) getActivity(), mFunds);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(!mRecyclerView.isComputingLayout() && isMaxScrollReached(mRecyclerView)) {
                    mBtnInvest.animate().alpha(1f).setDuration(150).start();
                } else {
                    if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                        mBtnInvest.animate().alpha(0f).setDuration(150).start();
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return view;
    }

    static private boolean isMaxScrollReached(RecyclerView recyclerView) {
        int maxScroll = recyclerView.computeVerticalScrollRange();
        int currentScroll = recyclerView.computeVerticalScrollOffset() + recyclerView.computeVerticalScrollExtent();
        return currentScroll >= maxScroll;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
