package com.android.productassemblerapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.productassemblerapp.databinding.PartListItemBinding;
import com.android.productassemblerapp.models.PartItem;

import java.util.ArrayList;
import java.util.List;

public class PartsListAdapter extends RecyclerView.Adapter<PartsListAdapter.PartItemViewHolder> {

    private List<PartItem> partItemList = new ArrayList<>();
    private List<PartItem> selectedPartItemList = new ArrayList<>();
    private PartListAdapterType type;

    public void setPartItemList(List<PartItem> partItemList) {
        this.partItemList = partItemList;
    }

    public void setType(PartListAdapterType type) {
        this.type = type;
    }

    public List<PartItem> getSelectedPartItemList() {
        return selectedPartItemList;
    }

    @NonNull
    @Override
    public PartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PartListItemBinding binding = PartListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PartItemViewHolder(binding, type, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PartItemViewHolder holder, int position) {
        holder.bind(partItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return partItemList.size();
    }

    void onPartItemSelected(PartItem partItem, boolean isSelected) {
        if (isSelected)
            selectedPartItemList.add(partItem);
        else
            selectedPartItemList.remove(partItem);
    }

    static class PartItemViewHolder extends RecyclerView.ViewHolder {

        private PartListItemBinding binding;
        private PartsListAdapter adapter;

        public PartItemViewHolder(PartListItemBinding binding, PartListAdapterType type, PartsListAdapter adapter) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapter = adapter;

            if (type == PartListAdapterType.UN_SELECTABLE) {
                binding.cbSelect.setVisibility(View.GONE);
            } else {
                binding.cbSelect.setVisibility(View.VISIBLE);
            }
        }

        public void bind(PartItem partItem) {
            binding.ivPartImage.setImageResource(partItem.getPartImageRes());
            binding.tvPartName.setText(partItem.getPartName());
            binding.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> adapter.onPartItemSelected(partItem, isChecked));
        }
    }
}
