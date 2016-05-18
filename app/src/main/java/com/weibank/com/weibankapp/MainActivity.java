package com.weibank.com.weibankapp;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.weibank.com.weibankapp.utils.AnimationMethods;

public class MainActivity extends TabActivity
                           implements RadioGroup.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        /**
         * 调用初始化方法
         */
        init();
//        AnimationMethods.setupWindowSlide(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.tabs_rg_shouye:

                tabHost.setCurrentTab(0);
                mTabsRgShouye.setTextColor(getResources().getColor(R.color.red));
                mTabsRgWo.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgHehuoren.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgShezhi.setTextColor(getResources().getColor(R.color.de_gray_white));
                break;
            case R.id.tabs_rg_wo:

                tabHost.setCurrentTab(1);
                mTabsRgShouye.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgWo.setTextColor(getResources().getColor(R.color.red));
                mTabsRgHehuoren.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgShezhi.setTextColor(getResources().getColor(R.color.de_gray_white));
                break;
            case R.id.tabs_rg_hehuoren:

                tabHost.setCurrentTab(2);
                mTabsRgShouye.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgWo.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgHehuoren.setTextColor(getResources().getColor(R.color.red));
                mTabsRgShezhi.setTextColor(getResources().getColor(R.color.de_gray_white));
                break;
            case R.id.tabs_rg_shezhi:

                tabHost.setCurrentTab(3);
                mTabsRgShouye.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgWo.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgHehuoren.setTextColor(getResources().getColor(R.color.de_gray_white));
                mTabsRgShezhi.setTextColor(getResources().getColor(R.color.red));
                break;
        }
    }
    /**
     * 初始化方法
     */
    private void init(){

        tabHost = this.getTabHost();
        tabSpec = tabHost.newTabSpec(SHOUYE)
                          .setIndicator(SHOUYE)
                          .setContent(new Intent(MainActivity.this, ShouYeActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(WO)
                           .setIndicator(WO)
                           .setContent(new Intent(MainActivity.this, WoActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(HEHUOREN)
                          .setIndicator(HEHUOREN)
                          .setContent(new Intent(MainActivity.this, HeHuoRenActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(SHEZHI)
                           .setIndicator(SHEZHI)
                           .setContent(new Intent(MainActivity.this, SheZhiActivity.class));
        tabHost.addTab(tabSpec);

        mRadiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        mRadiogroup.setOnCheckedChangeListener(this);

        mTabsRgShouye = (RadioButton) findViewById(R.id.tabs_rg_shouye);
        mTabsRgWo = (RadioButton) findViewById(R.id.tabs_rg_wo);
        mTabsRgHehuoren = (RadioButton) findViewById(R.id.tabs_rg_hehuoren);
        mTabsRgShezhi = (RadioButton) findViewById(R.id.tabs_rg_shezhi);
    }
    /**
     * TabHost及其子对象
     */
    private TabHost tabHost;
    private TabHost.TabSpec tabSpec;
    /**
     * 常量
     */
    private static final String SHOUYE = "shouye";
    private static final String WO = "wo";
    private static final String HEHUOREN = "hehuoren";
    private static final String SHEZHI = "shezhi";

    private RadioGroup mRadiogroup;
    private RadioButton mTabsRgShouye;
    private RadioButton mTabsRgWo;
    private RadioButton mTabsRgHehuoren;
    private RadioButton mTabsRgShezhi;
}
