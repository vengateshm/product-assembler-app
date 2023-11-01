package com.android.productassemblerapp.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.databinding.FragmentScreen4Binding;
import com.android.productassemblerapp.models.PaletteColor;
import com.android.productassemblerapp.models.PartItem;
import com.android.productassemblerapp.viewmodels.MainViewModel;

import java.util.List;

public class ScreenFour extends Fragment {

    private FragmentScreen4Binding binding;
    private MainViewModel mainViewModel;
    private AlertDialog alertDialog;
    private String selectedPartName = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScreen4Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.btnNext.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_screenFour_to_screenFive);
        });

        setUpAssembledImagesList();
    }

    private void setUpAssembledImagesList() {
        List<PartItem> partItems = mainViewModel.getAssembledPartItemsList();
        for (PartItem partItem : partItems) {
            View view = createPartItemWithColorView(partItem);
            view.setTag(partItem);
            view.setOnClickListener(v -> {
                selectedPartName = ((PartItem) v.getTag()).getPartName();
                showAlertDialog();
            });
            binding.llImageContainer.addView(view);
        }
    }

    private View createPartItemWithColorView(PartItem partItem) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.part_item_with_color, null);
        ImageView imageView = view.findViewById(R.id.ivImage);
        imageView.setImageResource(partItem.getPartImageRes());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity()); // 'this' refers to your activity or context

        View customView = getLayoutInflater().inflate(R.layout.color_palette_dialog, null);

        List<PaletteColor> colors = PaletteColor.getColors();
        customView.findViewById(R.id.vColor1).setBackgroundColor(Color.parseColor(colors.get(0).getColor()));
        customView.findViewById(R.id.vColor1).setOnClickListener(v -> {
            onColorSelected(colors.get(0));
        });
        customView.findViewById(R.id.vColor2).setBackgroundColor(Color.parseColor(colors.get(1).getColor()));
        customView.findViewById(R.id.vColor2).setOnClickListener(v -> {
            onColorSelected(colors.get(1));
        });
        customView.findViewById(R.id.vColor3).setBackgroundColor(Color.parseColor(colors.get(2).getColor()));
        customView.findViewById(R.id.vColor3).setOnClickListener(v -> {
            onColorSelected(colors.get(2));
        });
        customView.findViewById(R.id.vColor4).setBackgroundColor(Color.parseColor(colors.get(3).getColor()));
        customView.findViewById(R.id.vColor4).setOnClickListener(v -> {
            onColorSelected(colors.get(3));
        });

        builder.setView(customView);

        builder.setTitle("Choose color");
        builder.setNegativeButton("CANCEL", (dialog, which) -> {
            dialog.dismiss();
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void onColorSelected(PaletteColor paletteColor) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        int count = binding.llImageContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            PartItem partItem = (PartItem) binding.llImageContainer.getChildAt(i).getTag();
            if (partItem.getPartName().equals(selectedPartName)) {
                binding.llImageContainer.getChildAt(i).setBackgroundColor(Color.parseColor(paletteColor.getColor()));
                partItem.setSelectedColor(paletteColor.getColor());
            }
        }
    }
}
