package com.mobileapp.mobilelaba2.ui.dashboard;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.mobilelaba2.R;
import com.mobileapp.mobilelaba2.ui.dashboard.database.MyDbHelper;
import com.mobileapp.mobilelaba2.ui.dashboard.database.StudentCourses;
import com.mobileapp.mobilelaba2.ui.dashboard.database.StudentsCoursesAdapter;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        MyDbHelper myDbHelper = new MyDbHelper(requireContext());

//        myDbHelper.deleteStudent(1);
//        myDbHelper.addStudent(new StudentCourses("Кондратюк Ю.В.", "МПО", "65", "проспект Академіка Глушкова 4д"));
//        myDbHelper.addStudent(new StudentCourses("Гірка О.А.", "ІТ", "65", "Володимирська 60"));
//        myDbHelper.addStudent(new StudentCourses("Степаньк І.Р.", "РПЗ", "65", "Володимирська 70"));
//        myDbHelper.addStudent(new StudentCourses("Кондратюк Ю.В.", "ОУІТП", "70", "проспект Академіка Глушкова 4д"));
//        myDbHelper.addStudent(new StudentCourses("Кондратюк Ю.В.", "ОУІТП", "55", "проспект Академіка Глушкова 4д"));

        List<StudentCourses> studentCourses = myDbHelper.getAllStudents();
        StudentsCoursesAdapter studentsCoursesAdapter = new StudentsCoursesAdapter(requireContext(), studentCourses);
        recyclerView.setAdapter(studentsCoursesAdapter);

        Button buttonGrade = view.findViewById(R.id.buttonGrade);
        Button buttonAvarage = view.findViewById(R.id.button2);
        Button buttonShowAll = view.findViewById(R.id.button3);
        TextView textViewPercentage = view.findViewById(R.id.studentsPercentage);

        buttonGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<StudentCourses> studentsGradeMore60 = myDbHelper.getStudentsAbove60();
                StudentsCoursesAdapter studentsMore60Adapter = new StudentsCoursesAdapter(requireContext(), studentsGradeMore60);
                recyclerView.setAdapter(studentsMore60Adapter);
            }
        });

        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<StudentCourses> studentsAll = myDbHelper.getAllStudents();
                StudentsCoursesAdapter studentsAllAdapter = new StudentsCoursesAdapter(requireContext(), studentsAll);
                recyclerView.setAdapter(studentsAllAdapter);
            }
        });

        buttonAvarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double avarageGrade = myDbHelper.getAverageGrade();
                textViewPercentage.setText(String.format(Locale.getDefault(), "%.2f", avarageGrade));
            }
        });


        return view;
    }

}