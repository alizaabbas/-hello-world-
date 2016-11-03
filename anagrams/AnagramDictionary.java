package com.google.engedu.anagrams;
import android.util.Log;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
/***********************


    The project is not completed yet!!!



**************************/
public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<>();
    private HashMap<String,ArrayList<String>> lettersToWord= new HashMap<>();

     public AnagramDictionary(InputStream wordListStream) throws IOException {
         BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
         String line;

         while ((line = in.readLine()) != null) {
             String word = line.trim();
             wordList.add(word);
             wordSet.add(word);

             String myStr = sortLetter(word).toString() ;
             if (lettersToWord.containsKey(myStr)) {
                 ArrayList<String> temp = lettersToWord.get(myStr) ;
                 temp.add(word);
                 lettersToWord.put(myStr,temp);
             } else {
                 ArrayList<String> temp = new ArrayList<>();
                 temp.add(word);
                 lettersToWord.put(myStr, temp);
             }

         }
     }


    public boolean isGoodWord(String word, String base) {
        
        String str = word;
        boolean check = str.toLowerCase().contains(base.toLowerCase());
        if(check == false && wordList.contains(word))
        return true;
        else
            return false;
         }

    public ArrayList<String> getAnagrams(String targetWord) {

        ArrayList<String> result = new ArrayList<>();
        result = lettersToWord.get(sortLetter(targetWord).toString());
        return result;
    }
    private char[] sortLetter(String str){
        char[] ch = new char[str.length()];
        ch = str.toCharArray();
        String returnStr = "";
        Arrays.sort(ch);
        return ch;
    }
    /**
    private boolean findBaseWord(String oneMoreLetterWord, String sortedWord){
        String str = "";
        String letter = sortLetter(oneMoreLetterWord).toString();
        int i = 0;
        while(i < sortedWord.length()){
            if(oneMoreLetterWord.indexOf(sortedWord.charAt(i)) < 0) return false;
         i++;
        }
        return true;
    }
     **/
    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char[] alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(char i = 'a' ; i<= 'z'; i++){
            String baseWord = sortLetter(word+i).toString();
                if(lettersToWord.containsKey(baseWord)){
                    result.addAll(lettersToWord.get(baseWord));
                }
        }
        for(int i = 0; i < result.size(); i++){
            if(result.contains(word)) result.remove(i);
        }
        
        return result;
    }

    public String pickGoodStarterWord() {
		// this method not implemented yet, in progress
        return "stop";
    }
}
