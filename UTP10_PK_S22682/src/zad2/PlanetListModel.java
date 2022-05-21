package zad2;

import javax.swing.*;
import java.util.LinkedList;

public class
PlanetListModel extends AbstractListModel {
    LinkedList<String> planets= new LinkedList<String>();

    PlanetListModel(){
        planets.add("Merkury");
        planets.add("Wenus");
        planets.add("Ziemia");
        planets.add("Mars");
        planets.add("Jowisz");
        planets.add("Saturn");
        planets.add("Uran");
        planets.add("Neptun");
    }

    @Override
    public int getSize() {
        return planets.size();
    }

    @Override
    public Object getElementAt(int index) {
        return planets.get(index);
    }
}
