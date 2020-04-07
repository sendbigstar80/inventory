package com.logistics.cloud.Constants;

import java.util.regex.Pattern;

public class RegularConstants {
    /**
     * 校验电话格式
     */
    public static final Pattern PHONE_REGEXP = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");

}
