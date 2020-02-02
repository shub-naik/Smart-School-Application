package com.pragyatitsolutions.SchoolManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherManagement extends AppCompatActivity {

    Button Add, add_subject, view, delete, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_management);
        Add = findViewById(R.id.AddTeacher);
        add_subject = findViewById(R.id.AddSubject);
        view = findViewById(R.id.ViewTeacher);
        delete = findViewById(R.id.DeleteTeacher);
        update = findViewById(R.id.UpdateTeacher);

        getSupportActionBar().setTitle("Teacher Management");

        //Adding the Teacher to the FireBase
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherManagement.this, AddTeacher.class);
                startActivity(i);
            }
        });

        //Updating Teacher and Adding the Subjects
        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherManagement.this, AddSubject.class);
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherManagement.this, ViewTeacher.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherManagement.this, DeleteTeacher.class);
                startActivity(i);
            }
        });

    }
}
