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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.adapters.DragListener;
import com.android.productassemblerapp.adapters.PartsGridListAdapter;
import com.android.productassemblerapp.adapters.PartListAdapterType;
import com.android.productassemblerapp.adapters.PartsListAdapter;
import com.android.productassemblerapp.databinding.FragmentScreen3Binding;
import com.android.productassemblerapp.viewmodels.MainViewModel;

public class ScreenThree extends Fragment {

    private FragmentScreen3Binding binding;

    private MainViewModel mainViewModel;
    private PartsListAdapter selectedPartsListAdapter;
    private PartsGridListAdapter partItemGridListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScreen3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        setUpSelectedPartsRecyclerView();
        setUpDraggedPartsRecyclerView();

        binding.btnNext.setOnClickListener(v -> {
            if (partItemGridListAdapter != null) {
                mainViewModel.setAssembledPartItemsList(partItemGridListAdapter.getPartItemList());
            }
            Navigation.findNavController(view).navigate(R.id.action_screenThree_to_screenFour);
        });
    }

    private void setUpSelectedPartsRecyclerView() {
        selectedPartsListAdapter = new PartsListAdapter();
        selectedPartsListAdapter.setPartItemList(mainViewModel.getSelectedPartItemsList());
        selectedPartsListAdapter.setType(PartListAdapterType.UN_SELECTABLE);
        binding.rvSelectedParts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvSelectedParts.setAdapter(selectedPartsListAdapter);
    }

    private void setUpDraggedPartsRecyclerView() {
        partItemGridListAdapter = new PartsGridListAdapter();
        binding.rvDraggedParts.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        binding.rvDraggedParts.setAdapter(partItemGridListAdapter);
        binding.rvDraggedParts.setOnDragListener(new DragListener());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
