package com.example.week11.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.week11.fragments.MatchesFragment;
import com.example.week11.fragments.PlayersFragment;
import com.example.week11.fragments.TeamsFragment;

public class SoccerPagerAdapter extends FragmentStateAdapter {
    public SoccerPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TeamsFragment();
            case 1:
                return new PlayersFragment();
            case 2:
                return new MatchesFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}