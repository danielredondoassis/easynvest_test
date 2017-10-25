package com.test.easynvest.data.event;

import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;

/**
 * Created by DT on 10/25/17.
 */

public class ShowFieldErrorEvent {

    private TypeField typeField;
    private Type type;
    private String errorMessage;

    public ShowFieldErrorEvent(Type type, TypeField typeField, String errorMessage){
        this.type = type;
        this.typeField = typeField;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Type type,TypeField typeField) {
        return this.type == type && this.typeField == typeField ? errorMessage : null;
    }
}
