package GUI;

import InputHolders.ClassInputs;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;


public class SingletonGenDialogWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    private SingletonForm singletonForm;

    public SingletonGenDialogWrapper(@Nullable Project project) {
        super(project);
        singletonForm = new SingletonForm();
        init();

        setTitle("Singleton Generator");
    }
    //https://ayusch.com/intellij-plugin-development-tutorial-handling-user-input/


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

    @Override
    protected void doOKAction() {
        addClasses();
        ClassInputs.INSTANCE.generateClass();
        ClassInputs.INSTANCE.getFieldsToAddComps().clear();
        ClassInputs.INSTANCE.getMethodsToAddComps().clear();
        super.doOKAction();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void addClasses(){
        ClassInputs.INSTANCE.addClassName(singletonForm.getSingletonName().getText());
    }
}
