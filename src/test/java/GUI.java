import GUI.ClassGenerators.CompositeFactoryUI;
import GUI.ClassGenerators.Fields;
import JavaPoetTemplates.FieldGen;
import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.Before;
import org.junit.Test;

public class GUI {
    private DialogFixture dialog;

    @RunsInEDT
    @Before
    public void setup(){
        CompositeFactoryUI compositeDialog = GuiActionRunner.execute(() -> new CompositeFactoryUI(null));
        dialog = new DialogFixture(String.valueOf(compositeDialog));
        dialog.show();
    }

    @Test
    public void testingAddButton(){
        dialog.button("addBtn").click();
        dialog.button("addBtn").click();
        dialog.button("addBtn").click();
        dialog.button("addBtn").click();
    }
}
