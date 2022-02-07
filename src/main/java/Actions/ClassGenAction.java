package Actions;

import GUI.CompositeGenDialogWrapper;
import GUI.SingletonGenDialogWrapper;
import GUI.TemplateGenDialogWrapper;
import InputHolders.ClassInputs;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ClassGenAction extends AnAction {

    private PsiDirectory selectedDir;
    Project project;

    @Override
    public void update(AnActionEvent e) {
        PsiElement selectedElement = CommonDataKeys.PSI_ELEMENT.getData(e.getDataContext());
        if (selectedElement instanceof PsiDirectory) {
            selectedDir = (PsiDirectory) selectedElement;
        } else if (selectedElement instanceof PsiClass) {
            PsiFile psiFile = selectedElement.getContainingFile();
            selectedDir = psiFile.getContainingDirectory();
        }
        else {
            e.getPresentation().setEnabledAndVisible(false);
        }


        project = e.getData(PlatformDataKeys.PROJECT);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        String selectedPath = selectedDir.toString();
        String outputPath = selectedPath.toString();
        File outPut = new File(outputPath.substring(13));
        JavaFile file = null;
        String action = anActionEvent.getPresentation().getText();
//        System.out.println(action);
//
//        ArrayList<Modifier> modifiers1 = new ArrayList<>();
//        modifiers1.add(Modifier.PRIVATE);
//        modifiers1.add(Modifier.STATIC);
//        FieldGen field1 = new FieldGen(int.class, "Anas", modifiers1);
//
//        ArrayList<Modifier> modifiers2 = new ArrayList<>();
//        modifiers1.add(Modifier.PUBLIC);
//        modifiers1.add(Modifier.STATIC);
//        FieldGen field2 = new FieldGen(String.class, "Cannot", modifiers2);
//
//        ArrayList<Modifier> modifiers3 = new ArrayList<>();
//        modifiers1.add(Modifier.PROTECTED);
//        modifiers1.add(Modifier.STATIC);
//        FieldGen field3 = new FieldGen(String.class, "TakeYourCall", modifiers3);
//
//        ArrayList<FieldGen> fields = new ArrayList<>();
//        fields.add(field1);
//        fields.add(field2);
//        fields.add(field3);
//
//        ArrayList<Modifier> methodsMod1 = new ArrayList<>();
//        methodsMod1.add(Modifier.PUBLIC);
//
//
//        ArrayList<String> statements1 = new ArrayList<>();
//        statements1.add("System.out.println(\"Testing Statements1\")");
//        statements1.add("System.out.println(\"Testing Statements2\")");
//        statements1.add("System.out.println(\"Testing Statements3\")");
//        statements1.add("System.out.println(\"Testing Statements4\")");
//        statements1.add("System.out.println(\"Testing Statements5\")");
//        statements1.add("return null");



//        MethodGen method1 = new MethodGen("SimpleMethod1",
//                methodsMod1, String.class, null, null, statements1);

//
//        ArrayList<Modifier> methodsMod2 = new ArrayList<>();
//        methodsMod2.add(Modifier.PRIVATE);
//        methodsMod2.add(Modifier.SYNCHRONIZED);

//        ArrayList<Class> parameterTypes1 = new ArrayList<>();
//        ArrayList<String> parameterNames1 = new ArrayList<>();
//        parameterTypes1.add(int.class);
//        parameterTypes1.add(char.class);
//        parameterTypes1.add(boolean.class);
//        parameterNames1.add("intParameter");
//        parameterNames1.add("charParameter");
//        parameterNames1.add("boolParameter");

//        ArrayList<MethodGen> abstractMethods = new ArrayList<>();
//        MethodGen absMethod1 = new MethodGen("Example1", void.class, Modifier.PUBLIC);
//        MethodGen absMethod2 = new MethodGen("Example2", void.class, Modifier.PUBLIC);
//        MethodGen absMethod3 = new MethodGen("Example3", void.class, Modifier.PUBLIC);
//        MethodGen absMethod4 = new MethodGen("Example4", void.class, Modifier.PUBLIC);
//        abstractMethods.add(absMethod1);
//        abstractMethods.add(absMethod2);
//        abstractMethods.add(absMethod3);
//        abstractMethods.add(absMethod4);


//        MethodGen method2 = new MethodGen("SimpleMethod2",
//                methodsMod2, int.class, parameterTypes1, parameterNames1, statements1);
//
//        MethodGen method3 = new MethodGen("SimpleMethod3",
//                methodsMod1, float.class, null, null, statements1);

//        ArrayList<MethodSpec> methods = new ArrayList<>();
//        methods.add(method1.getMethod());

//        ArrayList<MethodSpec> abstracMethods = new ArrayList<>();;
//        abstracMethods.add(MethodSpec.methodBuilder("abstractExample1")
//                .addModifiers(Modifier.PUBLIC)
//                .returns(void.class)
//                .build()
//        );
//        abstracMethods.add(MethodSpec.methodBuilder("abstractExample2")
//                .addModifiers(Modifier.PUBLIC)
//                .returns(void.class)
//                .build()
//        );
//
//        BasicGenDialogWrapper dialogWrapper = new BasicGenDialogWrapper(anActionEvent.getProject());
//        dialogWrapper.show();




        ClassInputs.INSTANCE.setPatternToGenerate(action);

        if(action.equals("Composite")){
            CompositeGenDialogWrapper compositeUI = new CompositeGenDialogWrapper(anActionEvent.getProject());
            compositeUI.show();
        }

        else if(action.equals("Template")){
            TemplateGenDialogWrapper templateUI = new TemplateGenDialogWrapper(anActionEvent.getProject());
            templateUI.show();
        }

        else{
            SingletonGenDialogWrapper singletonUI = new SingletonGenDialogWrapper(anActionEvent.getProject());
            singletonUI.show();
        }

        ArrayList<TypeSpec> filesToWrite  = ClassInputs.INSTANCE.getClassGen();
        for(int i = 0; i < filesToWrite.size(); i++){
            file = JavaFile.builder(ClassInputs.INSTANCE.getPackageName(),filesToWrite.get(i)).build();
            try {
                file.writeTo(outPut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ClassInputs.INSTANCE.clearAll();

//        filesToWrite.forEach(
//                (fileToWrite) -> {
//                    file = JavaFile.builder(ClassInputs.INSTANCE.getPackageName(), fileToWrite).build();
//                });
        //Singleton singletonClass = new Singleton(SingletonClass.INSTANCE.getClassName(), "Eager", "Singleton");


//            }
//        }
//        else if(action.equals("Lazy")) {
////            SingletonGenDialogWrapper singletonUI = new SingletonGenDialogWrapper(anActionEvent.getProject(), "Eager");
////            singletonUI.show();
//            Singleton singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"withLazyAndExtra", "Lazy", "Singleton", fields, methods);
//            file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        else if(action.equals("ThreadSafe")) {
////            SingletonGenDialogWrapper singletonUI = new SingletonGenDialogWrapper(anActionEvent.getProject(), "Eager");
////            singletonUI.show();
//            Singleton singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"SyncImplementation", "Sync", "Singleton");
//            file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        else if(action.equals("Enum")){
////            SingletonGenDialogWrapper singletonUI = new SingletonGenDialogWrapper(anActionEvent.getProject(), "Eager");
////            singletonUI.show();
//            Singleton singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"EnumImplementation", "Enum", "Singleton");
//            file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        if(action.equals("Composite")) {
//            String packageName = "Composite";
//            Component interfaceFile = new Component("Component", abstracMethods);
//            file = JavaFile.builder(packageName, interfaceFile.getInterfaceGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Composite compositeObject = new Composite(interfaceFile, GeneratedClass.INSTANCE.getClassName()+"Composite", packageName);
//            file = JavaFile.builder(packageName, compositeObject.getCompositeGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Leaf leafObj = new Leaf(interfaceFile, GeneratedClass.INSTANCE.getClassName()+"Leaf",packageName, fields, null);
//            file = JavaFile.builder(packageName, leafObj.getLeafGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else if(action.equals("Template")){
//            Abstract abstractObject = new Abstract(GeneratedClass.INSTANCE.getClassName()+"Abstract","Template",fields,abstractMethods);
//            file = JavaFile.builder("Template", abstractObject.getAbsClassGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Concrete concreteObject = new Concrete(GeneratedClass.INSTANCE.getClassName()+"Concrete", abstractObject);
//            file = JavaFile.builder("Template", concreteObject.getConcreteGen()).build();
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        project.getBaseDir().refresh(false,true);


//        if (dialogWrapper.isOK()) {
//            String template;
//            if (GeneratedClass.INSTANCE.isHasPsvm()) {
//                template = "class_template.java";
//            } else {
//                template = "main_template.java";
//            }
//
//            JavaDirectoryService.getInstance().createClass(selectedDir,
//                    GeneratedClass.INSTANCE.getClassName(), template, true);
//        }


//


//
//        try {
//            file.writeTo(outPut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            file.writeTo(outPut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

//        try {
//            file.writeTo(outPut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


//        try {
//            file.writeTo(outPut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            file.writeTo(outPut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

//

//
//
//

//

    }
}

