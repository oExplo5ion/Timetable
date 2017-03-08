package com.kosenin.konstantin2.timetable.classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FireBaseHelper {

    private FireBaseHelper(){}

    private static String university = "University";
    private static String unn = "UNN";
    private static String faculty = "Faculty";
    private static String rgf = "RGF";

    public static DatabaseReference getUniversity(){
        DatabaseReference ref;
        return ref = FirebaseDatabase.getInstance().getReference().child(university);
    }

    public static DatabaseReference getUNN(){
        DatabaseReference ref;
        return ref = FirebaseDatabase.getInstance().getReference().child(university).child(unn);
    }

    public static DatabaseReference getFaculty(){
        DatabaseReference ref;
        return ref = FirebaseDatabase.getInstance().getReference().child(university).child(unn).child(faculty);
    }

    public static DatabaseReference getRGF(){
        DatabaseReference ref;
        return ref = FirebaseDatabase.getInstance().getReference().child(university).child(unn).child(faculty).child(rgf);
    }

}
