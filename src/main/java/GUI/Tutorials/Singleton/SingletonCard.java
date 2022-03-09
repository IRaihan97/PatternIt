package GUI.Tutorials.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingletonCard extends JDialog {
    private JPanel contentPane;
    private JButton nextBtn;
    private JButton prevBtn;
    private CardLayout pageCards = new CardLayout();
    private JPanel pages;
    private int n = 0;
    private int lastPaneidx;

    public SingletonCard() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(nextBtn);
        pages.setLayout(pageCards);
//        JPanel newP = new JPanel();
//        newP.add(new JLabel("P1"));
//        JPanel newP1 = new JPanel();
//        newP1.add(new JLabel("P2"));
//        pages.add(newP, "1");
//        pages.add(newP1, "2");
        SingletonCards cards = new SingletonCards();
        JPanel[] cardstoAdd = cards.getCards();
        lastPaneidx = cardstoAdd.length-1;
        for (int i = 0; i < cardstoAdd.length; i++){
            pages.add(cardstoAdd[i], Integer.toString(i));
        }





        


        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                previous();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void next() {
        // add your code here
        System.out.println(lastPaneidx);
        System.out.println(lastPaneidx);
        n++;
        pageCards.show(pages, Integer.toString(n));
        if(n >= lastPaneidx){
            n = lastPaneidx;
        }

        //dispose();
    }

    private void previous() {
        if(n==0){
            return;
        }
        n--;
        pageCards.show(pages, Integer.toString(n));
        //dispose();
    }

    public JPanel getContent(){
        return contentPane;
    }
}
