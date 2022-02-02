package GUI;

import InputHolders.FieldsClass;
import InputHolders.GeneratedClass;
import InputHolders.SingletonClass;
import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SingletonForm {
//    private JTextField classNameInput;
//    private JComboBox fieldDataTypeBox;
//    private JTextField fieldNameInput;
    private JPanel mainPanel;
    private JPanel classDefinerPanel;
    private JLabel singletonLb;
    private JTextField singletonName;
    private JTextField packageName;
    private JPanel addExtraPanel;
    private JLabel pkgLb;
    private JButton addFieldBtn;
    private JButton addMethodBtn;
    private GridBagConstraints con = new GridBagConstraints();

    public SingletonForm(){
        addFieldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fields dialog = new Fields();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addMethodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Methods dialog = new Methods();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });



        singletonName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(singletonName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(singletonName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(singletonName.getText());
            }
        });

        packageName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setPackageName(packageName.getText());
            }
        });

    }
    private void addFieldRow(){


    }

    private void generateMethodRow(){
    }


    public JComponent getContent() {


        return mainPanel;
    }





}
