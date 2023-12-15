package com.Derek.courseapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert
    void insertQuiz(Quiz quiz);

    @Query("SELECT * FROM quiz_table")
    List<Quiz> getAllQuizzes();
}

