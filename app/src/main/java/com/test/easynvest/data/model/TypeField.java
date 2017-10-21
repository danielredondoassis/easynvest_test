package com.test.easynvest.data.model;

/**
 * Created by DT on 10/21/17.
 */

public enum TypeField {

    TEXT(1),
    TEL_NUMBER(2),
    EMAIL(3);

    private final int type;

    TypeField(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static TypeField getEnumForId(long id) {
        for(TypeField typefield : TypeField.values()){
            if(typefield.getType() == id){
                return typefield;
            }
        }
        return TEXT;
    }
}