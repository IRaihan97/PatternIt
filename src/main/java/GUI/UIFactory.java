package GUI;

import GUI.ClassGenerators.CompositeFactoryUI;
import GUI.ClassGenerators.SingletonFactoryUI;
import GUI.ClassGenerators.TemplateFactoryUI;
import GUI.Tutorials.TutorialWrapper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

public class UIFactory {
    public static DialogWrapper createUI(String UIType, Project project){
        if(UIType.equals("Template")){
            return new TemplateFactoryUI(project);
        }
        if(UIType.equals("Composite")){
            return new CompositeFactoryUI(project);
        }
        if(UIType.equals("What's a Singleton?")){
            return new TutorialWrapper(project);
        }
        else{
            return new SingletonFactoryUI(project);
        }
    }
}
