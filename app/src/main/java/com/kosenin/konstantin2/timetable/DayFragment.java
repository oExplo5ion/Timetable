package com.kosenin.konstantin2.timetable;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kosenin.konstantin2.timetable.classes.FireBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Konstantin2 on 15.03.2017.
 */

public class DayFragment extends android.support.v4.app.Fragment {

    private DatabaseReference reference;
    private FirebaseDatabase database;
    private ChildEventListener childEventListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = FireBaseHelper.getRGF().child("Gruppe");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, null);

        final ListView listView = (ListView) view.findViewById(R.id.lessons_lv);
        final List<String> lessons = new ArrayList<>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               lessons.add(dataSnapshot.getKey());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, lessons);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                lessons.add(dataSnapshot.getKey());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, lessons);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                lessons.add(dataSnapshot.getKey());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, lessons);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                lessons.add(dataSnapshot.getKey());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, lessons);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addChildEventListener(childEventListener);

        return view;
    }

    public static android.support.v4.app.Fragment newInstance() {

        DayFragment dayFragment = new DayFragment();
        return dayFragment;
    }
}

