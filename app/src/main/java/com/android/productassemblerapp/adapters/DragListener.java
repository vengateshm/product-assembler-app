package com.android.productassemblerapp.adapters;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.productassemblerapp.R;
import com.android.productassemblerapp.models.PartItem;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();
            final int cvRoot = R.id.cvRoot;
            final int ivImage = R.id.ivImage;
            final int rvSelectedParts = R.id.rvSelectedParts;
            final int rvDraggedParts = R.id.rvDraggedParts;

            if (viewId == cvRoot || viewId == ivImage || viewId == rvSelectedParts || viewId == rvDraggedParts) {
                RecyclerView target;
                if (viewId == rvSelectedParts) {
                    target = (RecyclerView) view.getRootView().findViewById(rvSelectedParts);
                } else if (viewId == rvDraggedParts) {
                    target = (RecyclerView) view.getRootView().findViewById(rvDraggedParts);
                } else {
                    target = (RecyclerView) view.getParent();
                    positionTarget = (int) view.getTag();
                }

                if (viewSource != null) {
                    RecyclerView source = (RecyclerView) viewSource.getParent();

                    PartsListAdapter adapterSource = (PartsListAdapter) source.getAdapter();
                    int positionSource = (int) viewSource.getTag();

                    PartItem partItem = adapterSource.getPartItemList().get(positionSource);

                    PartsGridListAdapter adapterTarget = (PartsGridListAdapter) target.getAdapter();
                    List<PartItem> customListTarget = adapterTarget.getPartItemList();
                    if (!customListTarget.contains(partItem)) {
                        if (positionTarget >= 0) {
                            customListTarget.add(positionTarget, partItem);
                        } else {
                            customListTarget.add(partItem);
                        }
                        adapterTarget.updateList(customListTarget);
                        adapterTarget.notifyDataSetChanged();
                    }
                }
            }
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}