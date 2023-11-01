package com.android.productassemblerapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.databinding.FragmentScreen1Binding;

public class ScreenOne extends Fragment {

    private FragmentScreen1Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScreen1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivProductImage.setImageResource(R.drawable.desktop_computer);
        binding.tvProductDescription.setText("This is a desktop computer");

        binding.btnStart.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_screenOne_to_screenTwo);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
