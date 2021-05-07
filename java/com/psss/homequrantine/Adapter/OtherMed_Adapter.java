package com.psss.homequrantine.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.psss.homequrantine.OtherMedicines.Ayurvedic;
import com.psss.homequrantine.OtherMedicines.Homeopathy;
import com.psss.homequrantine.OtherMedicines.Sidha;

public class OtherMed_Adapter extends FragmentPagerAdapter {

    public OtherMed_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Ayurvedic();
        }
        else if (position == 1){
            return  new Sidha();
        }
        else {
            return new Homeopathy();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
