package GUI.Tutorials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TutorialDialogs extends JDialog {
    private JPanel contentPane;
    private JPanel elements;
    private CardLayout cards = new CardLayout();
    private JButton nextBtn;
    private JButton prevBtn;
    private JPanel navPan;
    private JPanel bottomPan;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JTextArea textToDisplay;
    private ArrayList<JPanel> cardsToDisplay;
    private int n = 0;
    private int lastPaneidx;



    public TutorialDialogs(String title, JTextArea textToDisplay, ArrayList<JPanel> cardsToDisplay) {
        this.cardsToDisplay = cardsToDisplay;
        elements.setLayout(cards);
        if(cardsToDisplay==null){
            elements.add(textToDisplay);
            navPan.setVisible(false);
        }
        else{
            for(int i = 0; i < cardsToDisplay.size(); i++){
                elements.add(cardsToDisplay.get(i), Integer.toString(i));
            }
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOk);
        setTitle(title);

        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        prevBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }



    private void next() {
        // add your code here
        System.out.println(lastPaneidx);
        System.out.println(lastPaneidx);
        n++;
        cards.show(elements, Integer.toString(n));
        if(n >= lastPaneidx){
            n = lastPaneidx;
        }
        //dispose();
    }

    private void prev() {
        n--;
        if(n <=0){
            n=0;
        }
        System.out.println(n);
        cards.show(elements, Integer.toString(n));

        //dispose();
    }


}
