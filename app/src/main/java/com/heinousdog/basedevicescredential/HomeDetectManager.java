package com.heinousdog.basedevicescredential;

/**
 * Created by milkteapro on 2017/8/2.
 */

public class HomeDetectManager {
    private  static HomeDetectManager ourInstance = new HomeDetectManager();
    public static HomeDetectManager getInstance(){
        return ourInstance;
    }
    private String lastFocused;
    private HomeDetectManager(){

    }
    public void setLastFocused(String lastFocused){
        this.lastFocused=lastFocused;
    }
    public String getLastFocused(){
        return lastFocused;
    }
}
