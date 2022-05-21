package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ProgLang {

    LinkedHashMap<String, LinkedList<String>> values = new LinkedHashMap<>();

    ProgLang(String path){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tab = line.split("\t");
            values.put(tab[0],new LinkedList<String>());
            for(int i=1;i<tab.length;i++) {
                Boolean exists=true;
                for(int j=1;j<i;j++){
                    if(tab[i].equals(tab[j])) exists=false;
                }
                if(exists)values.get(tab[0]).add(tab[i]);
            }
        }
        scanner.close();
    }


    public LinkedHashMap<String, LinkedList<String>> getLangsMap() {
        return values;
    }

    public LinkedHashMap<String, LinkedList<String>> getProgsMap(){
        LinkedHashMap<String, LinkedList<String>> temp = new LinkedHashMap<String,LinkedList<String>>();

        values.forEach((key,value)->{
            value.forEach((a)->{
                boolean exists=false;
                for(String s: temp.keySet()) {
                    if (s.equals(a)) exists = true;
                }
                if(exists){
                    temp.get(a).add(key);
                }else{
                    temp.put(a,new LinkedList<String>());
                    temp.get(a).add(key);
                }
            });
        });

        return temp;
    }

    public LinkedHashMap<String, LinkedList<String>> getLangsMapSortedByNumOfProgs(){
        LinkedHashMap<String, LinkedList<String>> result = new LinkedHashMap<String,LinkedList<String>>();

        String keys[] = values.keySet().toArray(new String[5]);

        int n = keys.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (values.get(keys[j]).size() < values.get(keys[j+1]).size())
                {
                    String temp = keys[j];
                    keys[j] = keys[j+1];
                    keys[j+1] = temp;
                }

        for(int i=0; i<keys.length; i++){
            result.put(keys[i],values.get(keys[i]));
        }

        return result;
    }

    public LinkedHashMap<String, LinkedList<String>> getProgsMapSortedByNumOfLangs(){
        LinkedHashMap<String, LinkedList<String>> result = new LinkedHashMap<String,LinkedList<String>>();

        LinkedHashMap<String, LinkedList<String>> progs = new LinkedHashMap<String,LinkedList<String>>();

        values.forEach((key,value)->{
            value.forEach((a)->{
                boolean exists=false;
                for(String s: progs.keySet()) {
                    if (s.equals(a)) exists = true;
                }
                if(exists){
                    progs.get(a).add(key);
                }else{
                    progs.put(a,new LinkedList<String>());
                    progs.get(a).add(key);
                }
            });
        });

        String keys[] = progs.keySet().toArray(new String[10]);

        int n = keys.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (progs.get(keys[j]).size() < progs.get(keys[j+1]).size())
                {
                    String temp = keys[j];
                    keys[j] = keys[j+1];
                    keys[j+1] = temp;
                }

        for(int i=0; i<keys.length; i++){
            result.put(keys[i],progs.get(keys[i]));
        }
        return result;
    }


    public LinkedHashMap<String, LinkedList<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        LinkedHashMap<String, LinkedList<String>> temp = new LinkedHashMap<String,LinkedList<String>>();

        LinkedHashMap<String, LinkedList<String>> Progs = this.getProgsMap();

        Progs.forEach((key,value)->{
            if(value.size()>n) temp.put(key,value);
        });

        return temp;
    }

    public Map sorted(Map map, BiFunction<Object, Object,Boolean> fun){
        Map result = new HashMap();

        int n = map.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (fun.apply(map.get(j),map.get(j+1)))
                {
                    Object temp = map.get(j);
                    map.replace(j,map.get(j+1));
                    map.replace(j+1,temp);
                }

        return result;
    }

    public Map filtered(Map map, Function<Integer,Boolean> fun){
        Map result = new HashMap();
        map.forEach((a,b)->{
            if(fun.apply((Integer)a)) result.put(a,b);
        });

        return result;
    }
}
