package main.java;

/**
 * Created by valentin on 24/02/15.
 */
import java.util.ArrayList;
public class Template {

    private ArrayList<Champ> champs;

    public Template(String template){
        this.champs = new ArrayList<Champ>();
        String[] elements = template.split(",");
        for (String element : elements) {
            String[] contenu = element.split(" ");
            if (contenu.length == 1){
                champs.add(new Champ(null, contenu[0]));
            }
            else{
                champs.add(new Champ(contenu[0], contenu[1]));
            }
        }
    }

    public int size(){
        return champs.size();
    }

    public Champ get(int i){
        return this.champs.get(i);
    }
}
