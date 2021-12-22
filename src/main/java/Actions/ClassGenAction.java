package Actions;

import GUI.GenDialogWrapper;
import JavaPoetTemplates.SampleTemplate;
import SampleClasses.GeneratedClass;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.*;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;


public class ClassGenAction extends AnAction {

    private PsiDirectory selectedDir;

    @Override
    public void update(AnActionEvent e) {
        PsiElement selectedElement = CommonDataKeys.PSI_ELEMENT.getData(e.getDataContext());
        if (selectedElement instanceof PsiDirectory) {
            selectedDir = (PsiDirectory) selectedElement;
        } else if (selectedElement instanceof PsiClass) {
            PsiFile psiFile = selectedElement.getContainingFile();
            selectedDir = psiFile.getContainingDirectory();
        } else {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        GenDialogWrapper dialogWrapper = new GenDialogWrapper(anActionEvent.getProject());
        dialogWrapper.show();
        if (dialogWrapper.isOK()) {
            String template;
            if (GeneratedClass.INSTANCE.isHasPsvm()) {
                template = "class_template.java";
            } else {
                template = "main_template.java";
            }

            JavaDirectoryService.getInstance().createClass(selectedDir,
                    GeneratedClass.INSTANCE.getClassName(), template, true);
        }
        System.out.println(selectedDir);
        String selectedPath = selectedDir.toString();
        System.out.println(selectedPath);
        String outputPath = selectedPath.toString();
        System.out.println(outputPath.substring(12));
        File outPut = new File(outputPath.substring(13));
        SampleTemplate generate = new SampleTemplate("newClass", "newMethod", "newField");
        TypeSpec newClass = generate.generateJavaClass();
        JavaFile javaFile = JavaFile.builder("GeneratedClass", newClass)
                .build();

        try {
            javaFile.writeTo(outPut);
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

