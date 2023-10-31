package com.android.productassemblerapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.adapters.PartListAdapterType;
import com.android.productassemblerapp.adapters.PartsListAdapter;
import com.android.productassemblerapp.databinding.FragmentScreen2Binding;
import com.android.productassemblerapp.models.PartItem;
import com.android.productassemblerapp.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScreenTwo extends Fragment {

    private FragmentScreen2Binding binding;

    private PartsListAdapter partsListAdapter;
    private MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScreen2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        partsListAdapter = new PartsListAdapter();
        partsListAdapter.setType(PartListAdapterType.SELECTABLE);

        List<PartItem> partItemList = new ArrayList<>();
        partItemList.add(new PartItem("Part1", R.drawable.ic_image));
        partItemList.add(new PartItem("Part2", R.drawable.ic_image));
        partItemList.add(new PartItem("Part3", R.drawable.ic_image));
        partItemList.add(new PartItem("Part4", R.drawable.ic_image));
        partItemList.add(new PartItem("Part5", R.drawable.ic_image));

        partsListAdapter.setPartItemList(partItemList);

        binding.rvParts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvParts.setAdapter(partsListAdapter);

        binding.btnNext.setOnClickListener(v -> {
            mainViewModel.setSelectedPartItemsList(partsListAdapter.getSelectedPartItemList());
            Navigation.findNavController(view).navigate(R.id.action_screenTwo_to_screenThree);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
