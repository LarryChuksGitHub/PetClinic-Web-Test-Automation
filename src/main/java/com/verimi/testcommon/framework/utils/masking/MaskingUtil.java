package com.verimi.testcommon.framework.utils.masking;

 public class MaskingUtil {

     public static final int TWO_CHARACTERS_UNMASKED = 2;
     public static final int ZERO_CHARACTERS_UNMASKED = 0;
     private static final char CHARACTER_MASK = 'X';

     public static String maskCharactersFromTo(String string, int from, int to) {
         StringBuilder result = new StringBuilder();
         for (int i = 0; i < string.length(); i++) {
             if (i >= from &&  i < string.length() - to)  {
                 result.append(CHARACTER_MASK);
             } else {
                 result.append(string.charAt(i));
             }
         }
         return result.toString();
     }

}
