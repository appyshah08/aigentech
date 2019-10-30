package com.example.dummy.aigentech.common;

/**
 * Created by Neeta on 10/29/2019.
 */

public class CommonUtils {

    public static boolean isEmpty(String value)
    {
        if(value != null && !value.isEmpty())
        {
            return false;
        }
        return true;
    }
}
