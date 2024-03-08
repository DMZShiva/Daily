package com.example.watersystem.personal.type;

public class PersonalNameType extends PersonalCenterItemType{

    public PersonalNameType(String key, String value) {
        super(key, value);
    }

    @Override
    public int getItemType() {
        return PersonalCenterItemType.PERSONAL_CENTER_ITEM_NAME;
    }
}
