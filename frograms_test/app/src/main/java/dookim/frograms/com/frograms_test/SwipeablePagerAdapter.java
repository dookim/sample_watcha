package dookim.frograms.com.frograms_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by dookim on 10/8/15.
 */
public class SwipeablePagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_SIZE = 4;


    public SwipeablePagerAdapter(FragmentManager fm) {
        super(fm);
        Log.e("dookim", "SwipeablePagerAdapter");
    }


    @Override
    public Fragment getItem(int i) {
        Log.e("dookim", "getItem : " + i);
        Fragment fragment = null;


        if(i == 0) {
            fragment = new ChildFragment1();
        } else if(i == 1) {
            fragment = new ChildFragment2();
        }  else if(i == 2) {
            fragment = new ChildFragment3();
        } else if(i == 3) {
            fragment = new ChildFragment4();
        }

        if(fragment == null) {
            throw new IllegalStateException("fragment cannot be null");
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_SIZE;
    }
}
