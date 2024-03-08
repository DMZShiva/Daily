package com.example.watersystem.personal.type;

public abstract class PersonalCenterItemType {

    public static final int PERSONAL_CENTER_ITEM_NAME = 1;
    public static final int PERSONAL_CENTER_ITEM_ID = 2;
    public static final int PERSONAL_CENTER_ITEM_PASSWORD = 3;
    public static final int PERSONAL_CENTER_ITEM_PHONE = 4;
    public static final int PERSONAL_CENTER_NEW_VERSION = 5;

    private String itemKey;
    private String itemValue;

    public PersonalCenterItemType(String itemKey, String itemValue) {
        this.itemKey = itemKey;
        this.itemValue = itemValue;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public abstract int getItemType();
}
