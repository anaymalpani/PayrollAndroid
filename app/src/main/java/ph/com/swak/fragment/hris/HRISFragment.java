package ph.com.swak.fragment.hris;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;

import ph.com.swak.R;
import ph.com.swak.fragment.BaseFragment;
import ph.com.swak.fragment.hris.HRISGenderFragment;
import ph.com.swak.utils.ChartUtil;

public class HRISFragment extends BaseFragment {

    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;
    FloatingActionButton floatingActionButton3;

    public HRISFragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hris, container, false);

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fabHRIS);
        floatingActionButton1 = (FloatingActionButton) rootView.findViewById(R.id.fabHRISm1);
        floatingActionButton2 = (FloatingActionButton) rootView.findViewById(R.id.fabHRISm2);
        floatingActionButton3 = (FloatingActionButton) rootView.findViewById(R.id.fabHRISm3);

        // Setup Tabs
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (floatingActionButton1.getVisibility() == View.VISIBLE) {
                    floatingActionButton.setImageResource(R.drawable.ic_poll_white_24dp);
                    floatingActionButton1.hide();
                    floatingActionButton2.hide();
                    floatingActionButton3.hide();
                } else {
                    floatingActionButton.setImageResource(R.drawable.ic_clear_white_24dp);
                    floatingActionButton1.show();
                    floatingActionButton2.show();
                    floatingActionButton3.show();
                }
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB MINI 1 CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB MINI 2 CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB MINI 3 CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new HRISGenderFragment(), "Gender");
        adapter.addFragment(new HRISAgeFragment(), "Age");
        adapter.addFragment(new HRISDepartmentFragment(), "Department");
        adapter.addFragment(new HRISStatusFragment(), "Status");
        adapter.addFragment(new HRISHiringFragment(), "Hiring");
        adapter.addFragment(new HRISResignationFragment(), "Resignation");

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int posTab;

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                this.posTab = position;


                switch (position){
                    case 4:
                    case 5:
                       /* if(floatingActionButton.getVisibility() == View.VISIBLE)
                            floatingActionButton.hide();*/

                        floatingActionButton.setTranslationY(floatingActionButton.getTranslationY() * positionOffset);

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> mFragmentList = new ArrayList<>();
        List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(BaseFragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
