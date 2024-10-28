/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author colinjacobruark
 */

import java.util.*;

public class Diff {
    
    
    public static String inCommon(String a, String b) {
        if(a.length() > 0 && b.length() > 0) {
            if(b.indexOf(a.charAt(0)) != -1) {
                return a.charAt(0) + inCommon(a.substring(1), b.substring(b.indexOf(a.charAt(0)) + 1));
            }
            else {
                return inCommon(a.substring(1), b);
            }
        }
        return "";
    }
    
    public static String inCommonRev(String a, String b) {
        if(a.length() > 0 && b.length() > 0) {
            if(b.lastIndexOf(a.charAt(a.length() - 1)) != -1) {
                return inCommonRev(a.substring(0, a.length() - 1), b.substring(0, b.lastIndexOf(a.charAt(a.length() - 1)))) + a.charAt(a.length() - 1);
            }
            else {
                return inCommonRev(a.substring(0, a.length() - 1), b);
            }
        }
        return "";
    }
    
    public static Set<Character> toSet(String[] commons) {
        Set<Character> allChars = new HashSet<>();
        String base = commons[0];
        boolean inAll;
        for(int j = 0; j < base.length(); j++) {
            inAll = true;
            for(int i = 1; i < commons.length; i++) {
                if(commons[i].indexOf(base.charAt(j)) == -1) {
                      inAll = false;
                }
            }
            if(inAll) {
                allChars.add(base.charAt(j));
            }
        }
        return allChars;
    }
    
    public static String fullRun(String input) {
        String first, second;
        first = input.substring(0, input.indexOf(" "));
        second = input.substring(input.indexOf(" ") + 1);
        String[] commons = new String[]{inCommon(first, second), inCommon(second, first), inCommonRev(first, second), inCommonRev(second, first)};
        String ans = "";
        for(char c: alphabetize(toSet(commons))) {
            ans += c;
        }
        return ans;
    }
    
    public static List<Character> alphabetize(Set<Character> chars) {
        int[] count = new int[26]; // Adjust to 52 if you need case-sensitive sorting
        
        // Counting occurrences
        for (char c : chars) {
            count[c - 'a']++; // Map 'a' to 0, 'b' to 1, ..., 'z' to 25
        }

        // Creating a sorted list of characters
        List<Character> sortedChars = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                sortedChars.add((char) (i + 'a'));
            }
        }
        return sortedChars;
    }
    
    
    public static void main(String[] args) {
        
        System.out.println(fullRun("abcddef vdfaddwf"));
        
        System.out.println(fullRun("friends afraid"));
        
        System.out.println(fullRun("delicious indiginous"));
        
        System.out.println(fullRun("shalom saaalaaam"));
        
        System.out.println(fullRun("happy shipshape"));
        
        System.out.println(fullRun("seashells seashore"));
        
        System.out.println(fullRun("newjersey jerseyshore"));
        
        System.out.println(fullRun("javaprogramming programinjava"));
        
        System.out.println(fullRun("hurricanesandy hurrytosandbeaches"));
        
        System.out.println(fullRun("antidisestablishmentarianism antitotalitarianism"));
                
    }
}
