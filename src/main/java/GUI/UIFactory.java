package GUI;

import GUI.ClassGenerators.CompositeFactoryUI;
import GUI.ClassGenerators.SingletonFactoryUI;
import GUI.ClassGenerators.TemplateFactoryUI;
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
        else{
            return new SingletonFactoryUI(project);
        }
    }
}
