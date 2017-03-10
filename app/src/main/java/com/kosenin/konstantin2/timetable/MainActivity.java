package com.kosenin.konstantin2.timetable;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kosenin.konstantin2.timetable.classes.FireBaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGruppeDBRef;
    private ChildEventListener mGruppeChildEventListener;

    private FirebaseAuth mTimetableAuth;
    private FirebaseAuth.AuthStateListener mTimetableAuthListener;

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mTimetableAuth = FirebaseAuth.getInstance();


        mGruppeDBRef = FireBaseHelper.getRGF().child("Gruppe").child("j915d");
        final List<String> lst = new ArrayList<String>(); // Result will be holded Here


        final ListView listView = (ListView) findViewById(R.id.lv_main);


        mGruppeChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                lst.add(dataSnapshot.getKey());
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


        mTimetableAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {
                    //signed in
                    Toast.makeText(MainActivity.this, "Успешный вход", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


    }

    // Adding Authentification Listener
    @Override
    protected void onResume() {
        super.onResume();
        mTimetableAuth.addAuthStateListener(mTimetableAuthListener);
    }


    //Removing Authentification Listener
    @Override
    protected void onPause() {
        super.onPause();
        mTimetableAuth.removeAuthStateListener(mTimetableAuthListener);
    }
}
