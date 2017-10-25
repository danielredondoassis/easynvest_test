package com.test.easynvest.data.model;

/**
 * Created by DT on 10/21/17.
 */

public enum TypeField {

    TEXT(1,"text"),
    PHONE(2,"telnumber"),
    EMAIL(3,"email");

    private int type;
    private String typeName;

    TypeField(int typeId, String typeName) {
        this.type = typeId;
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }


    public static TypeField getEnumForName(String name) {
        for(TypeField typefield : TypeField.values()){
            if(typefield.getTypeName().equals(name)){
                return typefield;
            }
        }
        return null;
    }

    public static TypeField getEnumForId(int id) {
        for(TypeField typefield : TypeField.values()){
            if(typefield.getType() == id){
                return typefield;
            }
        }
        return null;
    }
}