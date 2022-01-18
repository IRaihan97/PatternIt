package GUI;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;


import javax.swing.*;

public class BasicGenDialogWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    private SingletonForm singletonForm;
    public BasicGenDialogWrapper(@Nullable Project project) {
        super(project);
        classGenForm = new ClassGenerator();
        init();
        setTitle("Class generator");
    }
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return classGenForm.getContent();
    }
}
