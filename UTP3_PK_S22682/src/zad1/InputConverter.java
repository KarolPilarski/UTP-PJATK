package zad1;

import java.util.function.Function;

public class InputConverter <T> {
    T t;
    InputConverter(T Input){
        this.t=Input;
    }

    public <T> T convertBy(Function... functions) {
        Object temp = t;

        for (Function f : functions) {
            temp = f.apply(temp);
        }

        return (T)temp;
    }
}
