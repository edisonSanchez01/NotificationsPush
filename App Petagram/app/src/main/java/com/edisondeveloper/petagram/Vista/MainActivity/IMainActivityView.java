package com.edisondeveloper.petagram.Vista.MainActivity;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public interface IMainActivityView {

    void configViewPagerAdapter(ArrayList<Fragment> fragments);
}
