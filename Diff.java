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
    
    //left to right recursive method to detect similar characters
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

    //right to left recursive
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

    //finds all characters shared by all four versions of the inCommon call
    public static Set<Character> toSet(String[] commons) {
        Set<Character> allChars = new HashSet<>();
        String base = commons[0];
        boolean inAll;
        for(int j = 0; j < base.length(); j++) {
            inAll = true;
            for(int i = 1; i < commons.length; i++) {
                if(commons[i].indexOf(base.charAt(j)) == -1) {
                    inAll = false;
                    break //still worst case O(m * n) but increases efficiency in anything besides worst case
                    //room for improvement: setting base to the shortest string so that m is as small as possible
                }
            }
            if(inAll) {
                allChars.add(base.charAt(j));
            }
        }
        return allChars;
    }

    //rewriting the set so that all characters appear alphabetized in a list
    //Heavily inspired by ChatGPT
    public static List<Character> alphabetize(Set<Character> chars) {
        int[] count = new int[26]; 
        
        for (char c : chars) {
            count[c - 'a']++;
        }
        
        List<Character> sortedChars = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                sortedChars.add((char) (i + 'a'));
            }
        }
        return sortedChars;
    }
    
    public static String fullRun(String input) {
        String first, second;
        //separates the one String into two strings for simpler implementation
        first = input.substring(0, input.indexOf(" "));
        second = input.substring(input.indexOf(" ") + 1);

        //creates the four versions of the String inputted into toSet()
        String[] commons = new String[]{inCommon(first, second), inCommon(second, first), inCommonRev(first, second), inCommonRev(second, first)};
        
        String ans = "";
        for(char c: alphabetize(toSet(commons))) {
            //converts list into String
            ans += c;
        }
        return ans;
    }
    
    
    public static void main(String[] args) {

        //manually inputs all examples
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
