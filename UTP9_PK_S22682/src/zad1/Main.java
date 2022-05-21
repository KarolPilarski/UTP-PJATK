/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      List<Towar> data = Collections.synchronizedList(new ArrayList<>());

      LoadingThread load = new LoadingThread(data);
      CountingThread count = new CountingThread(data);
      load.start();
      count.start();

    }
}
