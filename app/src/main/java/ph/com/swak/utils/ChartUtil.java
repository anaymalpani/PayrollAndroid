package ph.com.swak.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ph.com.swak.R;

/**
 * Created by Knowell on 3/29/2016.
 */
public class ChartUtil {

    private Context context;
    private View view;

    public ChartUtil(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public LineDataSet initLineDataSet(ArrayList<Entry> entry, String label,
                                       int circleColor, float circleRadius, int lineColor) {
        // Create instance of dataSet
        LineDataSet dataSet = new LineDataSet(entry, label);

        // Settings
        dataSet.setDrawValues(false);
        dataSet.setDrawCubic(true);
        dataSet.setDrawCircleHole(false);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        // Changeable
        dataSet.setCircleColor(circleColor);
        dataSet.setCircleRadius(circleRadius);
        dataSet.setColor(lineColor);

        return dataSet;
    }

    public PieDataSet initPieDataSet(ArrayList<Entry> entry, String label) {
        // Create instance of dataSet
        PieDataSet dataSet = new PieDataSet(entry, label);

        // Set ValueFormatter
        PiePercentValueFormatter vf = new PiePercentValueFormatter();
        dataSet.setValueFormatter(vf);

        // Settings
        dataSet.setDrawValues(true);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueTextColor(Color.DKGRAY);
        dataSet.setValueTypeface(Util.setTypeface(context, Util.FONT));
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(8f);


        return dataSet;
    }

    public LineChart initLineChart(int layoutID, ArrayList<String> xVals, String desc) {
        // Create instance of Linechart
        LineChart lineChart = (LineChart) view.findViewById(layoutID);

        // Custom MarkerView
        MyCustomMarkerView mv = new MyCustomMarkerView(xVals);
        lineChart.setMarkerView(mv);

        // Set LineChart Details
        lineChart.setDescription(desc);
        lineChart.setDescriptionTextSize(20f);
        lineChart.setNoDataText("No data!");
        lineChart.setNoDataTextDescription("The data is not available for now!");

        lineChart.setExtraOffsets(50f, 0f, 40f, 40f);

        // Set LineChart settings
        lineChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        lineChart.setDragDecelerationEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);

        lineChart.getAxisLeft().setAxisMinValue(0f);
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setAvoidFirstLastClipping(true);
        lineChart.getXAxis().setTextSize(16f);



        return lineChart;
    }

    public PieChart initPieChart(int layoutID, ArrayList<String> xVals, String desc) {
        // Create instance of PieChart
        PieChart pieChart = (PieChart) view.findViewById(layoutID);

        // CustomMarkerView
        MyCustomMarkerView mv = new MyCustomMarkerView(xVals);
        pieChart.setMarkerView(mv);

        // Set PieChart details
        pieChart.setHoleRadius(47f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setCenterText(desc);
        pieChart.setCenterTextSize(20f);

        pieChart.setDescription("");
        pieChart.setNoDataText("No data!");
        pieChart.setNoDataTextDescription("The data is not available for now!");

        // Set PieChart settings
        pieChart.getLegend().setEnabled(false);
        pieChart.setDragDecelerationEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawSliceText(false);
        pieChart.setCenterTextTypeface(Util.setTypeface(context, Util.FONT));

        return pieChart;
    }

    private class MyCustomMarkerView extends MarkerView {

        private TextView label;
        private TextView value;
        private ArrayList<String> xVals;

        public MyCustomMarkerView(ArrayList<String> xVals) {
            super(context, R.layout.custom_marker_view);
            this.xVals = xVals;
            label = (TextView) findViewById(R.id.tvContentLabel);
            value = (TextView) findViewById(R.id.tvContentValue);
            label.setTypeface(Util.setTypeface(context, Util.FONT));
            value.setTypeface(Util.setTypeface(context, Util.FONT));
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            String labelText = String.valueOf(xVals.get(highlight.getXIndex())) + " : ";
            String valueText = String.valueOf((int) e.getVal());

            label.setText(labelText);
            value.setText(valueText);
        }

        @Override
        public int getXOffset(float xpos) {
            return -(getWidth() / 2);
        }

        @Override
        public int getYOffset(float ypos) {
            return -getHeight() - 10;
        }
    }

    private class PiePercentValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public PiePercentValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.00");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return String.valueOf(mFormat.format(value)) + "%";
        }
    }

}
