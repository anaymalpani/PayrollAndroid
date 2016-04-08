package ph.com.swak.fragment.hris;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import ph.com.swak.R;
import ph.com.swak.fragment.BaseFragment;
import ph.com.swak.utils.ChartUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HRISResignationFragment extends BaseFragment {

    ArrayList<Integer> values2010 = new ArrayList<>();
    ArrayList<Integer> values2011 = new ArrayList<>();
    ArrayList<Integer> values2012 = new ArrayList<>();
    ArrayList<Integer> values2013 = new ArrayList<>();
    ArrayList<Integer> values2014 = new ArrayList<>();
    ArrayList<Integer> values2015 = new ArrayList<>();
    ArrayList<Integer> values2016 = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();

    ArrayList<Entry> yVals = new ArrayList<>();
    ArrayList<String> xVals = new ArrayList<>();


    public HRISResignationFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hris_resignation, container, false);

        setData();

        ChartUtil chartUtil = new ChartUtil(getActivity(), rootView);
        LineDataSet dataSet = chartUtil.initLineDataSet(yVals, "2015", R.color.dot_color_1, 4f, R.color.line_color_1);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData data = new LineData(xVals, dataSets);
        LineChart lineChart = chartUtil.initLineChart(R.id.lineHRISResignation, xVals, "Resignation Frequency");
        lineChart.setData(data);
        lineChart.invalidate();

        return rootView;
    }

    private void setData(){
        values2015 = new ArrayList<>();
        values2015.add(6);
        values2015.add(416);
        values2015.add(12);
        values2015.add(20);
        values2015.add(15);
        values2015.add(15);
        values2015.add(13);
        values2015.add(12);
        values2015.add(14);
        values2015.add(18);
        values2015.add(37);
        values2015.add(15);

        labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");

        yVals = new ArrayList<>();
        xVals = new ArrayList<>();

        for(int i = 0; i < values2015.size(); i++)
            yVals.add(new Entry(values2015.get(i), i));

        for(String month : labels)
            xVals.add(month);
    }

}
