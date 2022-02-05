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
        return singletonForm.getContent();

    }

    @Override
    protected void doOKAction() {
        addClasses();
        ClassInputs.INSTANCE.generateClass();
        ClassInputs.INSTANCE.getFieldsToAddComps().clear();
        ClassInputs.INSTANCE.getMethodsToAddComps().clear();
        super.doOKAction();
    }

    private void addClasses(){
        ClassInputs.INSTANCE.addClassName(singletonForm.getSingletonName().getText());
    }
}
