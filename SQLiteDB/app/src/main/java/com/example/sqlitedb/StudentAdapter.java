package com.example.sqlitedb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    Context context;
    List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_student,null);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {

        holder.textRollNo.setText("Rollno - "+studentList.get(position).getRollNo());
        holder.textName.setText("Name - "+studentList.get(position).getName());
        holder.textMarks.setText("Marks - "+studentList.get(position).getMarks());


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        TextView textRollNo,textName,textMarks;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            textRollNo =  itemView.findViewById(R.id.textRollNo);
            textName = itemView.findViewById(R.id.textname);
            textMarks = itemView.findViewById(R.id.textmarks);
            itemView.setOnLongClickListener(this);
        }



        @Override
        public boolean onLongClick(View view) {


            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.getMenu().add("Edit");
            popupMenu.getMenu().add("Delete");
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getTitle().equals("Edit")) {
                        Intent intent = new Intent(context, EditStudentActivity.class);
                        intent.putExtra("student",studentList.get(getAdapterPosition()));
                        context.startActivity(intent);

                    } else if (menuItem.getTitle().equals("Delete")) {
                        StudentDbHelper dbHelper = new StudentDbHelper(context);
                        int rollno = studentList.get(getAdapterPosition()).getRollNo();
                        dbHelper.deleteStudent(rollno);

                        studentList = dbHelper.getAllStudents();
                        StudentAdapter.this.notifyDataSetChanged();

                    }


                    return false;
                }
            });
            return false;
        }
    }
}
