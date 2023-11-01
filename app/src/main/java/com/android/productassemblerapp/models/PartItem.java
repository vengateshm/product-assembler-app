package com.android.productassemblerapp.models;

public class PartItem {
    private String partName;
    private int partImageRes;
    public String selectedColor;

    public PartItem() {
    }

    public PartItem(String partName, int partImageRes) {
        this.partName = partName;
        this.partImageRes = partImageRes;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getPartImageRes() {
        return partImageRes;
    }

    public void setPartImageRes(int partImageRes) {
        this.partImageRes = partImageRes;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }
}
