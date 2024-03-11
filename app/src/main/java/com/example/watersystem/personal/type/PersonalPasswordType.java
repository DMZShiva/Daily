package com.example.watersystem.personal.type;

public class PersonalPasswordType extends PersonalCenterItemType{

    private boolean isPlain = false;

    public PersonalPasswordType(String itemKey, String itemValue) {
        super(itemKey, itemValue);
    }

    public boolean isPlain() {
        return isPlain;
    }

    public void setPlain(boolean plain) {
        isPlain = plain;
    }

    @Override
    public int getItemType() {
        return PersonalCenterItemType.PERSONAL_CENTER_ITEM_PASSWORD;
    }
}
