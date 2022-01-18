package GUI;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.istack.Nullable;

import javax.swing.*;

public class SingletonGenDialogWrapper extends DialogWrapper {
    private ClassGenerator classGenForm;
    private SingletonForm singletonForm;
    private String singletonMode;
    public SingletonGenDialogWrapper(@Nullable Project project, String singletonMode) {
        super(project);
        this.singletonMode = singletonMode;
        singletonForm = new SingletonForm();
        init();
        setTitle("Singleton Generator");
    }
    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        return singletonForm.getContent();
    }
}
