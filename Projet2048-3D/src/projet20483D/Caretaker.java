package projet20483D;

import java.util.*;

/**** @author Maxime*/

public class Caretaker { // Classe du gardien : pour conserver la possibilité d'undo une action

    private ArrayList savedStates = new ArrayList();

    public void addMemento(Object m) {      //add de l'objet dans la liste de saves
        savedStates.add(m);
    }

    public Object getMemento(int index) {   //getter pour avoir notre objet
        return savedStates.get(index);
    }
}
