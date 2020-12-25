package com.ce.majiang;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author c__e
 * @date 2020/12/25 16:18
 */
public class Mytest {

    @Test
    public void test1() {
        System.out.println(StringUtils.isBlank("   "));
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(null));
    }
}
