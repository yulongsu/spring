/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package cn.su.louverbox;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author xiangnan.wxn
 * @version $Id: EnDeCodeUtil.java, v 0.1 2019年08月21日 3:54 PM xiangnan.wxn Exp $
 */
public class EnDeCodeUtil {


    /**
     * Base 64 encode string.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String base64Encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * Base 64 decode byte [ ].
     *
     * @param content the content
     * @return the byte [ ]
     */
    public static byte[] base64Decode(String content) {
        if (StringUtils.isEmpty(content)) {
            return new byte[0];
        }
        return Base64.decodeBase64(content.getBytes());
    }

    /**
     * Md 5 string.
     *
     * @param data the data
     * @return the string
     */
    public static String md5(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * Md 5 string.
     *
     * @param data the data
     * @return the string
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }
}