package ph.com.swak.fragment.hris;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;

import ph.com.swak.R;
import ph.com.swak.fragment.BaseFragment;
import ph.com.swak.utils.ChartUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HRISAgeFragment extends BaseFragment {

    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();

    ArrayList<Entry> yVals = new ArrayList<>();
    ArrayList<String> xVals = new ArrayList<>();

    public HRISAgeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hris_age, container, false);

        setData();

        ChartUtil chartUtil = new ChartUtil(getActivity(), rootView);

        PieDataSet dataSet = chartUtil.initPieDataSet(yVals, "Age");
        PieData data = new PieData(xVals, dataSet);
        PieChart pieChart = chartUtil.initPieChart(R.id.pieHRISAge, xVals, "Age Population");
        pieChart.setData(data);
        pieChart.invalidate();

        return rootView;
    }

    private void setData(){
        values = new ArrayList<>();
        values.add(2);
        values.add(48);
        values.add(91);
        values.add(35);
        values.add(5);
        values.add(2);

        labels = new ArrayList<>();
        labels.add("Age: Below 18");
        labels.add("Age: 18 - 25");
        labels.add("Age: 26 - 35");
        labels.add("Age: 36 - 45");
        labels.add("Age: 46 - 55");
        labels.add("Age: 56 Above");

        yVals = new ArrayList<>();
        xVals = new ArrayList<>();

        for(int i = 0; i < values.size(); i++)
            yVals.add(new Entry(values.get(i), i));

        for(String age : labels)
            xVals.add(age);
    }

}
