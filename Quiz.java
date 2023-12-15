package com.Derek.courseapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "quiz_table")
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    public int quizId;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "options")
    public List<String> options;

    @ColumnInfo(name = "correctOption")
    public int correctOption;



}
