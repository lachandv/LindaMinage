package main.java;

/**
 * Created by valentin on 24/02/15.
 */
import java.util.ArrayList;
import java.io.*;

public class EspaceTuple {

    ArrayList<TupleLinda> espace;

    public EspaceTuple(){
        this.espace = new ArrayList<TupleLinda>();
    }

    public void out(EspaceTuple ts, TupleLinda tpl){
        ts.espace.add(tpl);
    }

    public void add(TupleLinda[] tuples, EspaceTuple ts){
        for (TupleLinda tuple : tuples){
            ts.espace.add(tuple);
        }
    }

    public TupleLinda in(EspaceTuple ts, Template tp) {
        boolean founded = false;
        while (!founded) {
            for (TupleLinda t : ts.espace) {
                if (t.match(tp)) {
                    ts.espace.remove(t);
                    return t;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public TupleLinda rd(EspaceTuple ts, Template tp) {
        boolean founded = false;
        while (!founded) {
            for (TupleLinda t : ts.espace) {
                if (t.match(tp)) {
                    return t;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
