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
import java.util.ArrayList;


@Data
public class TemplateForm {
    private JPanel mainPanel;
    private JPanel InterfaceDefinerPanel;
    private JPanel objectsDefinerPanel;
    private JLabel interfaceLb;
    private JLabel packageLb;
    private JTextField interfaceNameIn;
    private JTextField packageNameIn;
    private JScrollPane concreteScroll;
    private JPanel concreteDefinerPanel;
    private JButton addConcreteBtn;
    private JButton addAbstractMethodsButton;
    private JButton addAbstractFieldsButton;
    private JPanel abstractPropPanel;
    private int concreteIndex = 0;
    private GridBagConstraints conComp = new GridBagConstraints();
    private ArrayList<JComponent[]> concreteComps = new ArrayList<>();
    private TextFieldVerifier inputVerifier = new TextFieldVerifier();


    TemplateForm(){
        SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                interfaceLb.requestFocus();
                interfaceNameIn.setInputVerifier(inputVerifier);
                packageNameIn.setInputVerifier(inputVerifier);
            }
        } );
        conComp.gridy = 0;
        //mainPanel.setPreferredSize(new Dimension(700, 1000));
        Dimension scrollD = new Dimension(500, 100);
        Dimension panelD = new Dimension(500, 110);
        concreteScroll.setPreferredSize(scrollD);
        concreteDefinerPanel.setPreferredSize(panelD);
        scrollSetup(concreteScroll, concreteDefinerPanel);

        packageNameIn.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageNameIn.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageNameIn.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ClassInputs.INSTANCE.setPackageName(packageNameIn.getText());
            }
        });

        addAbstractMethodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateFields())return;
                Methods dialog = new Methods(interfaceNameIn.getText(), true);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addAbstractFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateFields())return;
                Fields dialog = new Fields(interfaceNameIn.getText());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });



        addConcreteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateFields())return;
                addJComponents();
                addObjectRow(concreteDefinerPanel, conComp, "Concrete");
                panelSizeIncrease(concreteDefinerPanel);
            }
        });




    }

    private void scrollSetup(JScrollPane scroll, JPanel panel){
        scroll.setViewportView(panel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setEnabled(true);
    }

    private void panelSizeIncrease(JPanel panel){
        Dimension d = new Dimension(panel.getWidth(), panel.getHeight()+40);
        panel.setPreferredSize(d);
        panel.repaint();
        panel.revalidate();
    }

    private void addJComponents(){
        JComponent[] components = new JComponent[4];
        JLabel label = new JLabel("Concrete Class Name:");
        JTextField classNameInput = new JTextField();
        classNameInput.setInputVerifier(inputVerifier);
        JButton addField = new JButton("Add Fields");
        JButton addMethod = new JButton("Add Methods");
        addField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fields dialog = new Fields(classNameInput.getText());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Methods dialog = new Methods(classNameInput.getText(), false);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        components[0] = label;
        components[1] = classNameInput;
        components[2] = addField;
        components[3] = addMethod;
        this.concreteComps.add(components);

    }

    private void addObjectRow(JPanel panel, GridBagConstraints con, String objectType) {
        JPanel newPanel = new JPanel();
        JComponent[] components;
        components = concreteComps.get(concreteIndex);
        concreteIndex++;


        for (int i = 0; i < components.length; i++) {
            newPanel.add(components[i]);
        }
        panel.add(newPanel, con);
        panel.revalidate();

        con.gridy++;
    }


    private boolean validateFields(){
        if(!inputVerifier.verify(interfaceNameIn))return false;
        if(!inputVerifier.verify(packageNameIn))return false;
        return true;
    }

    public JComponent getContent(){
        return mainPanel;
    }
}
