package com.example.user.hradvacation;

import org.json.JSONArray;

public class ParsingJSON {
    public static String[] toStringArray(JSONArray array){
        if(array == null)
            return null;
        String[] arr = new String[array.length()];
        for(int i=0; i<arr.length; i++){
            arr[i] = array.optString(i);
        }
        return arr;
    }
}
