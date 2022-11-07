package projet20483D;

import java.util.*;

/*** @author Maxime*/
public class Originator {

    private String state;

    public void set(String state) {
        System.out.println("Originator: etat affecte a: " + state);
        this.state = state;
    }

    public Object saveToMemento() {
        System.out.println("Originator: sauvegarde dans le memento.");
        return new Memento(state);
    }

    public void restoreFromMemento(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            state = memento.getSavedState();
            System.out.println("Originator: Etat après restauration: " + state);
        }
    }

    private static class Memento { // Classe interne --> permet la sauvegarde

        private String state;

        public Memento(String stateToSave) {
            state = stateToSave;
        }

        public String getSavedState() {
            return state;
        }
    }
}
