package com.psss.homequrantine.Adapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.homequrantine.BeforeCovid.Bc_precautions_fragpage1;
import com.psss.homequrantine.BeforeCovid.Bc_precautions_fragpage2;
import com.psss.homequrantine.BeforeCovid.Bc_precautions_fragpage3;

public class Bc_precaution_frag_pageadapter
        extends FragmentPagerAdapter {

    public Bc_precaution_frag_pageadapter(
            FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0) {
            return new Bc_precautions_fragpage1();
        }
        else if (position == 1){
            return  new Bc_precautions_fragpage2();
        }
        else {
            return new Bc_precautions_fragpage3();
        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
