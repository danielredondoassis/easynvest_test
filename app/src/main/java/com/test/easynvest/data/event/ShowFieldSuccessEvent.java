package com.test.easynvest.data.event;

import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;

/**
 * Created by DT on 10/25/17.
 */

public class ShowFieldSuccessEvent {

    private TypeField typeField;
    private Type type;

    public ShowFieldSuccessEvent(Type type, TypeField typeField){
        this.type = type;
        this.typeField = typeField;
    }


    public TypeField getTypeField() {
        return typeField;
    }

    public Type getType() {
        return type;
    }
}
