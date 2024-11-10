package com.example.trabalho;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.trabalho.fragments.CityListFragment;
import com.example.trabalho.fragments.MapFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Alterna entre o primeiro e o segundo fragment
        switch (position) {
            case 0:
                return new CityListFragment(); // Primeiro fragment
            case 1:
                return new MapFragment(); // Segundo fragment (vazio por enquanto)
            default:
                return new CityListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Quantidade de abas (2)
    }
}
