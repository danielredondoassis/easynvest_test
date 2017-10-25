package com.test.easynvest.view.adapter.holder.fund;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.GraphModel;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DT on 10/25/17.
 */

public class FundChartHolder extends BaseHolder implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;

    public FundChartHolder(View itemView) {
        super(itemView);

        mChart = itemView.findViewById(R.id.lineChart);
    }

    public void setup(GraphModel graphModel){

        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);

        Context context = EasynvestApplication.getStaticContext();

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + "DINPro-Regular.otf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setGridColor(Color.TRANSPARENT);
        xAxis.setTextColor(Color.TRANSPARENT);
        xAxis.setAxisLineColor(Color.TRANSPARENT);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(40f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setGridColor(context.getResources().getColor(R.color.fund_light_gray));
        leftAxis.setAxisLineColor(Color.TRANSPARENT);
        leftAxis.setTextColor(context.getResources().getColor(R.color.fund_light_gray));

        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        mChart.getAxisRight().setEnabled(false);

        setData(graphModel);
        mChart.animateX(250);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData(GraphModel graphModel) {

        ArrayList<Entry> valuesFund = new ArrayList<Entry>();
        ArrayList<Entry> valuesCDI = new ArrayList<Entry>();


        for (int i = 0; i < graphModel.getFundInfo().length; i++) {
            valuesFund.add(new Entry(i, (float) graphModel.getFundInfo()[i]));
        }

        for (int i = 0; i < graphModel.getCdiInfo().length; i++) {
            valuesCDI.add(new Entry(i, (float) graphModel.getCdiInfo()[i]));
        }

        Context context = EasynvestApplication.getStaticContext();

        LineDataSet set1;
        LineDataSet set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);

            set2 = (LineDataSet)mChart.getData().getDataSetByIndex(1);

            set1.setValues(valuesFund);
            set2.setValues(valuesCDI);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();

        } else {

            set1 = new LineDataSet(valuesFund, "Vinci Valorem FI Multimercado");
            set1.setDrawIcons(false);
            set1.setColor(context.getResources().getColor(R.color.chart_purple));
            set1.setCircleColor(Color.TRANSPARENT);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(0f);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            set1.setFillColor(Color.TRANSPARENT);

            set2 = new LineDataSet(valuesCDI, "CDI");
            set2.setDrawIcons(false);
            set2.setColor(context.getResources().getColor(R.color.chart_green));
            set2.setCircleColor(Color.TRANSPARENT);
            set2.setLineWidth(1f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(0f);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);
            set2.setFillColor(Color.TRANSPARENT);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);

            LineData data = new LineData(dataSets);
            mChart.setTouchEnabled(false);
            mChart.disableScroll();
            mChart.setData(data);
        }
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {}

    @Override
    public void onChartDoubleTapped(MotionEvent me) {}

    @Override
    public void onChartSingleTapped(MotionEvent me) {}

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {}

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {}

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {}

    @Override
    public void onValueSelected(Entry e, Highlight h) {}

    @Override
    public void onNothingSelected() {}

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
