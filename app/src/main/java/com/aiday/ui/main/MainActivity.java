package com.aiday.ui.main;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiday.R;
import com.aiday.ui.base.BaseActivity;
import com.aiday.ui.callLog.CallLogFragment;
import com.aiday.ui.contact.ContactFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainInterface.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.logout)
    LinearLayout mLogout;

    private static final int PAGE_SIDE_RETAINED = 1;

    private static final int[] PICTURE = new int[]{
            R.drawable.ic_message_grey,
            R.drawable.ic_call_grey,
            R.drawable.ic_contact_grey};

    private static final int[] PICTURE_CLICK = new int[]{
            R.drawable.ic_message_green,
            R.drawable.ic_call_green,
            R.drawable.ic_contact_green};

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getActivityPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initialize() {
        mActivityPresenter.loadData();
        initDrawer();
        initBottomTabLayout();
        mLogout.setOnClickListener(v -> mActivityPresenter.logOut(MainActivity.this));
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            mActivityPresenter.onDrawerItemClick(menuItem);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void initBottomTabLayout() {

        final ArrayList<Fragment> mTabFragments = new ArrayList<>();

        mTabFragments.add(new CallLogFragment());
        mTabFragments.add(new CallLogFragment());
        mTabFragments.add(new ContactFragment());

        final List<String> mTabTitles = Arrays.asList(getResources().getStringArray(R.array.main_fragment_title));

        FragmentStatePagerAdapter mMainPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mTabFragments.get(i);
            }

            @Override
            public int getCount() {
                return mTabFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.get(position);
            }
        };
        mViewPager.setAdapter(mMainPageAdapter);
        mViewPager.setOffscreenPageLimit(PAGE_SIDE_RETAINED);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(R.layout.simple_pager_title_layout);
            mTabLayout.getTabAt(i).setIcon(PICTURE[i]);
            ((TextView) mTabLayout.getTabAt(i).getCustomView().findViewById(android.R.id.title)).setText(mTabTitles.get(i));
        }

        setTabMode(mTabLayout.getTabAt(0), true);

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabMode(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabMode(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setTabMode(TabLayout.Tab tab, boolean isSelected) {
        int textNormal = getResources().getColor(android.R.color.darker_gray);
        int textClicked = getResources().getColor(android.R.color.holo_blue_light);
        tab.setIcon(isSelected ? PICTURE_CLICK[tab.getPosition()] : PICTURE[tab.getPosition()]);
        tab.getCustomView().findViewById(android.R.id.icon).setScaleX(isSelected ? 1.5f : 1.0f);
        tab.getCustomView().findViewById(android.R.id.icon).setScaleY(isSelected ? 1.5f : 1.0f);
        ((TextView) mTabLayout.getTabAt(tab.getPosition()).getCustomView().findViewById(android.R.id.title)).setTextColor(isSelected ? textClicked : textNormal);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
