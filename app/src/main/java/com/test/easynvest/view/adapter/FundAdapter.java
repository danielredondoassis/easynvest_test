package com.test.easynvest.view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.data.model.fund.RowModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.fund.FundChartHolder;
import com.test.easynvest.view.adapter.holder.fund.FundDownInfoHolder;
import com.test.easynvest.view.adapter.holder.fund.FundHeaderHolder;
import com.test.easynvest.view.adapter.holder.fund.FundInvestButtonHolder;
import com.test.easynvest.view.adapter.holder.fund.FundMoreInfoHolder;
import com.test.easynvest.view.adapter.holder.fund.FundRiskHolder;
import com.test.easynvest.view.adapter.holder.fund.FundRowHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldEmailHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldPhoneHolder;
import com.test.easynvest.view.adapter.holder.signup.TextHolder;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by DT on 10/22/17.
 */

public class FundAdapter extends RecyclerView.Adapter {


    private int total;
    private Context context;
    private WeakReference<AppCompatActivity> activity;
    private FundModel mSource;

    private static final int VH_HEADER = 0;
    private static final int VH_GRAPH = 1;
    private static final int VH_RISK = 2;
    private static final int VH_MORE_INFO = 3;
    private static final int VH_INFO = 4;
    private static final int VH_DOWN_INFO = 5;
    private static final int VH_INVEST = 6;


    private static final int FUND_HEADER = 1;
    private static final int FUND_GRAPH = 1;
    private static final int FUND_RISK = 1;
    private static final int FUND_MORE_INFO = 1;


    public FundAdapter(AppCompatActivity activity, FundModel fundModel) {
        this.activity = new WeakReference<>(activity);
        this.context = this.activity.get();
        this.mSource = fundModel;
        this.total = FUND_HEADER +
                FUND_GRAPH +
                FUND_RISK +
                FUND_MORE_INFO +
                (mSource.getInfo() != null ? mSource.getInfo().length : 0) +
                (mSource.getDownInfo() != null ? mSource.getDownInfo().length : 0);
    }

    @Override
    public int getItemViewType(int position) {
        int infoSize = mSource.getInfo() != null ? mSource.getInfo().length : 0;

        if (position == 0) {
            return VH_HEADER;
        } else if (position == 1) {
            return VH_GRAPH;
        } else if (position == 2) {
            return VH_RISK;
        } else if (position == 3) {
            return VH_MORE_INFO;
        } else if (position > 3 && position-4 < infoSize) {
            return VH_INFO;
        } else if (position > 3 + infoSize && position < getItemCount()-1) {
            return VH_DOWN_INFO;
        }
        return VH_DOWN_INFO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VH_HEADER:
                view = inflater.inflate(R.layout.vh_fund_header, parent, false);
                return new FundHeaderHolder(view);
            case VH_GRAPH:
                view = inflater.inflate(R.layout.vh_fund_chart, parent, false);
                return new FundChartHolder(view);
            case VH_RISK:
                view = inflater.inflate(R.layout.vh_fund_risk, parent, false);
                return new FundRiskHolder(view);
            case VH_MORE_INFO:
                view = inflater.inflate(R.layout.vh_fund_more_info, parent, false);
                return new FundMoreInfoHolder(view);
            case VH_INFO:
                view = inflater.inflate(R.layout.vh_fund_info, parent, false);
                return new FundRowHolder(view);
            case VH_DOWN_INFO:
                view = inflater.inflate(R.layout.vh_fund_down_info, parent, false);
                return new FundDownInfoHolder(view);
            default:
                view = inflater.inflate(R.layout.vh_fund_down_info, parent, false);
                return new TextHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VH_HEADER:
                FundHeaderHolder fundHeaderHolder = (FundHeaderHolder) holder;
                fundHeaderHolder.setup(mSource);
                break;
            case VH_GRAPH:
                FundChartHolder fundChartHolder = (FundChartHolder) holder;
                fundChartHolder.setup(mSource.getGraphInfo());
                break;
            case VH_RISK:
                FundRiskHolder fundRiskHolder = (FundRiskHolder) holder;
                fundRiskHolder.setup(mSource.getRisk());
                break;
            case VH_MORE_INFO:
                FundMoreInfoHolder fundMoreInfoHolder = (FundMoreInfoHolder) holder;
                fundMoreInfoHolder.setup(mSource.getMoreInfo());
                break;
            case VH_INFO:
                FundRowHolder fundRowHolder = (FundRowHolder) holder;

                RowModel[] info = mSource.getInfo();

                int posInfo = position-4;
                if(info != null && info.length > posInfo) {
                    fundRowHolder.setup(info[posInfo]);
                }
                break;
            case VH_DOWN_INFO:
                FundDownInfoHolder fundDownInfoHolder = (FundDownInfoHolder) holder;

                RowModel[] infoRows = mSource.getInfo();
                RowModel[] downInfo = mSource.getDownInfo();

                int infoSize = 0;
                if(infoRows != null) infoSize = infoRows.length;

                int posDownInfo = position-infoSize-4;
                if(downInfo != null && downInfo.length > posDownInfo) {
                    fundDownInfoHolder.setup(downInfo[posDownInfo]);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return total;
    }
}

