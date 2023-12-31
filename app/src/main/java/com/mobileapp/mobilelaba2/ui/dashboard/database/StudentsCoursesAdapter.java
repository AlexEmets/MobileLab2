package com.mobileapp.mobilelaba2.ui.dashboard.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.List;

public class StudentsCoursesAdapter extends RecyclerView.Adapter<MyStubentCoursesHolder> {

    Context context;
    List<StudentCourses> studentCourses;

    public StudentsCoursesAdapter(Context context, List<StudentCourses> studentCourses) {
        this.context = context;
        this.studentCourses = studentCourses;
    }

    @NonNull
    @Override
    public MyStubentCoursesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyStubentCoursesHolder(LayoutInflater.from(context).inflate(R.layout.student_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyStubentCoursesHolder holder, int position) {
        holder.pibView.setText(studentCourses.get(position).getPib());
        holder.nameView.setText(studentCourses.get(position).getName());
        holder.gradesView.setText(studentCourses.get(position).getGrade());
        holder.addressView.setText(studentCourses.get(position).getAdress());
        String id = "    " + studentCourses.get(position).getId() ;
        holder.idView.setText(id);
    }

    @Override
    public int getItemCount() {
        return studentCourses.size();
    }
}
