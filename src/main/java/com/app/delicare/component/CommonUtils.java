package com.app.delicare.component;

import com.app.delicare.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean isNullOrEmpty(Long str) {
        return str == null || str == 0L;
    }
    public static boolean isNullOrEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }
    public static boolean isNullOrEmpty(Object objects) {
        return objects == null;
    }
    public static String generateKeyBusiness(String key){
        //Viet nam sinh ma o day
        String keyRandom = DateUtils.convertDateToStringFormat("yyyymmddhhmmss");
        if(keyRandom == null || keyRandom.isEmpty()){
            return null;
        }
        return key.concat(keyRandom);
    }

    public static String generatePrimaryKey(){
        return RandomStringUtils.randomAlphanumeric(24);
    }

    public static int convertIntToLong(Long longValue) {
        if (longValue == null) {
            return 0;
        }

        // Ép kiểu từ Long sang int (kiểm tra phạm vi)
        long longVal = longValue.longValue();
        if (longVal < Integer.MIN_VALUE || longVal > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) longVal;  // Ép kiểu từ long sang int
    }

    public static int convertIntToString(String valueString){
        if(valueString == null){
            return 0;
        }
        return Integer.valueOf(valueString);
    }
}
