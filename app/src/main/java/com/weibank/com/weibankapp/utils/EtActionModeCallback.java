package com.weibank.com.weibankapp.utils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Administrator on 2016/4/21.
 */
public class EtActionModeCallback implements ActionMode.Callback{

    public EtActionModeCallback(){}
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu){
        return false;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu){
        return false;
    }
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item){
        return false;
    }
    @Override
    public void onDestroyActionMode(ActionMode mode){}
}
