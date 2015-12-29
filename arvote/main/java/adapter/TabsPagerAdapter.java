package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import arvote.yhtosoft.kisese.com.arvote.DriversFragment;
import arvote.yhtosoft.kisese.com.arvote.OptionsFragment;
import arvote.yhtosoft.kisese.com.arvote.RulesFragment;
import arvote.yhtosoft.kisese.com.arvote.StandingsFragment;
import arvote.yhtosoft.kisese.com.arvote.VoteFragment;


/**
 * Created by Brayo on 8/12/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index){
            case 0:
                return new VoteFragment();
            case 1:
                return new DriversFragment();
            case 2:
                return new StandingsFragment();
            case 3:
                return new RulesFragment();
            case 4:
                return new OptionsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        //get item  count - equal to number of tabs
        return 5;
    }
}
