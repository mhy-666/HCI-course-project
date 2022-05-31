package com.hci.mhy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.uit.databinding.FragmentCenterBinding;

public class CenterFragment extends Fragment {
    private FragmentCenterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCenterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}