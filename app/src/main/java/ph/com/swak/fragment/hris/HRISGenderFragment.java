package ph.com.swak.fragment.hris;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
public class HRISGenderFragment extends BaseFragment {

    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();

    ArrayList<Entry> yVals = new ArrayList<>();
    ArrayList<String> xVals = new ArrayList<>();

    public HRISGenderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hris_gender, container, false);

        setData();

        ChartUtil chartUtil = new ChartUtil(getActivity(), rootView);

        PieDataSet dataSet = chartUtil.initPieDataSet(yVals, "Gender");
        PieData data = new PieData(xVals, dataSet);
        PieChart pieChart = chartUtil.initPieChart(R.id.pieHRISGender, xVals, "Gender Population");
        pieChart.setData(data);
        pieChart.invalidate();

        return rootView;
    }

    private void setData(){
        values = new ArrayList<>();
        values.add(52);
        values.add(131);

        labels = new ArrayList<>();
        labels.add("Female");
        labels.add("Male");

        yVals = new ArrayList<>();
        xVals = new ArrayList<>();

        for(int i = 0; i < values.size(); i++)
            yVals.add(new Entry(values.get(i), i));

        for(String gender : labels)
            xVals.add(gender);
    }

}
