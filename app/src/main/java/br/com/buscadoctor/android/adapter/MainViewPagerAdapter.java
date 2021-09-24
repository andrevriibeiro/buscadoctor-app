package br.com.buscadoctor.android.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.fragment.MainTabFragment;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> mFragmentTitleList = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mFragmentTitleList = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        if (position == 0) {
            fragment = new MainTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tipo", position);
            fragment.setArguments(bundle);
        } else {
            fragment = new MainTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tipo", position);
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}