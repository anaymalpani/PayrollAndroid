package ph.com.swak.fragment;

import android.app.Activity;

import ph.com.swak.activity.MainActivity;
import ph.com.swak.activity.SplashActivity;
import ph.com.swak.model.Employee;
import ph.com.swak.utils.Util;

/**
 * Created by admin on 3/18/2016.
 */
public class SignOutFragment extends BaseFragment {

    @Override
    public void onStart() {
        super.onStart();
        Employee.signout();
        Util.startActivity(getActivity(), SplashActivity.class, true);
    }
}
