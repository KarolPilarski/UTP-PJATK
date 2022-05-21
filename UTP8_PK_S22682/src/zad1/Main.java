/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    try{
        InputStream is = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openConnection().getInputStream();
        Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines();
        Map<String, List<String>> anagrams = stream.collect(Collectors.groupingBy(w ->{
          char tempArray[] = w.toCharArray();
          Arrays.sort(tempArray);
          return new String(tempArray);}
          ));

          anagrams.values().stream().filter((a)->{
            return (a.size()>1);
          }).sorted((a,b)->{
            return b.size()-a.size();
          }).forEach((b)->{
            for(String s: b){
              System.out.print(s+" ");
            }
            System.out.println();
          });
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
