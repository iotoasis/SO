package com.pineone.icbms.so.util.conversion;

import java.util.Random;

/**
 * Created by melvin on 2017. 2. 6..
 */
public class UUIDConverter {

    public static String shortUUID(char[] uuid){
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0;  i < 8;  i++) {
            id[i] = uuid[r.nextInt(uuid.length)];
        }
        return new String(id);
    }

}
