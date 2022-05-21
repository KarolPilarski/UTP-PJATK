package zad1;

import java.io.*;
import java.util.List;

public class LoadingThread extends Thread{
    List<Towar> data;

    LoadingThread(List<Towar> data){
        this.data = data;
    }

    @Override
    public void run() {
        try {
            FileReader file = new FileReader(new File("../Towary.txt"));
            BufferedReader reader = new BufferedReader(file);
            String line = "";
            int count = 0;
            while((line = reader.readLine()) != null){
                String[] tow = line.split(" ");
                synchronized(data){
                    data.add(new Towar((Integer.parseInt(tow[0])),(Integer.parseInt(tow[1]))));
                }
                count++;
                if(count%200==0) System.out.println("utworzono "+count+" obiektów");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
