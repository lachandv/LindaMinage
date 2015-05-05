package threads;
import linda.*;
import Mine.*;
/**
* Created by valentin on 03/03/15.
*/
public class CapteurH2O extends Thread {

private EspaceTuple ts;
private Mine mine;

public CapteurH2O(EspaceTuple ts, Mine m) {
this.ts = ts;
this.mine = m;
}

public void run() {
    while(1==1) {
        ts.add(ts, new Template("niveau_H20 " + mine.getH2O()));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

}