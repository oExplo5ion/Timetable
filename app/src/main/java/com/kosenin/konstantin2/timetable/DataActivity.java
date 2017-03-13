package com.kosenin.konstantin2.timetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;

public class DataActivity extends AppCompatActivity {

    enum Type{
        UNN, FACULTY, RGF
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        // action bar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String data = getIntent().getExtras().getString("data");
        Type type = (Type) getIntent().getExtras().get("type");

        switch (type){
            case UNN:
                // load unn
                System.out.print("ezzz");
                break;
            case FACULTY:
                // load faculty
                break;
            case RGF:
                // load rgf
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out:
                AuthUI.getInstance().signOut(this);
                break;
        }
        return true;
    }

}
