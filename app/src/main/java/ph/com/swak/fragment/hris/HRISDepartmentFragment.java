package ph.com.swak.fragment.hris;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import ph.com.swak.R;
import ph.com.swak.fragment.BaseFragment;
import ph.com.swak.utils.ChartUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HRISDepartmentFragment extends BaseFragment {

    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();

    ArrayList<String> xVals = new ArrayList<>();
    ArrayList<Entry> yVals = new ArrayList<>();

    public HRISDepartmentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hris_department, container, false);

        setData();

        ChartUtil chartUtil = new ChartUtil(getActivity(), rootView);

        PieDataSet dataSet = chartUtil.initPieDataSet(yVals, "Gender");
        PieData data = new PieData(xVals, dataSet);
        PieChart pieChart = chartUtil.initPieChart(R.id.pieHRISDepartment, xVals, "Department Population");
        pieChart.setData(data);
        pieChart.invalidate();

        return rootView;
    }

    private void setData(){
        values = new ArrayList<>();
        values.add(27);
        values.add(3);
        values.add(2);
        values.add(6);
        values.add(7);
        values.add(10);
        values.add(1);
        values.add(1);
        values.add(7);
        values.add(4);
        values.add(7);
        values.add(12);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(6);
        values.add(10);
        values.add(2);

        labels = new ArrayList<>();
        labels.add("ACCOUNTING");
        labels.add("AUDIT");
        labels.add("DSA SALES");
        labels.add("HR / ADMIN");
        labels.add("IT");
        labels.add("MARKETING");
        labels.add("PLANNING AND DESIGN");
        labels.add("PURCHASING");
        labels.add("SALES");
        labels.add("SALES - LIVE");
        labels.add("SALES ADMINISTRATION");
        labels.add("SALES CLUSTER - MANA");
        labels.add("SALES CLUSTER - MALOLOS");
        labels.add("SALES CLUSTER - PLARIDEL");
        labels.add("SALES CLUSTER - STA. MARIA");
        labels.add("SALES CLUSTER - SUBIC");
        labels.add("SALES CLUSTER - VAL");
        labels.add("TREASURY");

        yVals = new ArrayList<>();
        xVals = new ArrayList<>();

        for(int i = 0; i < values.size(); i++)
            yVals.add(new Entry(values.get(i), i));

        for(String dept : labels)
            xVals.add(dept);
    }

}
