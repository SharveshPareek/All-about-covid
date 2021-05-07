package com.psss.homequrantine.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.homequrantine.AfftectedCovid.Ac_self_diagnosis_fragpage1;
import com.psss.homequrantine.AfftectedCovid.Ac_self_diagnosis_fragpage2;
import com.psss.homequrantine.AfftectedCovid.Ac_self_diagnosis_fragpage3;

public class Ac_self_diagnosis_fragadapter extends FragmentPagerAdapter {
    public Ac_self_diagnosis_fragadapter(
        FragmentManager fm)
{
    super(fm);
}

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0) {
                return new Ac_self_diagnosis_fragpage1();
            }
            else if (position == 1){
                return  new Ac_self_diagnosis_fragpage2();
            }
            else {
                return new Ac_self_diagnosis_fragpage3();
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }
}

