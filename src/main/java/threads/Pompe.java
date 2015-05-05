package threads;
import linda.*;
import Mine.*;
/**
 * Created by valentin on 03/03/15.
 */
public class Pompe extends Thread {

    private EspaceTuple ts;
    private Mine mine;
    private boolean etat = false;

    public Pompe(EspaceTuple ts, Mine m) {
        this.ts = ts;
        this.mine = m;
    }

    public void run() {
        while (1 == 1) {
            //System.out.println(ts.inp(ts, new Template("activer_pompe string")));
            if (ts.inp(ts, new Template("activer_pompe")) != null) {
                this.etat = true;
            }
            if (ts.inp(ts, new Template("desactiver_pompe")) != null) {
                this.etat = false;
            }
            if (this.etat == true) {
                mine.pompe();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}