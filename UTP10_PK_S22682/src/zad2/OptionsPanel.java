package zad2;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class OptionsPanel extends JPanel implements ActionListener, ChangeListener {

    LinkedList<Planet> planets;

    JList jList = new JList();

    OptionsPanel(LinkedList<Planet> planets){
        setPreferredSize(new Dimension(150,800));
        this.planets=planets;
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setLayout(new FlowLayout());

        setSize(300,600);
        setBackground(Color.BLUE);

        jList.setBackground(Color.BLUE);
        jList.setForeground(Color.WHITE);
        PlanetListModel planetListModel = new PlanetListModel();
        jList.setModel(planetListModel);
        jList.setPreferredSize(new Dimension(150,300));
        System.out.println(jList.getSelectedIndex());
        add(jList);


        JButton start = new JButton("START");
        start.setPreferredSize(new Dimension(150,20));
        start.setBackground(new Color(90,90,90));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        JButton stop = new JButton("STOP");
        stop.setPreferredSize(new Dimension(150,20));
        stop.setBackground(new Color(90,90,90));
        stop.setForeground(Color.WHITE);
        stop.addActionListener(this);
        add(stop);
        JButton anuluj = new JButton("ANULUJ");
        anuluj.setPreferredSize(new Dimension(150,20));
        anuluj.setBackground(new Color(90,90,90));
        anuluj.setForeground(Color.WHITE);
        anuluj.addActionListener(this);
        add(anuluj);
        JButton state = new JButton("STAN");
        state.setPreferredSize(new Dimension(150,20));
        state.setBackground(new Color(90,90,90));
        state.setForeground(Color.WHITE);
        state.addActionListener(this);
        add(state);

        JLabel radiusLabel = new JLabel("Odległość od słońca:");
        add(radiusLabel);

        JSlider radius = new JSlider(JSlider.HORIZONTAL,
                20,420,20);
        radius.setPreferredSize(new Dimension(150,50));
        radius.setName("radius");
        radius.addChangeListener(this);
        radius.setMajorTickSpacing(100);
        radius.setMinorTickSpacing(10);
        radius.setPaintTicks(true);
        radius.setPaintLabels(true);
        add(radius);

        JLabel speedLabel = new JLabel("Prędkość:");
        add(speedLabel);

        JSlider speed = new JSlider(JSlider.HORIZONTAL,
                0,2000,1000);
        speed.setPreferredSize(new Dimension(150,50));
        speed.setName("speed");
        speed.addChangeListener(this);
        speed.setMajorTickSpacing(1000);
        speed.setMinorTickSpacing(100);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        add(speed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("START")){

            if(jList.getSelectedIndex()!=(-1))
            for(Planet p:planets){
                if(p.getPlanetName().equals(jList.getModel().getElementAt(jList.getSelectedIndex()))){
                    p.startThread();
                }
            }
        }else if(e.getActionCommand().equals("STOP")){
            if(jList.getSelectedIndex()!=(-1))
            for(Planet p:planets){
                if(p.getPlanetName().equals(jList.getModel().getElementAt(jList.getSelectedIndex()))){
                    p.stopThread();
                }
            }
        }else if(e.getActionCommand().equals("STAN")){
            if(jList.getSelectedIndex()!=(-1))
                for(Planet p:planets){
                    if(p.getPlanetName().equals(jList.getModel().getElementAt(jList.getSelectedIndex()))){
                        p.printState();
                    }
                }
        }else if(e.getActionCommand().equals("ANULUJ")){
            if(jList.getSelectedIndex()!=(-1))
                for(Planet p:planets){
                    if(p.getPlanetName().equals(jList.getModel().getElementAt(jList.getSelectedIndex()))){
                        p.abort();
                    }
                }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int tmp = (int)source.getValue();
            if(jList.getSelectedIndex()!=(-1))
            for(Planet p:planets){
                if(p.getPlanetName().equals(jList.getModel().getElementAt(jList.getSelectedIndex()))){
                    if(source.getName().equals("radius"))p.setRadius(tmp);
                    if(source.getName().equals("speed"))p.setSpeed(2000-tmp);
                }
            }
        }
    }
}
