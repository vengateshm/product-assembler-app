package com.android.productassemblerapp.adapters;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.productassemblerapp.databinding.PartListItemBinding;
import com.android.productassemblerapp.models.PartItem;

import java.util.ArrayList;
import java.util.List;

public class PartsListAdapter extends RecyclerView.Adapter<PartsListAdapter.PartItemViewHolder> implements View.OnTouchListener {

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

    public List<PartItem> getPartItemList() {
        return partItemList;
    }

    @NonNull
    @Override
    public PartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PartListItemBinding binding = PartListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PartItemViewHolder(binding, type, this);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public void onBindViewHolder(@NonNull PartItemViewHolder holder, int position) {
        holder.bind(partItemList.get(position));
        holder.binding.cvRoot.setTag(position);
        holder.binding.cvRoot.setOnTouchListener(this);
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

    public PartItem getItemAt(int adapterPosition) {
        return partItemList.get(adapterPosition);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(clipData, dragShadowBuilder, v, 0);
            return true;
        }
        return false;
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
