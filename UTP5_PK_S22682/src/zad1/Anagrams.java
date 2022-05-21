/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Anagrams {
    List<ArrayList<String>> anagrams= new ArrayList<>();

    public Anagrams(String path){
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Boolean temp;
        while(scan.hasNext()) {
            String word=scan.next();
            temp=false;
            for(List<String> a: anagrams){
                if(isAnagram(a.get(0),word)){
                    a.add(word);
                    temp=true;
                }
            }
            if(!temp){
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(word);
                anagrams.add(newList);
            }
        }
    }


    public List<ArrayList<String>> getSortedByAnQty(){
        anagrams.sort(new Comp());
        return anagrams;
    }

    public String getAnagramsFor(String next) {
        String result=next+": [";
        Boolean first=true;
        for(List<String> a : anagrams){
            if(isAnagram(next,a.get(0))){
                for(String s : a){
                    if(!s.equals(next))
                        if(first) {
                            result = result + s;
                            first=false;
                        }
                        else result=result+","+s;
                }
            }
        }
        result=result+"]";
        return result;
    }

    private Boolean isAnagram(String s1,String s2){
        if(s1.length()!=s2.length()) return false;
        int temp;
        for(char c1: s1.toCharArray()){
            temp=0;
            for(char c2: s2.toCharArray()){
                if(c1==c2) temp++;
            }
            if(temp==0) return false;
        }
        return true;
    }
    class Comp implements Comparator<ArrayList<String>>
    {

        public int compare(ArrayList<String> a, ArrayList<String> b)
        {
            if(b.size()==a.size()) return a.get(0).toCharArray()[0] - b.get(0).toCharArray()[0];
            else return b.size()-a.size();
        }
    }

}

