package com.Derek.courseapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);
    @Insert
    void insertLesson(Lesson lesson);

    @Insert
    void insertQuiz(Quiz quiz);

    @Query("SELECT * FROM lesson_table")
    LiveData<List<Lesson>> getAllLessons();

    @Query("SELECT * FROM quiz_table")
    LiveData<List<Quiz>> getAllQuizzes();
    @Query("SELECT * FROM course_table")
    LiveData<List<Course>> getAllCourses();
}

