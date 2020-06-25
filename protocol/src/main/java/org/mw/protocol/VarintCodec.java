package org.mw.protocol;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public class VarintCodec {


    public static byte[] intToBytes(int num) {
        byte[] result = null;
        if (num < 127) {
            result = new byte[1];
            int i = 0;
            while (num % 2 > 1) {
                result[i] = 1;
                num = num / 2;
            }
        } else {

        }
        return result;
    }

    public static byte[] longToBytes(long num) {
        if (0 < num && num < 127) {
           // return new byte[1] =;
        }
        return null;
    }

    public static void main(String[] args) {
        intToBytes(123);
    }


}
