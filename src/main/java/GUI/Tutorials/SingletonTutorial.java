package GUI.Tutorials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import com.github.cjwizard.*;
import com.github.cjwizard.pagetemplates.TitledPageTemplate;
import com.intellij.ui.wizard.WizardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonTutorial extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private final Logger log = LoggerFactory.getLogger(SingletonTutorial.class);
    WizardModel model;
    WizardController controller;

    public SingletonTutorial() {
        final WizardContainer wc = new WizardContainer(
            new TestFactory(), new TitledPageTemplate(), new StackWizardSettings()
        );
        wc.addWizardListener(new WizardListener(){
            @Override
            public void onCanceled(List<WizardPage> path, WizardSettings settings) {
                log.debug("settings: "+wc.getSettings());
                SingletonTutorial.this.dispose();
            }

            @Override
            public void onFinished(List<WizardPage> path, WizardSettings settings) {
                log.debug("settings: "+wc.getSettings());
                SingletonTutorial.this.dispose();
            }

            @Override
            public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
                log.debug("settings: "+wc.getSettings());
                // Set the dialog title to match the description of the new page:
                SingletonTutorial.this.setTitle(newPage.getDescription());
            }

            public void onPageChanging(WizardPage newPage, List<WizardPage> path) {
                log.debug("settings: "+wc.getSettings());
            }
        });
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(wc);
        this.pack();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public JPanel getContent(){
        return contentPane;
    }

    private class TestFactory extends AbstractPageFactory {

        // To keep things simple, we'll just create an array of wizard pages:
        private final WizardPage[] pages = {
                new WizardPage("One", "First Page") {
                    // this is an instance initializer -- it's a constructor for
                    // an anonymous class.  WizardPages don't need to be anonymous,
                    // of course.  It just makes the demo fit in one file if we do it
                    // this way:
                    {
                        JTextField field = new JTextField();
                        // set a name on any component that you want to collect values
                        // from.  Be sure to do this *before* adding the component to
                        // the WizardPage.
                        field.setName("testField");
                        field.setPreferredSize(new Dimension(50, 20));
                        add(new JLabel("One!"));
                        add(field);
                    }
                },
                new WizardPage("Two", "Second Page") {
                    {
                        JCheckBox box = new JCheckBox("testBox");
                        box.setName("box");
                        add(new JLabel("Two!"));
                        add(box);
                    }

                    /* (non-Javadoc)
                     * @see com.github.cjwizard.WizardPage#updateSettings(com.github.cjwizard.WizardSettings)
                     */
                    @Override
                    public void updateSettings(WizardSettings settings) {
                        super.updateSettings(settings);

                        // This is called when the user clicks next, so we could do
                        // some longer-running processing here if we wanted to, and
                        // pop up progress bars, etc.  Once this method returns, the
                        // wizard will continue.  Beware though, this runs in the
                        // event dispatch thread (EDT), and may render the UI
                        // unresponsive if you don't issue a new thread for any long
                        // running ops.  Future versions will offer a better way of
                        // doing this.
                    }

                },
                new WizardPage("Three", "Third Page") {
                    {
                        add(new JLabel("Three!"));
                        setBackground(Color.green);
                    }

                    /**
                     * This is the last page in the wizard, so we will enable the finish
                     * button and disable the "Next >" button just before the page is
                     * displayed:
                     */
                    public void rendering(List<WizardPage> path, WizardSettings settings) {
                        super.rendering(path, settings);
                        setFinishEnabled(true);
                        setNextEnabled(false);
                    }
                }
        };


        /* (non-Javadoc)
         * @see com.github.cjwizard.PageFactory#createPage(java.util.List, com.github.cjwizard.WizardSettings)
         */
        @Override
        public WizardPage createPage(List<WizardPage> path,
                                     WizardSettings settings) {
            log.debug("creating page " + path.size());

            // Get the next page to display.  The path is the list of all wizard
            // pages that the user has proceeded through from the start of the
            // wizard, so we can easily see which step the user is on by taking
            // the length of the path.  This makes it trivial to return the next
            // WizardPage:
            WizardPage page = pages[path.size()];

            // if we wanted to, we could use the WizardSettings object like a
            // Map<String, Object> to change the flow of the wizard pages.
            // In fact, we can do arbitrarily complex computation to determine
            // the next wizard page.

            log.debug("Returning page: " + page);
            return page;
        }
    }
}
