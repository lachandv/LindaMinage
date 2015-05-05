package linda;

import java.util.ArrayList;


public class TupleLinda {

	private ArrayList<Champ> monTuple = new ArrayList<Champ>();

	public TupleLinda(String tupleLin) {
		String champs[] = tupleLin.split(",");
		for (String champ : champs){
			String cham[] = champ.split(" ");
			Champ field = new Champ(cham[0],cham[1]);
			monTuple.add(field);
		}


	}

    public int size(){
        return this.monTuple.size();
    }

    public boolean match(Template t){
        if (this.size() != t.size()){
            return false;
        }
        for(int i = 0; i<this.monTuple.size(); i++){
            Champ champTuple = this.monTuple.get(i);
            Champ champTemplate = t.get(i);
            if (!champTuple.getType().equals(champTemplate.getType()) /*||
                    (!champTuple.getValeur().equals(champTemplate.getValeur()) && champTemplate.getValeur() != null &&
                            !champTemplate.getValeur().contains("?"))*/) {
                return false;
            }
        }
        return true;
    }

    public String getValue(int i){
        return this.monTuple.get(i).getValeur();
    }

    public String toString(){
        String tmp = "";
        for(int i = 0; i<this.size(); i++) {
            Champ champTuple = this.monTuple.get(i);
            tmp = tmp + champTuple.getValeur() + " " + champTuple.getType() + ",";
        }
        return tmp;
    }


}
