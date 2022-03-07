package GUI.Tutorials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import com.github.cjwizard.*;
import com.github.cjwizard.pagetemplates.TitledPageTemplate;
import com.intellij.ui.wizard.WizardModel;
import net.miginfocom.layout.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonTutorial extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private final Logger log = LoggerFactory.getLogger(SingletonTutorial.class);

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

//        buttonOK.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onOK();
//            }
//        });
//
//        buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });

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
                new WizardPage("What is Singleton", "What is Singleton") {
                    // this is an instance initializer -- it's a constructor for
                    // an anonymous class.  WizardPages don't need to be anonymous,
                    // of course.  It just makes the demo fit in one file if we do it
                    // this way:
                    {
                        JTextArea text = new JTextArea(
                                "Singleton pattern is a design pattern which restricts a \n class to instantiate its multiple objects." +
                                "\n It is nothing but a way of defining a class. " +
                                "\n Class is defined in such a way that only one instance of \n the class is created in the complete execution of a program or project. " +
                                "\n It is used where only a single instance of a class is required \n to control the action throughout the execution. " +
                                "\n A singleton class shouldn’t have multiple instances in any case and at any cost." +
                                "\n Singleton classes are used for logging, driver objects,\n caching and thread pool, database connections.");
                        // set a name on any component that you want to collect values
                        // from.  Be sure to do this *before* adding the component to
                        // the WizardPage.

                        add(text);


                    }
                },
                new WizardPage("Common Uses", "Second Page") {
                    {
                        setLayout(new GridBagLayout());
                        GridBagConstraints con = new GridBagConstraints();
                        Dimension size = new Dimension(200, 200);
                        con.gridy = 1;
                        JTextArea text1 = new JTextArea(
                                "Hardware interface access: The use of singleton depends on the \n" +
                                "requirements. Singleton classes are also used to prevent concurrent access of class.\n" +
                                "Practically singleton can be used in case external hardware resource usage\n" +
                                        "limitation required e.g. Hardware printers where the print spooler can be made a\n" +
                                "singleton to avoid multiple concurrent accesses and creating deadlock"
                        );
                        JTextArea text2 = new JTextArea(
                                "Logger : Singleton classes are used in log file generations. \n" +
                                "Log files are created by the logger class object. \n" +
                                "Suppose an application where the logging utility has to produce one log file\n" +
                                "based on the messages received from the users. \n" +
                                "If there is multiple client application using this \n" +
                                "logging utility class they might create multiple instances \n" +
                                "of this class and it can potentially cause issues during concurrent \n" +
                                "access to the same logger file. We can use the logger utility class\n" +
                                "as a singleton and provide a global point of reference \n" +
                                "so that each user can use this utility and no 2 users access it at the same time."
                        );

                        add(text1);

                        add(new JSeparator());
                        add(text2, con);
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
