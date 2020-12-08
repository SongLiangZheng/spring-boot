package com.hs.slz;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

public class StringsTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(Strings.padStart("faa", 6, 'o'));//ooofaa
        System.out.println(Strings.padEnd("faa", 6, 'o'));//faaooo

        System.out.println(Strings.nullToEmpty(null));//""
        System.out.println(Strings.emptyToNull(""));//null
        System.out.println(Strings.repeat("abc", 2));//abcabc

        System.out.println("String with spaces and tabs"
                .equals(CharMatcher.WHITESPACE.collapseFrom("String with   spaces	and	tabs",' ')));//true

    }
}
