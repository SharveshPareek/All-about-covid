package com.psss.homequrantine.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.homequrantine.RecoveryCovid.Rc_exercise_fragpage1;
import com.psss.homequrantine.RecoveryCovid.Rc_exercise_fragpage2;
import com.psss.homequrantine.RecoveryCovid.Rc_exercise_fragpage3;

public class Rc_exercise_fragadapter extends FragmentPagerAdapter {
    public Rc_exercise_fragadapter(
            FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0) {
            return new Rc_exercise_fragpage1();
        }
        else if (position == 1){
            return  new Rc_exercise_fragpage2();
        }
        else {
            return new Rc_exercise_fragpage3();
        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}

