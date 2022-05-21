package zad1;


import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList <T> extends ArrayList<T> {
    private XList () {}

    public XList (Object... obj) {
        this.addAll(XList.of(obj));
    }

/*
    public static <T> XList<T> of(Collection col) {
        XList result = new XList<>();
        col.forEach(o -> result.addAll(XList.of(o)));
        return result;
    }




    public static <T> XList<T> of(Object... objects) {
        XList result = new XList<>();


        if(objects.length>1) {
            for (Object o : objects) {
                result.add(o);
            }
        }else{
            if (objects[0] instanceof Collection) {
                ((Collection) objects[0]).forEach(x -> result.addAll(XList.of(x)));
            } else if (objects[0].getClass().isArray()) {
                Arrays.stream((Object[]) objects[0]).forEach(x -> result.addAll(XList.of(x)));
            } else {
                result.add(objects[0]);
            }
        }


        return result;
    }

*/
    public static <T> XList of(Object... obj) {
        XList result = new XList<>();

        boolean x = true;
        for (Object o : obj) {
            if (!(o instanceof Collection || o.getClass().isArray()) || obj.length == 1) {
                for (Object o2 : obj) {
                    if (o2 instanceof Collection) {
                        ((Collection) o2).forEach(z -> result.addAll(XList.of(z)));
                    } else if (o.getClass().isArray()) {
                        Arrays.stream(((Object[]) o2)).forEach(z -> result.addAll(XList.of(z)));
                    }else{
                        result.add(o2);
                    }
                }
                return result;
            }
        }

        for (Object o : obj) {
            result.add(XList.of(o));
        }
        return result;
    }

    public static XList<String> charsOf(String str) {
        List<String> result = new ArrayList<>();

        for (char c : str.toCharArray()) {
            result.add(String.valueOf(c));
        }
        return XList.of(result);
    }

    public static XList<String> tokensOf(String str, String separator) {
        return XList.of(str.split(separator));
    }

    public static XList<String> tokensOf(String str) {
        return XList.of(str.split("\\s+"));
    }

    public XList<Integer> union(Object... obj) {
        XList result = new XList(this);

        for(Object o: obj){
            result.add(o);
        }

        return result;
    }

    public XList<T> diff(Object... obj) {
        XList<T> result = new XList<>(this);

        result.removeAll(Arrays.asList(obj));

        return result;
    }

    public XList<T> unique() {
        XList<T> result = new XList<T>();

        this.forEach(obj -> {
           boolean exists = false;

           for(Object o: result){
               if(obj==o) exists=true;
           }

           if(!exists){
               result.add(obj);
           }
        });

        return result;
    }


    public XList<XList<T>> combine() {
        XList<XList<T>> result = new XList<>();
        XList<XList<T>> var = (XList<XList<T>>) this;
        int index=0;

        if (index>=var.size()) {
            result.add(new XList<>());
        }else{ for (T o : var.get(index))
                    for (XList<T> o2 : combineRecursive(index + 1, var)) {
                        o2.add((T) o);
                        result.add(o2);
                    }
        }
        result.forEach(Collections::reverse);
        return result;
    }


    private static <T> XList<XList<T>> combineRecursive(int index, XList<XList<T>> var) {
        XList<XList<T>> result = new XList<>();

        if (index == var.size()) {
            result.add(new XList<>());
        }else{ for (Object o : var.get(index))
            for (XList<T> o2 : combineRecursive(index + 1, var)) {
                o2.add((T) o);
                result.add(o2);
            }
        }
        return result;
    }



    public String join() {
        AtomicReference<String> result = new AtomicReference<>("");

        this.forEach(o -> {
            result.set(result + (o.toString()));
        });

        return result.get();
    }


    public String join(String separator) {
        AtomicReference<String> result = new AtomicReference<>("");
        AtomicBoolean first = new AtomicBoolean(true);

        this.forEach(o -> {
            if(!first.get())result.set(result + (separator + o.toString()));
            else result.set(result + (o.toString()));

            first.set(false);
        });

        return result.get();
    }



    public <P> XList<String> collect (Function<XList<P>,String> fun) {
        XList<String> result = new XList<>();

        for (XList<P> p : ((XList<XList<P>>) this)) {
            result.add(fun.apply(p));
        }

        return result;
    }


    public void forEachWithIndex(BiConsumer<T,Integer> con) {
        for (int i = 0; i < this.size(); i++) con.accept(this.get(i), i);
    }

}
