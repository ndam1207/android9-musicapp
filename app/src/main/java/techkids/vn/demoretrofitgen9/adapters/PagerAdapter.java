package techkids.vn.demoretrofitgen9.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import techkids.vn.demoretrofitgen9.fragments.DownloadFragment;
import techkids.vn.demoretrofitgen9.fragments.FavouriteFragment;
import techkids.vn.demoretrofitgen9.fragments.MusicTypesFragment;

/**
 * Created by Admins on 7/19/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MusicTypesFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new DownloadFragment();
            default:
                return new MusicTypesFragment();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
