package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class ListCreator<T> {

    List<T> list;

    ListCreator(List<T> list){
        this.list=list;
    }

    public static <T> ListCreator collectFrom(List<T> list){
        return new ListCreator<T>(list);
    }

    public <T> ListCreator when(Predicate<T> u){
        List<T> nowalista = new ArrayList<T>();



        return new ListCreator<T>(nowalista);
    }

    public <R> List<T> mapEvery(Function <T,R> u) {
        List<T> nowalista= new ArrayList<T>();


        return nowalista;
    }
}

