package Actions;

import GUI.ClassGenerators.CompositeFactoryUI;
import GUI.ClassGenerators.SingletonFactoryUI;
import GUI.ClassGenerators.TemplateFactoryUI;
import GUI.Tutorials.TutorialWrapper;
import GUI.UIFactory;
import InputHolders.ClassInputs;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
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

        ClassInputs.INSTANCE.setPatternToGenerate(action);


        DialogWrapper ui = UIFactory.createUI(action.toString(), project);
        ui.show();

//
//        if(action.equals("Composite")){
//            CompositeFactoryUI compositeUI = new CompositeFactoryUI(anActionEvent.getProject());
//            compositeUI.show();
//
//        }
//
//        else if(action.equals("Template")){
//            TemplateFactoryUI templateUI = new TemplateFactoryUI(anActionEvent.getProject());
//            templateUI.show();
//        }
//
//        else if(action.equals("What's a Singleton?")){
//            TutorialWrapper dialog = new TutorialWrapper(anActionEvent.getProject());
//            dialog.pack();
//            dialog.show();
//        }
//
//        else{
//            SingletonFactoryUI singletonUI = new SingletonFactoryUI(anActionEvent.getProject());
//            singletonUI.show();
//        }

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


        project.getBaseDir().refresh(false,true);



    }
}

