package projet20483D;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*** @author Maxime*/
public class Originator {

    private Grille3D state;

    public void set(Grille3D state) {
        this.state = state.deepCopy();
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

        private Grille3D state;

        public Memento(Grille3D stateToSave) {
            try {
                state = stateToSave.deepCopy();
            } catch (IOException ex) {
                Logger.getLogger(Originator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Originator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public Grille3D getSavedState() {
            return state;
        }
    }
}
