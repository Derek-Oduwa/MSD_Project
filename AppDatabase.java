package com.Derek.courseapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Course.class, Lesson.class, Quiz.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getInstance(ListActivity listActivity) {
        return null;
    }

    public static void getInstance(Context applicationContext) {
        return;
    }

    // Define abstract methods for each DAO
    public abstract CourseDao courseDao();
    public abstract LessonDao lessonDao();
    public abstract QuizDao quizDao();

    private static AppDatabase INSTANCE;

    // Singleton pattern to ensure only one instance of the database is created
    public static synchronized AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
