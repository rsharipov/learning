package com.topcoder;

import java.util.ArrayList;
import java.util.List;

public class CreateString {

    public List<String> findString(int K) {
        int length = 1;
        List<String> list = new ArrayList<>();
        while (true) {
            for (int i = 0, end = (int)Math.pow(3, length); i < end; ++i) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0, num = i; j < length; ++j) {
                    builder.append("ABC".charAt(num % 3));
                    num /= 3;
                }
                int pairs = 0;
                for (int j = 0; j < length; ++j) {
                    for (int k = j + 1; k < length; ++k) {
                        if (builder.codePointAt(j) < builder.codePointAt(k)) {
                            ++pairs;
                        }
                    }
                }
                if (pairs == K) {
                    list.add(builder.toString());
                }
            }
            if (!list.isEmpty()) {
                return list;
            }
            ++length;
        }
        
    }
    
    
    public static void main(String[] args) {
        for (int i = 1; i < 50; ++i) {
            System.out.println(i + " - " + new CreateString().findString(i));
        }
    }

    private boolean sorted(String string) {
        for (int i = 0; i < string.length() - 1; ++i) {
            if (string.charAt(i) > string.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
}
