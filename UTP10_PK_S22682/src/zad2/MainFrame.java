package zad2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainFrame extends JFrame {
    MainFrame(){

        LinkedList<Planet> planets = new LinkedList<>();
        planets.add(new Planet("Merkury",70,10,24,Color.RED));
        planets.add(new Planet("Wenus",100,15,62,Color.BLUE));
        planets.add(new Planet("Ziemia",130,20,100,Color.GREEN));
        planets.add(new Planet("Mars",160,17,188,Color.ORANGE));
        planets.add(new Planet("Jowisz",190,25,1187,Color.lightGray));
        planets.add(new Planet("Saturn",230,35,2948,Color.YELLOW));
        planets.add(new Planet("Uran",280,40,8407,Color.CYAN));
        planets.add(new Planet("Neptun",350,50,16490,Color.BLUE));

        for(Planet p :planets){
            p.start();
        }

        getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new MainPanel(planets);
        JPanel optionsPanel = new OptionsPanel(planets);

        add(mainPanel,BorderLayout.CENTER);
        add(optionsPanel,BorderLayout.EAST);

        setVisible(true);
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

