package zad1;

public class StringTask implements Runnable{
    public enum TaskState{ABORTED,RUNNING,READY,CREATED}
    volatile TaskState currentState;
    private boolean done=false;
    volatile String result="";
    volatile String word;
    int quantity;
    Thread thr;

    public StringTask(String word, int quantity) {
        this.word=word;
        this.quantity=quantity;
        currentState=TaskState.CREATED;
    }

    @Override
    public void run() {
        currentState=TaskState.RUNNING;
        while(currentState==TaskState.RUNNING&&quantity>0) {
            result=result+word;
            quantity--;
        }
        if(quantity==0)currentState=TaskState.READY;
        done=true;
    }

    public void start(){
        (thr=new Thread(this)).start();
    }

    public TaskState getState(){
        return currentState;
    }

    public boolean isDone() {
        return done;
    }

    public String getResult() {
        return result;
    }

    public void abort(){
        thr.interrupt();
        if(thr.isInterrupted())
            currentState=TaskState.ABORTED;
        done=true;
    }
}
