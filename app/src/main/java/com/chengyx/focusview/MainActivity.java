package com.chengyx.focusview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private FocusView mFocusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFocusView = (FocusView) findViewById(R.id.focus_View);
    }

    public void onBtnClick(View view) {
        mFocusView.flyBorder(view);
    }
}
