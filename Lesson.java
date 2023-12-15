package com.Derek.courseapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
@Entity(tableName = "lesson_table")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    public int lessonId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    // Additional fields as needed
}
