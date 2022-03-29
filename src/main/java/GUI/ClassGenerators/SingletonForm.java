package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import InputHolders.TextFieldVerifier;
import lombok.Data;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
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
    private TextFieldVerifier inputVerifier = new TextFieldVerifier();

    public SingletonForm(){
        SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                singletonLb.requestFocus();
                singletonName.setInputVerifier(inputVerifier);
                packageName.setInputVerifier(inputVerifier);
            }
        } );

        addFieldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateFields())return;
                Fields dialog = new Fields("");
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        addMethodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateFields())return;
                Methods dialog = new Methods("", false);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

//        singletonName.setInputVerifier(inputVerifier);
//        packageName.setInputVerifier(inputVerifier);

//        singletonName.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                ClassInputs.INSTANCE.addClassName(singletonName.getText());
//                System.out.println(singletonName.getText());
//            }
//        });

        packageName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageName.getText());
            }
        });
    }

    private boolean validateFields(){
        if(!inputVerifier.verify(singletonName))return false;
        if(!inputVerifier.verify(packageName))return false;
        return true;
    }
    private void addFieldRow(){


    }

    private void generateMethodRow(){
    }


    public JComponent getContent() {
        return mainPanel;
    }





}
