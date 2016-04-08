package ph.com.swak.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.util.MaterialDrawerLayout;
import it.neokree.materialnavigationdrawer.util.Utils;
import ph.com.swak.R;
import ph.com.swak.fragment.BasicFragment;
import ph.com.swak.fragment.BulletinFragment;
import ph.com.swak.fragment.ChangePassFragment;
import ph.com.swak.fragment.hris.HRISFragment;
import ph.com.swak.fragment.LeaveFragment;
import ph.com.swak.fragment.OTNDFragment;
import ph.com.swak.fragment.PayHistoryFragment;
import ph.com.swak.fragment.SignOutFragment;
import ph.com.swak.model.Employee;
import ph.com.swak.utils.Constants;
import ph.com.swak.utils.Util;
import ph.com.swak.view.CircularImageView;

/**
 * Created by SWAK-THREE on 4/8/2015.
 */
public class MainActivity extends MaterialNavigationDrawer  {

    MaterialAccount account;

    @InjectView(R.id.imgAvatar)
    public CircularImageView avatarContainer;

    @InjectView(R.id.txtFullname)
    public TextView txtFullname;

    @InjectView(R.id.txtDesignation)
    public TextView txtDesig;

    @Override
    public void init(Bundle bundle) {

        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        ButterKnife.inject(this, view);
        setUserData(view);
        setDrawerHeaderCustom(view);

        addSection(newSection("Bulletin", R.drawable.ic_action_assignment, new BulletinFragment()));
        addSection(newSection("Basic Information", R.drawable.ic_menu_basic, new BasicFragment()));
        addSection(newSection("File Leave", R.drawable.ic_menu_file_leave, new LeaveFragment()));

        addSection(newSection("File OT / ND", R.drawable.ic_menu_file_ot, new OTNDFragment()));
        addSection(newSection("Pay History", R.drawable.ic_menu_pay_history, new PayHistoryFragment()));
        addSection(newSection("HRIS", R.drawable.ic_action_accept, new HRISFragment()));

        addBottomSection(newSection("Change Password", R.drawable.ic_menu_change_pass, new ChangePassFragment()));
        addBottomSection(newSection("Sign Out", R.drawable.ic_action_highlight_remove, new SignOutFragment()));

        allowArrowAnimation();

        fixMultipaneBug();
    }

    public void setUserData(View view) {
        txtFullname.setText(Employee.getEmployeeInfo().getFullname());
        txtDesig.setText(Employee.getEmployeeInfo().getDesignation());
        Util.getInstance().displayImage(Constants.URL + Employee.getEmployeeInfo().getProf_pic(), avatarContainer, Util.imageLoadingListener);
    }

    private void fixMultipaneBug(){
        if(Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation && Utils.isTablet(getResources())) {
            final MaterialDrawerLayout newLayout = (MaterialDrawerLayout) findViewById(R.id.drawer_layout);

            newLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {}

                @Override
                public void onDrawerOpened(View drawerView) {}

                @Override
                public void onDrawerClosed(View drawerView) {
                    newLayout.openDrawer(drawerView);
                }

                @Override
                public void onDrawerStateChanged(int newState) {}
            });
        }
    }
}
