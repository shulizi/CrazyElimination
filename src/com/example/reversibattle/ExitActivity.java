package com.example.reversibattle;

import android.app.Activity;
import android.os.Bundle;

public class ExitActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        finish();
        System.exit(0);
	}
}
