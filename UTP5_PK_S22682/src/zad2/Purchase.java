/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad2;


public class Purchase {
    public String id_klienta, nazwisko_imie, nazwa_towaru;
            public double cena,zakupiona_ilosc, koszt;
    public Purchase(){};

    public Purchase(String id_klienta, String nazwisko_imie,String nazwa_towaru,double cena, double zakupiona_ilosc){
        this.id_klienta = id_klienta;
        this.nazwisko_imie = nazwisko_imie;
        this.nazwa_towaru = nazwa_towaru;
        this.cena = cena;
        this.zakupiona_ilosc = zakupiona_ilosc;
        koszt=zakupiona_ilosc*cena;
    }

}
