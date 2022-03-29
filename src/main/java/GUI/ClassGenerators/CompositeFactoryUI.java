package GUI.ClassGenerators;

import InputHolders.ClassInputs;
import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.Patterns.Composite.Component;
import JavaPoetTemplates.Patterns.Composite.Composite;
import JavaPoetTemplates.Patterns.Composite.Leaf;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;


import javax.swing.*;
import java.util.ArrayList;

public class CompositeFactoryUI extends DialogWrapper {
    private CompositeForm compositeForm;
    private Component component;
    public CompositeFactoryUI(@Nullable Project project) {
        super(project);
        compositeForm = new CompositeForm();
        init();
        setTitle("Composite Generator");
    }

    private void addClasses(){
        String interfaceName = compositeForm.getInterfaceNameIn().getText();
        ClassInputs.INSTANCE.setPackageName(compositeForm.getPackageNameIn().getText());
        addMethodsAndFields(interfaceName, "Interface", null);


        for(int i = 0; i < compositeForm.getLeafComponents().size(); i++){
            JTextField classNameField = (JTextField) compositeForm.getLeafComponents().get(i)[1];
            addMethodsAndFields(classNameField.getText(), "Leaf", component);
        }

        for(int i = 0; i < compositeForm.getCompositeComponents().size(); i++){
            JTextField classNameField = (JTextField) compositeForm.getCompositeComponents().get(i)[1];
            addMethodsAndFields(classNameField.getText(), "Composite", component);

        }
    }

    private void addMethodsAndFields(String targetClass, String classType, Component component){
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

        if(classType.equals("Leaf")){
            Leaf leafObj = new Leaf(component, targetClass, ClassInputs.INSTANCE.getPackageName(), fieldsToAdd, methodsToAdd);
            ClassInputs.INSTANCE.addClassGen(leafObj.getLeafGen());

        }
        else if(classType.equals("Composite")){
            Composite compObj = new Composite(component, targetClass, ClassInputs.INSTANCE.getPackageName());
            ClassInputs.INSTANCE.addClassGen(compObj.getCompositeGen());
        }
        else{
            Component compInt = new Component(targetClass, methodsToAdd);
            this.component = compInt;
            ClassInputs.INSTANCE.addClassGen(compInt.getInterfaceGen());
        }
    }

    @Override
    protected void doOKAction() {
        try{
            addClasses();
            ClassInputs.INSTANCE.generateClass();
            ClassInputs.INSTANCE.getFieldsToAddComps().clear();
            ClassInputs.INSTANCE.getMethodsToAddComps().clear();
            super.doOKAction();
        }catch(Exception e){
            JOptionPane.showMessageDialog(compositeForm.getContent(), "Invalid name, Please choose a different name","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            ClassInputs.INSTANCE.clearAll();
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return compositeForm.getContent();
    }
}
