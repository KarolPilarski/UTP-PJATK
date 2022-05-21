package zad2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainPanel extends JPanel implements Runnable {
    LinkedList<Planet> planets;
    boolean[][] stars= new boolean[1200][800];
    MainPanel(LinkedList<Planet> planets){
        this.planets=planets;
        setBackground(Color.BLACK);
        (new Thread(this)).start();

        for(int i=0;i<1000;i++){
            stars[(int)(Math.random()*1200)][(int)(Math.random()*800)]=true;
        }
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0;i<1200;i++){
            for(int j=0;j<800;j++){
                if(stars[i][j]&&(Math.random()>0.001)){
                    g.setColor(Color.WHITE);
                    g.drawOval(i,j,1,1);
                }
            }
        }

        for(Planet p :planets){
            g.setColor(Color.WHITE);
            g.drawOval(550-p.getRadius()+p.size/2,350-p.getRadius()+p.size/2,p.getRadius()*2,p.getRadius()*2);
            g.setColor(p.getColor());
            g.fillOval(p.getCordX(), p.getCordY(), p.getSize(), p.getSize());
        }

        g.setColor(Color.YELLOW);

        g.fillOval(515, 315, 80, 80);
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}

