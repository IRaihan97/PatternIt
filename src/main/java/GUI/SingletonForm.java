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
    private JTextField classNameInput;
    private JLabel className;
    private JComboBox fieldDataTypeBox;
    private JComboBox fieldEncapsulationBox;
    private JTextField fieldNameInput;
    private JPanel mainPanel;
    private JComboBox fieldModifierBox;
    private JLabel fieldNameLabel;
    private JLabel methodNameLabel;
    private JComboBox methodEncapsulationBox;
    private JComboBox methodDataTypeBox;
    private JComboBox methodModifierBox;
    private JPanel classDefinerPanel;
    private JPanel fieldDefinerPanel;
    private JPanel methodDefinerPanel;
    private JButton addFieldButton;
    private JButton addMethodButton;
    private JPanel fieldsPanel;
    private JPanel methodsPanel;
    private ArrayList<JPanel> addFieldPanel;
    private ArrayList<JPanel> addMethodPanel;
    private int currentPanelRow = 0;
    private JLabel test;
    private GridBagConstraints con = new GridBagConstraints();

    public SingletonForm(){
        classNameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(classNameInput.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(classNameInput.getText());

            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                SingletonClass.INSTANCE.setClassName(classNameInput.getText());
            }
        });
        fieldNameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                FieldsClass.INSTANCE.setFieldName(fieldNameInput.getText());
                System.out.println(FieldsClass.INSTANCE.getFieldName());
                System.out.println(fieldDataTypeBox.getSelectedItem());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                FieldsClass.INSTANCE.setFieldName(fieldNameInput.getText());
                System.out.println(FieldsClass.INSTANCE.getFieldName());
                System.out.println(fieldDataTypeBox.getSelectedItem());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                FieldsClass.INSTANCE.setFieldName(fieldNameInput.getText());
                System.out.println(FieldsClass.INSTANCE.getFieldName());
                System.out.println(fieldDataTypeBox.getSelectedItem());
            }
        });

//        fieldDataTypeBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //if(fieldDataTypeBox.getSelectedItem().toString().matches("char")){
//                    System.out.println(fieldDataTypeBox.getSelectedItem().toString());
//                //}
//            }
//        });

    }
    private void addFieldRow(){


    }

    private void generateMethodRow(){
    }

    private void getAllInputs(){

        FieldsClass.INSTANCE.setFieldName(fieldNameInput.getText());
        System.out.println(FieldsClass.INSTANCE.getFieldName());
        System.out.println(fieldDataTypeBox.getSelectedIndex());
    }

    public JComponent getContent() {
        con.gridy=1;
        //mainPanel.setPreferredSize(new Dimension(500,100));
//        addFieldButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button Pressed");
//                int y = fieldNameLabel.getVerticalTextPosition();
//                int x = fieldNameLabel.getHorizontalTextPosition();
//                JLabel newLable = new JLabel("Field");
//                fieldDefinerPanel.add(newLable);
//                fieldDefinerPanel.revalidate();
//
//                currentPanelRow++;
//            }
//        });
        addFieldButton.addActionListener(e -> {
            addFieldRow(fieldsPanel, con);
        });

        return mainPanel;
    }

    public void addFieldRow(JPanel panel, GridBagConstraints con){
        JPanel newPanel = new JPanel();
        newPanel.setLocation(fieldsPanel.getLocation().x, fieldsPanel.getLocation().y+100);
        System.out.println(fieldDefinerPanel.getLocation().y);
        GridBagLayout c = new GridBagLayout();
        con.fill = GridBagConstraints.HORIZONTAL;

        JLabel fieldName = new JLabel("Field Name");
        JTextField fieldNameInput = new JTextField();
        ComboBox encBox = new ComboBox();
        createEncapsulationBox(encBox);
        ComboBox typeBox = new ComboBox();
        createTypeBox(typeBox);
        ComboBox modBox = new ComboBox();
        modBox.addItem("static");
        newPanel.add(fieldName);
        newPanel.add(fieldNameInput);
        newPanel.add(encBox);
        newPanel.add(typeBox);
        newPanel.add(modBox);
        panel.add(newPanel, con);
        panel.revalidate();
        con.gridy++;
    }

    private void createEncapsulationBox(ComboBox box){
        box.addItem("public");
        box.addItem("private");
        box.addItem("protected");
    }

    private void createTypeBox(ComboBox box){
        box.addItem("int");
        box.addItem("char");
        box.addItem("double");
        box.addItem("float");
        box.addItem("byte");
        box.addItem("short");
        box.addItem("long");
    }

    public JTextField getClassNameInput() {
        return classNameInput;
    }

    public void setClassNameInput(JTextField classNameInput) {
        this.classNameInput = classNameInput;
    }

    public JComboBox getFieldDataTypeBox() {
        return fieldDataTypeBox;
    }

    public void setFieldDataTypeBox(JComboBox fieldDataTypeBox) {
        this.fieldDataTypeBox = fieldDataTypeBox;
    }

    public JTextField getFieldNameInput() {
        return fieldNameInput;
    }

    public void setFieldNameInput(JTextField fieldNameInput) {
        this.fieldNameInput = fieldNameInput;
    }
}
