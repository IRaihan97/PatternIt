package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import InputHolders.TextFieldVerifier;
import JavaPoetTemplates.ParameterGen;
import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Parameters extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel dialogButtonPanel;
    private JPanel buttonsPanel;
    private JPanel parameterDefinerPanel = new JPanel();
    private JButton addBtn;
    private JScrollPane parameterScroll;
    private ArrayList<JComponent[]> componentsToAdd = new ArrayList<>();
    private ArrayList<JComponent[]> currentComponents;
    private int panelIndex = 0;
    GridBagConstraints con = new GridBagConstraints();
    private TextFieldVerifier inputVerifier = new TextFieldVerifier();

    private String methodName;

    public Parameters(String methodName) {
        this.methodName = methodName;
        con.gridy = 1;
        contentPane.setPreferredSize(new Dimension(550, 200));
        parameterDefinerPanel.setPreferredSize(new Dimension(500, 50));
        parameterScroll.setViewportView(parameterDefinerPanel);
        parameterScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        parameterScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        parameterScroll.setPreferredSize(new Dimension(500, 100));
        parameterScroll.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        parameterScroll.setEnabled(true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToComponentList();
                parameterAddition();
            }
        });

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
        try{
            for (int i = 0; i < componentsToAdd.size(); i++) {
                JComponent[] components = componentsToAdd.get(i);
                JTextField paramIn = (JTextField) components[1];
                ComboBox type = (ComboBox) components[2];
                Class dataType = typeDropDown(type);
                ParameterGen param = new ParameterGen(paramIn.getText(), dataType, methodName);
                ClassInputs.INSTANCE.addParameter(param);
                //SingletonClass.INSTANCE.setMethodsToAddComps(componentsToAdd);
                panelIndex = 0;
                con.gridy = 1;
                dispose();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(contentPane, e.getMessage()+", names cannot be blank or contain special characters","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
        }

    }

    private void parameterAddition() {
        addParameterRow(parameterDefinerPanel, con);
        Dimension d = new Dimension(parameterDefinerPanel.getWidth(), parameterDefinerPanel.getHeight() + 40);
        parameterDefinerPanel.setPreferredSize(d);
        parameterDefinerPanel.repaint();
        parameterDefinerPanel.revalidate();
    }

    public void addParameterRow(JPanel panel, GridBagConstraints con) {
        JPanel newPanel = new JPanel();
        JComponent[] components = componentsToAdd.get(panelIndex);
        for (int i = 0; i < 3; i++) {
            newPanel.add(components[i]);
        }
        panel.add(newPanel, con);
        panel.revalidate();
        panelIndex++;
        con.gridy++;
    }

    private void addToComponentList() {
        JComponent[] component = new JComponent[3];
        JLabel parameterName = new JLabel("Parameter Name");
        JTextField parameterNameInput = new JTextField();
        parameterNameInput.setInputVerifier(inputVerifier);
        parameterNameInput.setSize(new Dimension(200, 10));
        ComboBox typeBox = new ComboBox();
        createTypeBox(typeBox);


        component[0] = parameterName;
        component[1] = parameterNameInput;
        component[2] = typeBox;


        this.componentsToAdd.add(component);
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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
