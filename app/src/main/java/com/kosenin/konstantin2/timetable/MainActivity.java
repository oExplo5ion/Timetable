package com.kosenin.konstantin2.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        private FirebaseDatabase mFirebaseDatabase;
        private DatabaseReference mGruppeDBRef;
        private ChildEventListener mGruppeChildEventListener;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mGruppeDBRef = FirebaseDatabase.getInstance().getReference().child("University").child("UNN").child("Faculty").child("RGF");

            final ListView listView = (ListView) findViewById(R.id.lv_main);




            mGruppeChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    List<String> lst = new ArrayList<String>(); // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        lst.add(String.valueOf(dsp.getKey())); //add result into array list
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lst);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    List<String> lst = new ArrayList<String>(); // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        lst.add(String.valueOf(dsp.getKey())); //add result into array list
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lst);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    List<String> lst = new ArrayList<String>(); // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        lst.add(String.valueOf(dsp.getKey())); //add result into array list
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lst);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    List<String> lst = new ArrayList<String>(); // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        lst.add(String.valueOf(dsp.getKey())); //add result into array list
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lst);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mGruppeDBRef.addChildEventListener(mGruppeChildEventListener);

        }
}
