package com.Derek.courseapp;

import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> fromString(String value) {
        // Use a method to convert the string representation back to a List
        // For example, you can split the string by a delimiter
        return value == null ? null : Arrays.asList(value.split(","));
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        // Use a method to convert the List to a string representation
        // For example, you can join the list elements with a delimiter
        if (list == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append(",");
        }

        return sb.toString();
    }
}

