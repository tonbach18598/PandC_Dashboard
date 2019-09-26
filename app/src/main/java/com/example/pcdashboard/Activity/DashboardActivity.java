package com.example.pcdashboard.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pcdashboard.Adapter.PagerAdapter;
import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ChatFragment;
import com.example.pcdashboard.Fragment.ClassroomFragment;
import com.example.pcdashboard.Fragment.DepartmentFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }

    private void initialize() {
        screenManager = ScreenManager.getInstance();
        screenManager.setScreenManager(this);
        viewPager = findViewById(R.id.view_pager_dashboard);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, screenManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout = findViewById(R.id.tab_layout_dashboard);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(pagerAdapter.initTab(i));
            tabLayout.getTabAt(0).setCustomView(pagerAdapter.selectTab(tabLayout.getTabAt(0).getCustomView(), tabLayout.getTabAt(0).getPosition(), true));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setCustomView(pagerAdapter.selectTab(tab.getCustomView(), tab.getPosition(), true));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(pagerAdapter.selectTab(tab.getCustomView(), tab.getPosition(), false));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void openLoginScreen(String screenName) {
        //NULL
    }

    @Override
    public Fragment openDashboardScreen(String screenName) {
        Fragment fragment = null;
        switch (screenName) {
            case DEPARTMENT_FRAGMENT:
                fragment = new DepartmentFragment();
                break;
            case CLASSROOM_FRAGMENT:
                fragment = new ClassroomFragment();
                break;
            case CHAT_FRAGMENT:
                fragment = new ChatFragment();
                break;
            case ACCOUNT_FRAGMENT:
                fragment = new AccountFragment();
                break;
        }
        return fragment;
    }
}
