/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function<String, List<String>> flines = (path) -> {
      List<String> res = new LinkedList<>();
      FileReader freader = null;
      String line = "";

      try {
        freader = new FileReader(path);
      } catch (FileNotFoundException e) {
        System.out.println("Błąd przy otwieraniu pliku");
      }

      BufferedReader bfreader = new BufferedReader(freader);
      try {
        while ((line = bfreader.readLine()) != null) {
          res.add(line);
        }
      } catch (IOException e) {
        System.out.println("BŁĄD ODCZYTU Z PLIKU!");
        System.exit(2);
      }

      try {
        freader.close();
      } catch (IOException e) {
        System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
        System.exit(3);
      }

      return res;
    };

    Function<List<String>, String> join = (list) -> {
      String res="";
      for(String temp: list){
        res=res+temp;
      }
      return res;
    };
    Function<String, List<Integer>> collectInts = (str) -> {
      List<Integer> res = new LinkedList<>();
      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(str);

      while (matcher.find()) {
        res.add(Integer.parseInt(matcher.group()));
      }



      return res;
    };

    Function<List<Integer>, Integer> sum = (list) -> {
      Integer res=0;
      for(Integer i: list){
        res=res+i;
      }
      return res;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
