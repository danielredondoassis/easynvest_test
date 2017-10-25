package com.test.easynvest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.adapter.SignUpAdapter;
import com.test.easynvest.view.presenter.SignUpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpContracts.View {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private SignUpActivity mContext;
    private SignUpAdapter mAdapter;

    private SignUpContracts.Presenter mPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO salvar no saved instance esse usuário para eventual reconstrução da tela;
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        this.mContext = this;
        mPresenter = new SignUpPresenter(this);
    }

    @Override
    public void setupAdapter(ArrayList<CellModel> result) {
        this.mAdapter = new SignUpAdapter(this,mPresenter.getInteractor(),result);
        this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
        this.mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showNoSignUpScreen() {

    }

}
