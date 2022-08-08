package com.zy.blog.util;

import java.security.MessageDigest;

/**
 * @Classname Md5Util
 * @Description TODO
 * @Date 2021/9/21 16:46
 * @Created by 飞燕
 */
public class Md5Util {
    private static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     * 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {n += 256;}
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    /**判断用户密码是否正确
     *newpasswd 用户输入的密码
     *oldpasswd 正确密码*/
    public static boolean checkPassword(String newpasswd,String oldpasswd){
        if(MD5Encode(newpasswd).equals(oldpasswd)) {
            return true;
        }
        else {
            return false;
        }
    }
}
