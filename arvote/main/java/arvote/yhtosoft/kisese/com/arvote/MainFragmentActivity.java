package arvote.yhtosoft.kisese.com.arvote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import adapter.TabsPagerAdapter;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;


public class MainFragmentActivity extends ActionBarActivity implements ActionBar.TabListener{

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;

    private String[] tabs = {"Vote", "Drivers", "Standings", "Rules", "Options"};
    private SharedPreferences users;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        users = getSharedPreferences("user", Context.MODE_PRIVATE);
        user = users.getString("valid_user", "");

        //initialization
        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Adding Tabs
        for(String tab_name : tabs){
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //add the custom view to the actionBar
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setIcon(R.drawable.vipi_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.getThemedContext();
        actionBar.show();
    }




    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(user.isEmpty()){
            Intent intent = new Intent(MainFragmentActivity.this, SignInActivity.class);
            MainFragmentActivity.this.startActivity(intent);
        }else{
            Intent intent = new Intent(MainFragmentActivity.this, MainFragmentActivity.class);
            MainFragmentActivity.this.startActivity(intent);
        }
    }
}
