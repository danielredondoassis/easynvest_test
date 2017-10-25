package com.test.easynvest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;

import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.view.adapter.FundAdapter;
import com.test.easynvest.view.contract.FundContracts;
import com.test.easynvest.view.presenter.FundPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FundActivity extends BaseActivity implements FundContracts.View {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private FundAdapter mAdapter;
    private FundPresenter mPresenter;
    private FundActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO salvar no saved instance esse usuário para eventual reconstrução da tela;
        setContentView(R.layout.activity_fund);
        ButterKnife.bind(this);
        //tintActionBar(R.color.white);
        //tintStatusBar();
        this.context = this;
        mPresenter = new FundPresenter(this);

        /* Tomei a liberdade de alterar um pouco o layout dessa tela levando a Tab para a parte de cima do App.
        *  os patterns do Android/Material Design utilizam o Tab Layout na parte superior da tela, abaixo da action bar.
        *  Além de mais prático e seguro por usar layouts padrão do OS, nos poupa tempo de desenvolver uma simples view
        *  e reinventar a roda por assim dizer.
        */
    }

    @Override
    public void setupAdapter(FundModel result) {
        mViewpager.setAdapter(new TabsAdapter(context, getSupportFragmentManager(), result));
        mTabs.setupWithViewPager(mViewpager);
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {}

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private static class TabsAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT = 2;
        private FundModel mFundModel;
        private Context context;

        TabsAdapter(Context context, FragmentManager fm,FundModel result) {
            super(fm);
            this.context = context;
            this.mFundModel = result;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? FundFragment.newInstance(mFundModel) : ContactFragment.newInstance() ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? context.getResources().getString(R.string.tab_investment) : context.getResources().getString(R.string.tab_contact);
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showNoFundScreen() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
