package org.ygcxy.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: org.ygcxy.utils
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 23:47
 * @Description:
 */
public class AnalyzeUtils {
    public static List<String> velocityListByYear(Integer year) {
        List<String> months = new ArrayList<>();
        for(int i=1;i<=12;i++){
            months.add(
                    year + "-" + (i >= 10 ? i : "0" + i)
            )    ;
        }
        return months;
    }
}
