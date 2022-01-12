package Actions;

import GUI.GenDialogWrapper;
import JavaPoetTemplates.BaseClass;
import JavaPoetTemplates.Fields;
import JavaPoetTemplates.Methods;
import JavaPoetTemplates.Patterns.Composite.Component;
import JavaPoetTemplates.Patterns.Composite.Composite;
import JavaPoetTemplates.Patterns.Composite.Leaf;
import JavaPoetTemplates.Patterns.Singleton;
import JavaPoetTemplates.SampleTemplate;
import SampleClasses.GeneratedClass;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.tools.ant.taskdefs.Java;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
        GenDialogWrapper dialogWrapper = new GenDialogWrapper(anActionEvent.getProject());
        dialogWrapper.show();
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

        String selectedPath = selectedDir.toString();
        String outputPath = selectedPath.toString();
        File outPut = new File(outputPath.substring(13));
//        SampleTemplate generate = new SampleTemplate("newClass", "newMethod", "newField");
//        TypeSpec newClass = generate.generateJavaClass();
//        JavaFile javaFile = JavaFile.builder("GeneratedClass", newClass)
//                .build();
//
//        try {
//            javaFile.writeTo(outPut);
//            javaFile.writeTo(System.out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
        ArrayList<Modifier> modifiers1 = new ArrayList<>();
        modifiers1.add(Modifier.PRIVATE);
        modifiers1.add(Modifier.STATIC);
        Fields field1 = new Fields(int.class, "Anas", modifiers1);

        ArrayList<Modifier> modifiers2 = new ArrayList<>();
        modifiers1.add(Modifier.PUBLIC);
        modifiers1.add(Modifier.STATIC);
        Fields field2 = new Fields(String.class, "Cannot", modifiers2);

        ArrayList<Modifier> modifiers3 = new ArrayList<>();
        modifiers1.add(Modifier.PROTECTED);
        modifiers1.add(Modifier.STATIC);
        Fields field3 = new Fields(String.class, "TakeYourCall", modifiers3);

        ArrayList<Fields> fields = new ArrayList<>();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);

        ArrayList<Modifier> methodsMod1 = new ArrayList<>();
        methodsMod1.add(Modifier.PUBLIC);


        ArrayList<String> statements1 = new ArrayList<>();
        statements1.add("System.out.println(\"Testing Statements1\")");
        statements1.add("System.out.println(\"Testing Statements2\")");
        statements1.add("System.out.println(\"Testing Statements3\")");
        statements1.add("System.out.println(\"Testing Statements4\")");
        statements1.add("System.out.println(\"Testing Statements5\")");
        statements1.add("return null");



        Methods method1 = new Methods("SimpleMethod1",
                methodsMod1, String.class, null, null, statements1);


        ArrayList<Modifier> methodsMod2 = new ArrayList<>();
        methodsMod2.add(Modifier.PRIVATE);
        methodsMod2.add(Modifier.SYNCHRONIZED);

        ArrayList<Class> parameterTypes1 = new ArrayList<>();
        ArrayList<String> parameterNames1 = new ArrayList<>();
        parameterTypes1.add(int.class);
        parameterTypes1.add(char.class);
        parameterTypes1.add(boolean.class);
        parameterNames1.add("intParameter");
        parameterNames1.add("charParameter");
        parameterNames1.add("boolParameter");


        Methods method2 = new Methods("SimpleMethod2",
                methodsMod2, int.class, parameterTypes1, parameterNames1, statements1);

        Methods method3 = new Methods("SimpleMethod3",
                methodsMod1, float.class, null, null, statements1);

        ArrayList<MethodSpec> methods = new ArrayList<>();
        methods.add(method1.getMethod());
//        TypeSpec generateClass1 = new BaseClass(GeneratedClass.INSTANCE.getClassName(), fields, methods).getClassGen();
//
//        JavaFile newFile = JavaFile.builder("package1", generateClass1).build();
//        JavaFile newFile2 = JavaFile.builder("package2", generateClass1).build();
//        JavaFile newFile3 = JavaFile.builder("package3", generateClass1).build();
//
//        ArrayList<JavaFile> files = new ArrayList<>();
//        files.add(newFile);
//        files.add(newFile2);
//        files.add(newFile3);
//
//        files.forEach((file) -> {
//            try {
//                file.writeTo(outPut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        project.getBaseDir().refresh(false,true);


        Singleton singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName(), "singleObj", "Eager", "Singleton");
        JavaFile file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"withLazyAndExtra", "singleObj", "Lazy", "Singleton", fields, methods);
        file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"SyncImplementation", "singleObj", "Sync", "Singleton");
        file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        singletonClass = new Singleton(GeneratedClass.INSTANCE.getClassName()+"EnumImplementation", "singleObj", "Enum", "Singleton");
        file = JavaFile.builder("Singleton", singletonClass.getClassGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<MethodSpec> abstracMethods = new ArrayList<>();;
        abstracMethods.add(MethodSpec.methodBuilder("abstractExample1")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .build()
        );
        abstracMethods.add(MethodSpec.methodBuilder("abstractExample2")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .build()
        );
        String componentName = "Component";
        String packageName = "Composite";
        Component interfaceFile = new Component("Component", abstracMethods);
        file = JavaFile.builder(packageName, interfaceFile.getInterfaceGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Composite compositeObject = new Composite(interfaceFile, GeneratedClass.INSTANCE.getClassName()+"Composite", packageName);
        file = JavaFile.builder(packageName, compositeObject.getCompositeGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Leaf leafObj = new Leaf(interfaceFile, GeneratedClass.INSTANCE.getClassName()+"Leaf",packageName, fields, methods);
        file = JavaFile.builder(packageName, leafObj.getLeafGen()).build();
        try {
            file.writeTo(outPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        project.getBaseDir().refresh(false,true);

    }
}

