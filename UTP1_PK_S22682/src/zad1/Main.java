/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12); /*<-- tu dopisać inicjację elementów listy */
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" ); /*<-- tu dopisać inicjację elementów listy */
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>(){

      @Override
      public boolean select(Integer integer) {
        if(integer<10)return true;
        else return false;
      }
    }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper<Integer> map = new Mapper<Integer>(){

      @Override
      public Integer map(Integer integer) {
        return integer+10;
      }
    }; /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */ ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = new Selector<String>(){

      @Override
      public boolean select(String s) {
        if(s.length()>3) return true;
        else return false;
      }
    };  /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper<String> map = new Mapper<String>(){
      @Override
      public String map(String s) {
        return ""+(s.length()+10);
      }
    };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */ ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
