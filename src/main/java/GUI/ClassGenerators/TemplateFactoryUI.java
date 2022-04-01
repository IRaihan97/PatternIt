package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.Patterns.Template.AbstractTemplate;
import JavaPoetTemplates.Patterns.Template.Concrete;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;
import java.util.ArrayList;

public class TemplateFactoryUI extends DialogWrapper {
    private TemplateForm templateForm;
    private AbstractTemplate templateClass;
    public TemplateFactoryUI(@Nullable Project project) {
        super(project);
        templateForm = new TemplateForm();
        init();
        setTitle("Template Generator");
    }

    private void addClasses(){
        String interfaceName = templateForm.getInterfaceNameIn().getText();
        ClassInputs.INSTANCE.setPackageName(templateForm.getPackageNameIn().getText());
        addMethodsAndFields(interfaceName, null);


        for(int i = 0; i < templateForm.getConcreteComps().size(); i++){
            JTextField classNameField = (JTextField) templateForm.getConcreteComps().get(i)[1];
            addMethodsAndFields(classNameField.getText(), templateClass);
        }
    }

    private void addMethodsAndFields(String targetClass, AbstractTemplate template){
        ArrayList<MethodGen> methodsToAdd = new ArrayList<>();
        ArrayList<FieldGen> fieldsToAdd = new ArrayList<>();
        ArrayList<FieldGen> fieldsBank = ClassInputs.INSTANCE.getFields();
        ArrayList<MethodGen> methodsBank = ClassInputs.INSTANCE.getMethods();
        if(methodsBank!=null){
            methodsBank.stream().filter(method -> method.getTargetClass().equals(targetClass)).forEach(
                    method -> {
                        methodsToAdd.add(method);
                    }
            );
        }
        if(fieldsBank!=null){
            fieldsBank.stream().filter(field -> field.getTargetClass().equals(targetClass)).forEach(
                    field -> {
                        fieldsToAdd.add(field);
                    }
            );
        }

        if(template!=null){
            Concrete conObj = new Concrete(targetClass, template);
            ClassInputs.INSTANCE.addClassGen(conObj.getConcreteGen());
        }
        else{
            AbstractTemplate templateClass = new AbstractTemplate(targetClass, ClassInputs.INSTANCE.getPackageName(), fieldsToAdd, methodsToAdd);
            this.templateClass = templateClass;
            ClassInputs.INSTANCE.addClassGen(templateClass.getAbsClassGen());
        }
    }

    @Override
    protected void doOKAction() {
        try{
            addClasses();
            ClassInputs.INSTANCE.getFieldsToAddComps().clear();
            ClassInputs.INSTANCE.getMethodsToAddComps().clear();
            super.doOKAction();
        }catch(Exception e){
            JOptionPane.showMessageDialog(templateForm.getInterfaceNameIn(), e.getMessage()+ "Invalid name, Please choose a different name","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            ClassInputs.INSTANCE.clearAll();
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return templateForm.getContent();
    }
}
