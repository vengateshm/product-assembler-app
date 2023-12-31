package com.android.productassemblerapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.productassemblerapp.models.PartItem;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private List<PartItem> selectedPartItemsList = new ArrayList<>();

    public void setSelectedPartItemsList(List<PartItem> selectedPartItemsList) {
        this.selectedPartItemsList = selectedPartItemsList;
    }

    public List<PartItem> getSelectedPartItemsList() {
        return selectedPartItemsList;
    }
}
