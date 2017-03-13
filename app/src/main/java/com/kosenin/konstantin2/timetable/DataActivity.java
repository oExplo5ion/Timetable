package com.kosenin.konstantin2.timetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    enum Type{
        UNN, FACULTY, RGF;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

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
}
