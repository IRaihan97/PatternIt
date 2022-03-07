package GUI.Tutorials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingletonCard extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private CardLayout pageCards = new CardLayout();
    private JPanel pages;
    private int n = 2;

    public SingletonCard() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pages.setLayout(pageCards);
        JPanel newP = new JPanel();
        newP.add(new JLabel("P1"));
        JPanel newP1 = new JPanel();
        newP1.add(new JLabel("P2"));
        pages.add(newP, "1");
        pages.add(newP1, "2");





        


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here

        pageCards.show(pages, Integer.toString(n));
        n++;
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public JPanel getContent(){
        return contentPane;
    }
}
