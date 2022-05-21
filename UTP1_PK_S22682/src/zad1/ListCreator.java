/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;


public class ListCreator<T> {

    List<T> list;

    ListCreator(List<T> list){
        this.list=list;
    }

    public static <T> ListCreator collectFrom(List<T> list){

        return new ListCreator<T>(list);

    }

    public <T> ListCreator when(Selector<T> sel){
        List<T> nowalista = new ArrayList<T>();

        for(int i=0;i<list.size();i++){
            if(sel.select((T) list.get(i))) nowalista.add((T) list.get(i));
        }

        return new ListCreator<T>(nowalista);
    }

    public List<T> mapEvery(Mapper<T> map) {
        List<T> nowalista= new ArrayList<T>();

        for(int i=0;i<list.size();i++){
            nowalista.add(map.map(list.get(i)));
        }

        return nowalista;
    }
}
