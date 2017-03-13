package com.kosenin.konstantin2.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.kosenin.konstantin2.timetable.classes.FireBaseHelper;

import java.util.ArrayList;
import java.util.List;

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

        // accept data from intent
        final Type type = (Type) getIntent().getExtras().get("type");

        // switch data
        DatabaseReference mTypeBDRef;
        Type callType = null;

        switch (type){
            case UNN:
                mTypeBDRef = FireBaseHelper.getUNN();
                callType = Type.FACULTY;
                break;
            case FACULTY:
                mTypeBDRef = FireBaseHelper.getFaculty();
                callType = Type.RGF;
                break;
            case RGF:
                mTypeBDRef = FireBaseHelper.getRGF();
                break;
            default:
                mTypeBDRef = FireBaseHelper.getUniversity();
                callType = Type.UNN;
                break;
        }

        // collect data
        final List<String> lst = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.list_v);

        final Type finalCallType = callType;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (type != Type.RGF) {
                    Intent intent = new Intent(DataActivity.this, DataActivity.class);
                    intent.putExtra("type", finalCallType);
                    startActivity(intent);
                }

            }
        });

        ChildEventListener mTypeChildEventListener = new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 lst.add(dataSnapshot.getKey());
             }

             @Override
             public void onChildChanged(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onChildRemoved(DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(DataActivity.this, android.R.layout.simple_list_item_1, lst);
        listView.setAdapter(adapter);
        mTypeBDRef.addChildEventListener(mTypeChildEventListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            case R.id.log_out:
                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return true;
    }

}
