package br.com.buscadoctor.android.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.fragment.MyConveniosFragment;
import br.com.buscadoctor.android.fragment.MyFavoritosFragment;
import br.com.buscadoctor.android.fragment.MyProfileFragment;

/**
 * @author Andre
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyProfileTabsAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>();

    public MyProfileTabsAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        if (position == 0)
            frag = new MyProfileFragment();

        if (position == 1)
            frag = new MyConveniosFragment();

        if (position == 2)
            frag = new MyFavoritosFragment();


        Bundle b = new Bundle();
        b.putInt("position", position);

        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}