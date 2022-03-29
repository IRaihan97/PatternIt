package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import InputHolders.TextFieldVerifier;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.ParameterGen;
import com.intellij.openapi.ui.ComboBox;

import javax.lang.model.element.Modifier;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Methods extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel dialogButtonPanel;
    private JPanel buttonsPanel;
    private JPanel methodDefinerPanel = new JPanel();
    private JButton addBtn;
    private JScrollPane methodScroll;
    private ArrayList<JComponent[]> componentsToAdd = new ArrayList<>();
    private ArrayList<JComponent[]> currentComponents;
    private int panelIndex = 0;
    GridBagConstraints con = new GridBagConstraints();
    private String className;
    private boolean isAbstract;
    private TextFieldVerifier inputVerifier = new TextFieldVerifier();

    public Methods(String className, boolean isAbstract) {
        this.className = className;
        this.isAbstract = isAbstract;
        //currentComponents = ClassInputs.INSTANCE.getMethodsToAddComps();
        con.gridy = 1;
        contentPane.setPreferredSize(new Dimension(800, 200));
        methodDefinerPanel.setPreferredSize(new Dimension(750, 150));
        methodScroll.setViewportView(methodDefinerPanel);
        methodScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        methodScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        methodScroll.setPreferredSize(new Dimension(750, 100));
        methodScroll.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        methodScroll.setEnabled(true);
//        if (ClassInputs.INSTANCE.getMethodsToAddComps() != null) {
//            methodScroll.setPreferredSize(new Dimension(600, 130));
//            componentsToAdd = ClassInputs.INSTANCE.getMethodsToAddComps();
//            for (int i = 0; i < componentsToAdd.size(); i++) {
//                methodAddition();
//                methodScroll.repaint();
//                methodScroll.revalidate();
//            }
//        }
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
                methodAddition();
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
        //ClassInputs.INSTANCE.getMethods().clear();
        try{
            for (int i = 0; i < componentsToAdd.size(); i++) {
                JComponent[] components = componentsToAdd.get(i);
                ArrayList<javax.lang.model.element.Modifier> modifiers = new ArrayList<>();
                JTextField methodInput = (JTextField) components[1];
                modifiers.add(modDropDown((ComboBox) components[2]));
                if(!isAbstract){
                    modifiers.add(modDropDown((ComboBox) components[3]));
                }

                Class type = typeDropDown((ComboBox) components[4]);
                ArrayList<ParameterGen> parameterBank = ClassInputs.INSTANCE.getParameters();
                ArrayList<ParameterGen> parametersToAdd = new ArrayList<>();
                if(parameterBank!=null){
                    parameterBank.stream().filter(parameter -> parameter.getTargetMethod().equals(methodInput.getText())).forEach(
                            parameter -> {
                                parametersToAdd.add(parameter);
                            }
                    );
                }
                MethodGen method = new MethodGen(methodInput.getText(), modifiers, type, parametersToAdd, isAbstract, className);
                ClassInputs.INSTANCE.addMethods(method);
                ClassInputs.INSTANCE.setMethodsToAddComps(componentsToAdd);
                panelIndex = 0;
                con.gridy = 1;
            }
            dispose();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(contentPane, "Invalid name, names cannot be blank or contain special characters","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
        }

    }

    private void onCancel() {
        // add your code here if necessary

        ClassInputs.INSTANCE.setMethodsToAddComps(currentComponents);
        dispose();
    }

    private void methodAddition() {
        addMethodRow(methodDefinerPanel, con);
        Dimension d = new Dimension(methodDefinerPanel.getWidth(), methodDefinerPanel.getHeight() + 40);
        methodDefinerPanel.setPreferredSize(d);
        methodDefinerPanel.repaint();
        methodDefinerPanel.revalidate();
    }

    private Modifier modDropDown(ComboBox box) {
        switch (box.getSelectedItem().toString()) {
            case ("public"):
                return Modifier.PUBLIC;
            case ("static"):
                return Modifier.STATIC;
            case ("protected"):
                return Modifier.PROTECTED;
            default:
                return Modifier.PRIVATE;

        }
    }

    private Class typeDropDown(ComboBox box) {
        switch (box.getSelectedItem().toString()) {
            case ("int"):
                return int.class;

            case ("String"):
                return String.class;

            case ("float"):
                return float.class;

            case ("double"):
                return double.class;

            case ("byte"):
                return byte.class;

            case ("char"):
                return char.class;

            default:
                return long.class;

        }

    }

    private void addToComponentList() {
        JComponent[] component = new JComponent[6];
        JLabel methodName = new JLabel("Method Name");
        JTextField methodNameInput = new JTextField();
        methodNameInput.setSize(new Dimension(200, 10));
        methodNameInput.setInputVerifier(inputVerifier);
        ComboBox encBox = new ComboBox();
        createEncapsulationBox(encBox);
        ComboBox typeBox = new ComboBox();
        createTypeBox(typeBox);
        ComboBox modBox = new ComboBox();
        modBox.addItem("static");
        JButton paramBtn = new JButton("Add Parameter");
        paramBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!methodNameInput.getText().equals(null) || !methodNameInput.getText().equals("")){
                    Parameters dialog = new Parameters(methodNameInput.getText());
                    dialog.pack();
                    dialog.setVisible(true);
                }

            }
        });
        component[0] = methodName;
        component[1] = methodNameInput;
        component[2] = encBox;
        component[3] = modBox;
        component[4] = typeBox;
        component[5] = paramBtn;
        this.componentsToAdd.add(component);
    }


    private void addMethodRow(JPanel panel, GridBagConstraints con) {
        JPanel newPanel = new JPanel();
        JComponent[] components = componentsToAdd.get(panelIndex);
        for (int i = 0; i < 6; i++) {
            newPanel.add(components[i]);
        }
        panel.add(newPanel, con);
        panel.revalidate();
        panelIndex++;
        con.gridy++;
    }

    private void createEncapsulationBox(ComboBox box) {
        box.addItem("public");
        box.addItem("private");
        box.addItem("protected");
    }

    private void createTypeBox(ComboBox box) {
        box.addItem("int");
        box.addItem("char");
        box.addItem("double");
        box.addItem("float");
        box.addItem("byte");
        box.addItem("short");
        box.addItem("long");
    }
}