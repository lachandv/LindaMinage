package threads;
import linda.*;
import Mine.*;
/**
 * Created by valentin on 03/03/15.
 */
public class H2OHaut extends Thread {

    private EspaceTuple ts;
    private Mine mine;
    private float seuil;

    public H2OHaut(EspaceTuple ts, Mine m, float seuil) {
        this.ts = ts;
        this.mine = m;
        this.seuil = seuil;
    }

    public void run() {
        while (1 == 1) {
            ts.rd(ts, new Template("detection_H2O_haut"));
            TupleLinda t = ts.rd(ts, new Template("niveau_H2O ?x"));
            float x = Float.valueOf(t.getValue(1));
            if (x >= seuil) {
                ts.out(ts, new TupleLinda("H2O_haut_detecte"));
                ts.in(ts, new Template("detection_H2O_haut"));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}