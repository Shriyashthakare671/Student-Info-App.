package com.example.sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditStudentActivity extends AppCompatActivity {
    TextView textRollNo,textName;

    EditText editMarks;
    EditText editName;

    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        textRollNo = findViewById(R.id.textRollNo);
//        textName = findViewById(R.id.textName);
        editName = findViewById(R.id.textName);
        editMarks = findViewById(R.id.editMarks);
        
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        textRollNo.setText("Rollno -" +student.getRollNo());
//        textName.setText("Name -" +student.getName());
        editName.setText(""+student.getName());
        editMarks.setText("" + student.getMarks());

    }

    public void updateStudent(View view) {
        String name = (editName.getText().toString());
        double marks = Double.parseDouble(editMarks.getText().toString());
        student.setMarks(marks);
        student.setName(name);
        StudentDbHelper dbHelper = new StudentDbHelper(this);
        dbHelper.updateStudent(student);
        finish();
    }
}