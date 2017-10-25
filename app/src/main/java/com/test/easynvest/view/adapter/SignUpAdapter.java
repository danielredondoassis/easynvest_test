package com.test.easynvest.view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.easynvest.R;
import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.adapter.holder.signup.ButtonHolder;
import com.test.easynvest.view.adapter.holder.signup.CheckBoxHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldEmailHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldHolder;
import com.test.easynvest.view.adapter.holder.signup.FieldPhoneHolder;
import com.test.easynvest.view.adapter.holder.signup.ImageHolder;
import com.test.easynvest.view.adapter.holder.signup.TextHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * Created by DT on 10/22/17.
 */

public class SignUpAdapter extends RecyclerView.Adapter {

    private SignUpContracts.Interactor mInteractor;
    private Context context;
    private WeakReference<AppCompatActivity> activity;
    private ArrayList<CellModel> mSource;

    private static final int TYPE_FIELD_EMAIL = 5;
    private static final int TYPE_FIELD_TEXT = 6;
    private static final int TYPE_FIELD_PHONE = 7;

    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;
    private static final int TYPE_CHECKBOX = 3;
    private static final int TYPE_SEND = 4;

    public SignUpAdapter(AppCompatActivity activity, SignUpContracts.Interactor interactor, ArrayList<CellModel> source) {
        this.activity = new WeakReference<>(activity);
        this.context = this.activity.get();
        this.mSource = source;
        this.mInteractor = interactor;
    }

    @Override
    public int getItemViewType(int position) {

        // Apesar de ser um formulário dinâmico,
        // são necessários IDs únicos nas views para realizar testes,
        // então essa validação do typeField é necessária nesse momento.

        CellModel cellModel = mSource.get(position);
        int type = cellModel.getType();

        TypeField typeField = null;
        if(cellModel.getTypefield() != null) {
            try {
                typeField = TypeField.getEnumForId(Integer.parseInt(cellModel.getTypefield()));
            } catch (NumberFormatException e) {
                try {
                    typeField = TypeField.getEnumForName(cellModel.getTypefield());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                e.printStackTrace();
            }
        }

        if (type == Type.FIELD.getType()) {
            if(typeField != null){
                if(typeField == TypeField.EMAIL){
                    return TYPE_FIELD_EMAIL;
                } else if(typeField == TypeField.PHONE){
                    return TYPE_FIELD_PHONE;
                }
            }
            return TYPE_FIELD_TEXT;
        } else if (type == Type.TEXT.getType()) {
            return TYPE_TEXT;
        } else if (type == Type.IMAGE.getType()) {
            return TYPE_IMAGE;
        } else if (type == Type.CHECKBOX.getType()) {
            return TYPE_CHECKBOX;
        } else if (type == Type.SEND.getType()) {
            return TYPE_SEND;
        }

        return TYPE_FIELD_TEXT;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_FIELD_PHONE:
                view = inflater.inflate(R.layout.vh_signup_edit_phone_field, parent, false);
                return new FieldPhoneHolder(view);
            case TYPE_FIELD_EMAIL:
                view = inflater.inflate(R.layout.vh_signup_edit_email_field, parent, false);
                return new FieldEmailHolder(view);
            case TYPE_FIELD_TEXT:
                view = inflater.inflate(R.layout.vh_signup_edit_text_field, parent, false);
                return new FieldHolder(view);
            case TYPE_TEXT:
                view = inflater.inflate(R.layout.vh_signup_text_field, parent, false);
                return new TextHolder(view);
            case TYPE_IMAGE:
                view = inflater.inflate(R.layout.vh_signup_image_field, parent, false);
                return new ImageHolder(view);
            case TYPE_CHECKBOX:
                view = inflater.inflate(R.layout.vh_signup_checkbox_field, parent, false);
                return new CheckBoxHolder(view);
            case TYPE_SEND:
                view = inflater.inflate(R.layout.vh_signup_button, parent, false);
                return new ButtonHolder(view);
            default:
                view = inflater.inflate(R.layout.vh_signup_text_field, parent, false);
                return new TextHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CellModel cellModel = mSource.get(position);
        switch (getItemViewType(position)) {
            case TYPE_FIELD_PHONE:
                FieldPhoneHolder fieldPhoneHolder = (FieldPhoneHolder) holder;
                fieldPhoneHolder.setup(context, mInteractor, cellModel);
                break;
            case TYPE_FIELD_EMAIL:
                FieldEmailHolder fieldEmailHolder = (FieldEmailHolder) holder;
                fieldEmailHolder.setup(context, mInteractor, cellModel);
                break;
            case TYPE_FIELD_TEXT:
                FieldHolder fieldHolder = (FieldHolder) holder;
                fieldHolder.setup(context, mInteractor, cellModel);
                break;
            case TYPE_TEXT:
                TextHolder textHolder = (TextHolder) holder;
                textHolder.setup(context, cellModel);
                break;
            case TYPE_IMAGE:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.setup(context, cellModel);
                break;
            case TYPE_CHECKBOX:
                CheckBoxHolder checkBoxHolder = (CheckBoxHolder) holder;
                checkBoxHolder.setup(context, mInteractor,cellModel);
                break;
            case TYPE_SEND:
                ButtonHolder buttonHolder = (ButtonHolder) holder;
                buttonHolder.setup(context, mInteractor, cellModel);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof FieldHolder) ((FieldHolder) holder).removeObserver();
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof FieldHolder) ((FieldHolder) holder).removeObserver();
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mSource.size();
    }
}
