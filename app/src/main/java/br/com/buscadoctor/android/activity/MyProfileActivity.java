package br.com.buscadoctor.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.adapter.MyProfileTabsAdapter;
import io.fabric.sdk.android.Fabric;

public class MyProfileActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "CkWEqjxfH4tdjfw6L1NQvDZWT";
    private static final String TWITTER_SECRET = "MTnjeOWYh2aZB3JKPyNumRLOEcrXGwIksQGrgDEzFpTS6Hebx9";

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent intent = new Intent(MyProfileActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_my_consultas:
                            break;

                        case R.id.navigation_perfil:
                            break;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_my_profile);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_activity_profile);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_tabs);

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.title_profile_user));
        titles.add(getString(R.string.title_profile_convenio));
        titles.add(getString(R.string.title_profile_favoritos));

        final MyProfileTabsAdapter profileTabsAdapter = new MyProfileTabsAdapter(getSupportFragmentManager(), titles);

        viewPager.setAdapter(profileTabsAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}