package GUI.Tutorials;

import javax.swing.*;

public class SingletonCards {
    JPanel[] cards = new JPanel[5];
    SingletonCards(){

    }
    public JPanel[] getCards(){
        return cards;
    }

    private void tutorialPanels(){

    }
}
