package linda; /**
 * Created by valentin on 24/02/15.
 */
import java.util.ArrayList;

public class EspaceTuple {

    public ArrayList<TupleLinda> espace;

    public EspaceTuple(){
        this.espace = new ArrayList<TupleLinda>();
    }

    public void out(EspaceTuple ts, TupleLinda tpl){
        ts.espace.add(tpl);
    }

    public void add(EspaceTuple ts, Template tp){
        EspaceTuple copie = new EspaceTuple();
        copie.espace.addAll(ts.espace);
        synchronized(copie.espace) {
            boolean trouve = false;
            for (int i = 0; i<copie.espace.size(); i++){
                if (copie.espace.get(i) != null && copie.espace.get(i).match(tp)) {
                    ts.espace.set(i, tp.toTuple());
                    trouve = true;
                }
            }
            if (trouve == false) {
                ts.espace.add(tp.toTuple());
            }
        }
    }

    public TupleLinda in(EspaceTuple ts, Template tp) {
        EspaceTuple copie = new EspaceTuple();
        copie.espace.addAll(ts.espace);
            boolean founded = false;
            while (!founded) {
                copie.espace.addAll(ts.espace);
                synchronized(copie.espace) {
                for (int i = 0; i<copie.espace.size(); i++){
                    if (copie.espace.get(i).match(tp)) {
                        ts.espace.remove(i);
                        return copie.espace.get(i);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public TupleLinda rd(EspaceTuple ts, Template tp) {
        EspaceTuple copie = new EspaceTuple();
            boolean founded = false;
            while (!founded) {
                copie.espace.addAll(ts.espace);
                synchronized (copie.espace) {
                    for (int i = 0; i < copie.espace.size(); i++) {
                        if (copie.espace.get(i) != null) {
                            if (copie.espace.get(i).match(tp)) {
                                return copie.espace.get(i);
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

    public TupleLinda inp(EspaceTuple ts, Template tp) {
        EspaceTuple copie = new EspaceTuple();
        copie.espace.addAll(ts.espace);
        synchronized (copie.espace) {
            for (int i = 0; i < copie.espace.size(); i++) {
                if (copie.espace.get(i) != null) {
                    if (copie.espace.get(i).match(tp)) {
                        ts.espace.remove(i);
                        return copie.espace.get(i);
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
