package GUI;

import InputHolders.SingletonClass;
import JavaPoetTemplates.FieldGen;
import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Fields extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel dialogButtonPanel;
    private JPanel buttonsPanel;
    private JPanel fieldDefinerPanel = new JPanel();
    private JButton addBtn;
    private JScrollPane fieldScroll;
    private ArrayList<JComponent[]> componentsToAdd = new ArrayList<>();
    private int panelIndex = 0;
    GridBagConstraints con = new GridBagConstraints();

    public Fields() {
        con.gridy=1;
        contentPane.setPreferredSize(new Dimension(650,200));
        fieldDefinerPanel.setPreferredSize(new Dimension(600,110));
        //fieldDefinerPanel.setAutoscrolls(true);

        fieldScroll.setViewportView(fieldDefinerPanel);
        fieldScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        fieldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fieldScroll.setPreferredSize(new Dimension(600,100));
        fieldScroll.setEnabled(true);
        fieldScroll.revalidate();
        fieldDefinerPanel.revalidate();
        //contentPane.add(fieldScroll);
        contentPane.revalidate();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToComponentList();
                addFieldRow(fieldDefinerPanel, con);
                Dimension d = new Dimension(fieldDefinerPanel.getWidth(),fieldDefinerPanel.getHeight()+20);
                fieldDefinerPanel.setPreferredSize(d);
                fieldDefinerPanel.repaint();
                fieldDefinerPanel.revalidate();

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
        for(int i =0; i < componentsToAdd.size(); i++){
            JComponent[] components = componentsToAdd.get(i);
            ArrayList<javax.lang.model.element.Modifier> modifiers = new ArrayList<>();
            JTextField fieldInput = (JTextField) components[1];
            modifiers.add(modDropDown((ComboBox) components[2]));
            modifiers.add(modDropDown((ComboBox) components[3]));
            Class type = typeDropDown((ComboBox) components[4]);
            FieldGen field = new FieldGen(type, fieldInput.getText(), modifiers);
            SingletonClass.INSTANCE.addFields(field);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Modifier modDropDown(ComboBox box){
        switch (box.getSelectedItem().toString()){
            case ("public"):
                return Modifier.PUBLIC;
            case("static"):
                return Modifier.STATIC;
            case("protected"):
                return Modifier.PROTECTED;
            default:
                return Modifier.PRIVATE;

        }
    }

    private Class typeDropDown(ComboBox box){
        switch(box.getSelectedItem().toString()){
            case("int"):
                return int.class;

            case("String"):
                return String.class;

            case("float"):
                return float.class;

            case("double"):
                return double.class;

            case("byte"):
                return byte.class;

            case("char"):
                return char.class;

            default:
                return long.class;

        }

    }

    private void addToComponentList(){
        JComponent[] component = new JComponent[5];
        JLabel fieldName = new JLabel("Field Name");
        JTextField fieldNameInput = new JTextField();
        fieldNameInput.setSize(new Dimension(200, 10));
        ComboBox encBox = new ComboBox();
        createEncapsulationBox(encBox);
        ComboBox typeBox = new ComboBox();
        createTypeBox(typeBox);
        ComboBox modBox = new ComboBox();
        modBox.addItem("static");
        component[0] = fieldName;
        component[1] = fieldNameInput;
        component[2] = encBox;
        component[3] = modBox;
        component[4] = typeBox;
        this.componentsToAdd.add(component);
    }


    public void addFieldRow(JPanel panel, GridBagConstraints con){
        JPanel newPanel = new JPanel();
        System.out.println(panel.getLocation().y);
        JComponent[] components = componentsToAdd.get(panelIndex);
        for (int i = 0; i < 5; i++) {
            newPanel.add(components[i]);
        }

//        newPanel.add(fieldName);
//        newPanel.add(fieldNameInput);
//        newPanel.add(encBox);
//        newPanel.add(typeBox);
//        newPanel.add(modBox);
        panel.add(newPanel, con);
        panel.revalidate();
        panelIndex++;
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



//    public static void main(String[] args) {
//        Fields dialog = new Fields();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
