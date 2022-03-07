package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import lombok.Data;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Data
public class CompositeForm {
    private JPanel mainPanel;
    private JPanel InterfaceDefinerPanel;
    private JPanel objectsDefinerPanel;
    private JLabel interfaceLb;
    private JLabel packageLb;
    private JTextField interfaceNameIn;
    private JTextField packageNameIn;
    private JScrollPane compositeScroll;
    private JScrollPane leafScroll;
    private JPanel compositeDefinerPanel;
    private JPanel leafDefinerPanel;
    private JButton addCompositeBtn;
    private JButton addLeafBtn;
    private JButton addAbstractMethodsButton;
    private int leafIndex = 0;
    private int compositeIndex = 0;
    private GridBagConstraints conLeaf = new GridBagConstraints();
    private GridBagConstraints conComp = new GridBagConstraints();
    private ArrayList<JComponent[]> compositeComponents = new ArrayList<>();
    private ArrayList<JComponent[]> leafComponents = new ArrayList<>();



    CompositeForm(){
        conLeaf.gridy = 0;
        conComp.gridy = 0;
        //mainPanel.setPreferredSize(new Dimension(700, 1000));
        Dimension scrollD = new Dimension(500, 100);
        Dimension panelD = new Dimension(500, 110);
        leafScroll.setPreferredSize(scrollD);
        compositeScroll.setPreferredSize(scrollD);
        leafDefinerPanel.setPreferredSize(panelD);
        compositeDefinerPanel.setPreferredSize(panelD);
        scrollSetup(leafScroll, leafDefinerPanel);
        scrollSetup(compositeScroll, compositeDefinerPanel);

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
                Methods dialog = new Methods(interfaceNameIn.getText(), true);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addLeafBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJComponents("Leaf");
                addObjectRow(leafDefinerPanel, conLeaf, "Leaf");
                panelSizeIncrease(leafDefinerPanel);
            }
        });

        addCompositeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJComponents("Composite");
                addObjectRow(compositeDefinerPanel, conComp, "Composite");
                panelSizeIncrease(compositeDefinerPanel);
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

    private void addJComponents(String labelText){
        JComponent[] components = new JComponent[4];
        JLabel label = new JLabel(labelText + " Name:");
        JTextField classNameInput = new JTextField();
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
        if(labelText.equals("Leaf")){
            this.leafComponents.add(components);
        }
        else{
            this.compositeComponents.add(components);
        }
    }

    private void addObjectRow(JPanel panel, GridBagConstraints con, String objectType) {
        JPanel newPanel = new JPanel();
        JComponent[] components;
        if(objectType.equals("Leaf")){
            components = leafComponents.get(leafIndex);
            leafIndex++;

        }
        else{
            components = compositeComponents.get(compositeIndex);
            compositeIndex++;
        }

        for (int i = 0; i < components.length; i++) {
            newPanel.add(components[i]);
        }
        panel.add(newPanel, con);
        panel.revalidate();

        con.gridy++;
    }

    public JComponent getContent(){
        return mainPanel;
    }
}
