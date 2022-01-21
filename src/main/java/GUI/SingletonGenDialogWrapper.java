package GUI;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SingletonGenDialogWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    private SingletonForm singletonForm;
    private String singletonMode;
    public SingletonGenDialogWrapper(@Nullable Project project, String singletonMode) {
        super(project);
        this.singletonMode = singletonMode;
        singletonForm = new SingletonForm();
        init();

        setTitle("Singleton Generator");
    }
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
//        singletonForm.getAddFieldButton().addActionListener(e -> {
//            System.out.println("Button Pressed");
//            JLabel label = new JLabel("Test");
//            JLabel fieldname = singletonForm.getFieldNameLabel();
//            JTextField fieldnametext = singletonForm.getFieldNameInput();
//            JComboBox encapsulation = singletonForm.getFieldEncapsulationBox();
//            JComboBox dataType = singletonForm.getFieldDataTypeBox();
//            JComboBox modifier = singletonForm.getFieldModifierBox();
//            fieldnametext.setLocation(fieldname.getLocation().x+1, fieldname.getLocation().y+1);
//            dataType.setLocation(dataType.getLocation().x+1, dataType.getLocation().y+1);
//            encapsulation.setLocation(encapsulation.getLocation().x+1, encapsulation.getLocation().y+1);
//            modifier.setLocation(modifier.getLocation().x+1, modifier.getLocation().y+1);
//            fieldname.setVisible(true);
//            fieldnametext.setVisible(true);
//            dataType.setVisible(true);
//            encapsulation.setVisible(true);
//            modifier.setVisible(true);
//
//            JPanel panel = singletonForm.getFieldDefinerPanel();
//            panel.setLocation(panel.getX()+1, panel.getY()+1);
//            panel.setVisible(true);
//            singletonForm.getFieldsPanel().add(fieldname);
//            singletonForm.getFieldsPanel().add(fieldnametext);
//            singletonForm.getFieldsPanel().add(dataType);
//            singletonForm.getFieldsPanel().add(encapsulation);
//            singletonForm.getFieldsPanel().add(modifier);
//            singletonForm.getFieldsPanel().add(fieldnametext);
//            singletonForm.getFieldsPanel().add(encapsulation);
//            singletonForm.getFieldsPanel().add(dataType);
//            singletonForm.getFieldsPanel().add(modifier);
//            singletonForm.getFieldsPanel().revalidate();
//
//
//        });

        return singletonForm.getContent();
    }

}
