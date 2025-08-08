package com.example.jsonplaceholderappnew;

import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class FragmentMngr
{
    FragmentManager fragmentManager;

    public FragmentMngr(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    public void removeFragment()
    {
        fragmentManager.beginTransaction()
                .remove(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.frame_layout)))
                .commit();
    }
}
