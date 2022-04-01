package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import InputHolders.TextFieldVerifier;
import JavaPoetTemplates.Patterns.Singleton.Singleton;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;


public class SingletonFactoryUI extends DialogWrapper {
    private SingletonForm singletonForm;
    private TextFieldVerifier inputVerifier = new TextFieldVerifier();

    public SingletonFactoryUI(@Nullable Project project) {
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
        try{
            addClasses();

            Singleton singletonClass = new Singleton(ClassInputs.INSTANCE.getClassesToGenerate().get(0), ClassInputs.INSTANCE.getPatternToGenerate(), ClassInputs.INSTANCE.getPackageName(), ClassInputs.INSTANCE.getFields(), ClassInputs.INSTANCE.getMethods());
            ClassInputs.INSTANCE.addClassGen(singletonClass.getClassGen());
            ClassInputs.INSTANCE.getFieldsToAddComps().clear();
            ClassInputs.INSTANCE.getMethodsToAddComps().clear();
            super.doOKAction();
        }catch(Exception e){
            JOptionPane.showMessageDialog(singletonForm.getSingletonName(),"Invalid names provided, Please provide valid different names","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
        }

    }

    private void addClasses(){
        ClassInputs.INSTANCE.addClassName(singletonForm.getSingletonName().getText());
    }
}
