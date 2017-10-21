package com.test.easynvest.data.model;

import com.test.easynvest.view.BaseActivity;

/**
 * Created by DT on 10/21/17.
 */

public enum Type {
    FIELD(1),
    TEXT(2),
    IMAGE(3),
    CHECKBOX(4),
    SEND(5);

    private final int type;

    Type(int type) {
        this.type = type;
    }

    public static Type getEnumForId(long id) {
        for(Type type : Type.values()){
            if(type.getType() == id){
                return type;
            }
        }
        return FIELD;
    }

    public int getType() {
        return type;
    }
}