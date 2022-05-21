package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    public T t;

    public Maybe(){ }

    public Maybe(T t){
        this.t=t;
    }

    public static<T> Maybe<T> of(T t){
        return new Maybe(t);
    }

    public void ifPresent(Consumer cons){
        if(t!=null) cons.accept(t);
    }

    public <Q> Maybe<Q> map(Function<T,Q> func){
        if(t!=null)return new Maybe((Q)func.apply(t));
        else return new Maybe();
    }

    public T get(){
        if(t==null) throw new NoSuchElementException("maybe is empty");
        else return t;
    }

    public boolean isPresent(){
        return (t!=null);
    }

    public T orElse(T defVal){
        if(t!=null) return t;
        else return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(pred.test(t)||(t==null)) return this;
        else return new Maybe();
    }

    @Override
    public String toString() {
        if(t!=null) return "Maybe has value "+t;
        else return "Maybe is empty";
    }
}
