/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CustomersPurchaseSortFind {

    ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();

    public void readFile(String path) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scan.hasNext()) {
            String word = scan.next();
            word=word+" "+scan.next();

            String[] data= word.split(";");

            Purchase temp=new Purchase(data[0],data[1],data[2],Double.parseDouble(data[3]),Double.parseDouble(data[4]));

            purchaseList.add(temp);
        }
    }

    public void showSortedBy(String x) {
        System.out.println(x);
        if(x=="Nazwiska"){
            purchaseList.sort(new CompSurname());
            for(Purchase p: purchaseList){
                System.out.println(p.id_klienta+";"+p.nazwisko_imie+";"+p.nazwa_towaru+";"+p.cena+";"+p.zakupiona_ilosc);
            }
        }else if(x=="Koszty"){
            purchaseList.sort(new CompCost());
            for(Purchase p: purchaseList){
                System.out.println(p.id_klienta+";"+p.nazwisko_imie+";"+p.nazwa_towaru+";"+p.cena+";"+p.zakupiona_ilosc+" (koszt: "+p.koszt+")");
            }

        }else System.out.println("Nieznane sortowanie");
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient "+id);
        for(Purchase p : purchaseList){
            if(id.equals(p.id_klienta)) System.out.println(p.id_klienta+";"+p.nazwisko_imie+";"+p.nazwa_towaru+";"+p.cena+";"+p.zakupiona_ilosc);
        }
        System.out.println();
    }

    class CompSurname implements Comparator<Purchase>
    {

        public int compare(Purchase a, Purchase b)
        {
            if(!a.nazwisko_imie.equals(b.nazwisko_imie))return a.nazwisko_imie.toCharArray()[0] - b.nazwisko_imie.toCharArray()[0];
            else return parseInt(a.id_klienta.replace("c",""))-parseInt(b.id_klienta.replace("c",""));

        }
    }

    class CompCost implements Comparator<Purchase>
    {

        public int compare(Purchase a, Purchase b)
        {
            if(b.koszt==b.koszt) return (int)(b.koszt - a.koszt);
            else return parseInt(a.id_klienta.replace("c",""))-parseInt(b.id_klienta.replace("c",""));

        }
    }
}
