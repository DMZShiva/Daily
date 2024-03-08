package com.example.watersystem.personal.type;

public class PersonalNewVersionType extends PersonalCenterItemType{

    private boolean isShow = false;

    public PersonalNewVersionType(String itemKey, String itemValue,boolean isShow) {
        super(itemKey, itemValue);
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Override
    public int getItemType() {
        return PersonalCenterItemType.PERSONAL_CENTER_NEW_VERSION;
    }
}
