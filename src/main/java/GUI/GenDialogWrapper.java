package GUI;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;

public class GenDialogWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    public GenDialogWrapper(@Nullable Project project) {
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
