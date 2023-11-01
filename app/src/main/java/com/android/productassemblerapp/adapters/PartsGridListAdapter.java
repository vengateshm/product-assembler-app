package com.android.productassemblerapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.productassemblerapp.databinding.PartGridItemBinding;
import com.android.productassemblerapp.models.PartItem;

import java.util.ArrayList;
import java.util.List;

public class PartsGridListAdapter extends RecyclerView.Adapter<PartsGridListAdapter.PartItemGridVH> {
    private List<PartItem> partItemList = new ArrayList<>();

    public List<PartItem> getPartItemList() {
        return partItemList;
    }

    @NonNull
    @Override
    public PartItemGridVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PartGridItemBinding binding = PartGridItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PartItemGridVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PartItemGridVH holder, int position) {
        holder.bind(partItemList.get(position));
        holder.binding.ivImage.setOnDragListener(new DragListener());
    }

    @Override
    public int getItemCount() {
        return partItemList.size();
    }

    public void updateList(List<PartItem> customListTarget) {
        partItemList = customListTarget;
    }

    static class PartItemGridVH extends RecyclerView.ViewHolder {

        private PartGridItemBinding binding;

        public PartItemGridVH(PartGridItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PartItem partItem) {
            binding.ivImage.setImageResource(partItem.getPartImageRes());
        }
    }
}
