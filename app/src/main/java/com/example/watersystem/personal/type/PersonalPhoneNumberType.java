package com.example.watersystem.personal.type;

public class PersonalPhoneNumberType extends PersonalCenterItemType{

    private boolean isPlain = false;

    public PersonalPhoneNumberType(String key, String value) {
        super(key, value);
    }

    public boolean isPlain() {
        return isPlain;
    }

    public void setPlain(boolean plain) {
        isPlain = plain;
    }

    @Override
    public int getItemType() {
        return PersonalCenterItemType.PERSONAL_CENTER_ITEM_PHONE;
    }
}
