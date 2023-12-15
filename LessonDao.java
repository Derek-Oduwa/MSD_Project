package com.Derek.courseapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface LessonDao {
    @Insert
    void insertLesson(Lesson lesson);

    @Query("SELECT * FROM lesson_table")
    List<Lesson> getAllLessons();
}

