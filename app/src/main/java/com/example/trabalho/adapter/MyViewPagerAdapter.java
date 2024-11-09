package com.example.trabalho.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter{

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FirstTabFragment(); // Primeira aba
        } else {
            return new SecondTabFragment(); // Segunda aba
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Número de abas
    }

}
