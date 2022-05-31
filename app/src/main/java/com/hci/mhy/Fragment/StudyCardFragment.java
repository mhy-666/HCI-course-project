package com.hci.mhy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.uit.databinding.FragmentStudyCardBinding;


public class StudyCardFragment extends Fragment {

    private FragmentStudyCardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudyCardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}