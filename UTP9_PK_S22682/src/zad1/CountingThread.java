package zad1;

import java.util.List;

public class CountingThread extends Thread{
    List<Towar> data;
    int formerLength=0;
    int sum=0;
    int tmp;

    CountingThread(List<Towar> data){
        this.data = data;
    }


    @Override
    public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        synchronized(data){
            tmp=data.size();
        }
        while(formerLength!=(tmp=data.size())){
            for(int i=formerLength; i<tmp; i++){
                if((i+1)%100==0) System.out.println("policzono wage "+(i+1)+" towarów");
                synchronized(data){
                    sum=sum+(data.get(i)).waga;
                }
            }
            formerLength=tmp;
            synchronized(data){
                tmp=data.size();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
    }
}
