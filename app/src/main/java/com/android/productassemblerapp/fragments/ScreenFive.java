package com.android.productassemblerapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.databinding.FragmentScreen5Binding;
import com.android.productassemblerapp.models.PartItem;
import com.android.productassemblerapp.viewmodels.MainViewModel;

import java.util.List;


public class ScreenFive extends Fragment {

    private FragmentScreen5Binding binding;
    private MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScreen5Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.btnFinish.setOnClickListener(v -> {
            requireActivity().finish();
        });

        setUpFinalProductView();
    }

    private void setUpFinalProductView() {
        List<PartItem> partItems = mainViewModel.getAssembledPartItemsList();
        for (PartItem partItem : partItems) {
            View view = createPartItemWithColorView(partItem);
            binding.llImageContainer.addView(view);
        }
    }

    private View createPartItemWithColorView(PartItem partItem) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.part_item_with_color, null);
        if (!partItem.getSelectedColor().isEmpty()) {
            LinearLayout llPICColorRoot = view.findViewById(R.id.llPICColorRoot);
            llPICColorRoot.setBackgroundColor(Color.parseColor(partItem.getSelectedColor()));
        }
        ImageView imageView = view.findViewById(R.id.ivImage);
        imageView.setImageResource(partItem.getPartImageRes());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
