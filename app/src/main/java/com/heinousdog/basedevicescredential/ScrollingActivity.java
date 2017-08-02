package com.heinousdog.basedevicescredential;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS=1;
    private KeyguardManager keyguardManager;
    NestedScrollView nestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("alex",this.getLocalClassName());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        nestedScrollView = (NestedScrollView)findViewById(R.id.nestedScrollview);
        keyguardManager = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);

    }

    public void showAuthenticationScreen(){
        HomeDetectManager.getInstance().setLastFocused("Auth");
        Intent intent = keyguardManager.createConfirmDeviceCredentialIntent("重新認證","重新認證以繼續使用");
        if(intent != null){
            startActivityForResult(intent,REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS){
            if(resultCode==RESULT_OK){
                Snackbar.make(nestedScrollView, "Auth Success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else{
                Snackbar.make(nestedScrollView, "Auth Fail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(HomeDetectManager.getInstance().getLastFocused().equals(this.getLocalClassName())){
            showAuthenticationScreen();
        }else if(HomeDetectManager.getInstance().getLastFocused().equals("")){
            showAuthenticationScreen();
        }else{
            HomeDetectManager.getInstance().setLastFocused(this.getLocalClassName());
        }
    }
}
