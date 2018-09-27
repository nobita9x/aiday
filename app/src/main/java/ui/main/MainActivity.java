package ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aiday.com.aiday.R;
import butterknife.BindView;
import ui.base.BaseActivity;
import ui.callLog.CallLogFragment;
import ui.sms.SmsFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainInterface.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    private static final int PAGE_SIDE_RETAINED = 1;

    private static final int[] PICTURE = new int[]{
            R.drawable.sms,
            R.drawable.call_log,
            R.drawable.contact};

    private static final int[] PICTURE_CLICK = new int[]{
            R.drawable.sms_click,
            R.drawable.call_log_click,
            R.drawable.contact_click};

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
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_item1:
                        menuItem1Clicked();
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

            private void menuItem1Clicked() {
                Toast.makeText(getApplicationContext(), "NavigationView menu1 click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBottomTabLayout() {

        final ArrayList<Fragment> mTabFragments = new ArrayList<>();

        mTabFragments.add(new SmsFragment());
        mTabFragments.add(new CallLogFragment());
        mTabFragments.add(new SmsFragment());

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
