package techkids.vn.demoretrofitgen9.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import techkids.vn.demoretrofitgen9.R;

/**
 * Created by Admins on 7/19/2017.
 */

public class ScreenManager {
    public static void openFragment(FragmentManager fragmentManager,
                             Fragment fragment, int layoutID) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
