package com.heinousdog.basedevicescredential;

/**
 * Home button Detect singleton.
 * Created by heinousdog on 2017/8/2.
 */

class HomeDetectManager {
    private  static HomeDetectManager ourInstance = new HomeDetectManager();
    protected static HomeDetectManager getInstance(){
        return ourInstance;
    }
    private String lastFocused="";
    private HomeDetectManager(){

    }
    protected void setLastFocused(String lastFocused){
        this.lastFocused=lastFocused;
    }
    protected String getLastFocused(){
        return lastFocused;
    }
}
