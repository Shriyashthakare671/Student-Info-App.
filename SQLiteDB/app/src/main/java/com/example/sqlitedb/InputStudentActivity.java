package com.example.sqlitedb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputStudentActivity extends AppCompatActivity {
    EditText editRollNo,editName,editMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_student);

        editRollNo = findViewById(R.id.editRollNo);
        editName = findViewById(R.id.editName);
        editMarks = findViewById(R.id.editMarks);



    }
    public void save(View view) {
        Student student = new Student();
        student.setRollNo(Integer.parseInt(editRollNo.getText().toString()));

        student.setName(editName.getText().toString());

        student.setMarks(Double.parseDouble(editMarks.getText().toString()));

        StudentDbHelper Dbhelper = new StudentDbHelper(this);
        Dbhelper.insertStudent(student);
        finish();



    }
}