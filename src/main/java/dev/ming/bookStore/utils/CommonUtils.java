package dev.ming.bookStore.utils;

import java.security.MessageDigest;

/**
 * 工具類
 */
public class CommonUtils {

    /**
     * MD5加密工具類
     * @param data
     * @return
     */
    public static String MD5(String data){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte item: array){
                sb.append(Integer.toHexString((item&0xFF)| 0x100).substring(1,3));
            }
            return sb.toString().toUpperCase();
        }catch(Exception e){

        }
        return null;
    }
}
