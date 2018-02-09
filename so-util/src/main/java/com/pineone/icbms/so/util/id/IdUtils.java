package com.pineone.icbms.so.util.id;

import java.util.Random;
import java.util.UUID;

/**
 * ID utilities.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
public class IdUtils {
    /**
     * return random UUID string.<BR/>
     * @return UUID string
     */
    public static String createRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * return random Long value.<BR/>
     * @return Long value
     */
    public static Long createRandomLongValue() {
        return new Random().nextLong();
    }

    /**
     * test main
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println(IdUtils.createRandomUUID());
        //System.out.println(IdUtils.createRandomLongValue());
    }
}
