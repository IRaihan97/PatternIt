package GUI.Tutorials;

import GUI.ClassGenerators.ClassGenerator;
import GUI.Tutorials.SingletonTutorial;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;

public class TutorialWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    private SingletonCard singletonForm;

    public TutorialWrapper(@Nullable Project project) {
        super(project);
        singletonForm = new SingletonCard();
        init();
        setTitle("Singleton Generator");
    }
    //https://ayusch.com/intellij-plugin-development-tutorial-handling-user-input/
//
//    @Override
//    protected JComponent createSouthPanel(){
//        return null;
//    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return singletonForm.getContent();

    }



}
