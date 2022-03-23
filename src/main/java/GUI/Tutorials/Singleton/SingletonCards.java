package GUI.Tutorials.Singleton;

import GUI.Tutorials.TutorialDialogs;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SingletonCards {
    ArrayList<JPanel> cards = new ArrayList<>();
    JPanel parentContainer;
    SingletonCards(){
        singletonPage1();
        try {
            singletonPage2();
        } catch (IOException e) {
            e.printStackTrace();
        }
        singletonPage3();

    }
    public ArrayList<JPanel> getCards(){
        return cards;
    }

    private void singletonPage1(){
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.PAGE_AXIS));
//        String text = "<p>Singleton pattern is a design pattern which restricts a" +
//                "class to instantiate its multiple objects." +
//                "Class is defined in such a way that only <b>one instance</b> of " +
//                "the class is created in the complete execution of a program or project." +
//                "It is used where only a <b>single instance of a class</b> is required" +
//                "to control the action throughout the execution." +
//                "A singleton class shouldn't have multiple instances in any case and at any cost." +
//                "Singleton classes are used for logging, driver objects," +
//                "caching and thread pool, database connections.</p>";
//        JEditorPane editor = new JEditorPane();
//        editor.setContentType( "text/html" );
//        editor.setText(text);
        JLabel title = new JLabel("What is a Singleton?");
        title.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        JTextArea text1 = new JTextArea(
                "Singleton pattern is a design pattern which restricts a\n" +
                        "class to instantiate its multiple objects." +
                        "\nClass is defined in such a way that only one instance of \n" +
                        "the class is created in the complete execution of a program or project." +
                        "\nIt is used where only a single instance of a class is required\n" +
                        "to control the action throughout the execution." +
                        "\nA singleton class shouldn't have multiple instances in any case and at any cost." +
                        "\nSingleton classes are used for logging, driver objects,\n" +
                        "caching and thread pool, database connections.");
//        JTextArea text1 = new JTextArea();
        text1.setEditable(false);
        text1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
//        text1.setText(editor.getText());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        page.add(title);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(text1);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        cards.add(page);
    }
    @SneakyThrows
    private void singletonPage2() throws IOException {
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.PAGE_AXIS));

        BufferedImage myPicture1 = ImageIO.read(SingletonCards.class.getResourceAsStream("/images/Eager.png"));
        JLabel picLabel1 = new JLabel(new ImageIcon(myPicture1));
        BufferedImage myPicture2 = ImageIO.read(SingletonCards.class.getResourceAsStream("/images/Lazy.png"));
        JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
        BufferedImage myPicture3 = ImageIO.read(SingletonCards.class.getResourceAsStream("/images/Enum.png"));
        JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
        BufferedImage myPicture4 = ImageIO.read(SingletonCards.class.getResourceAsStream("/images/Sync.png"));
        JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
        JLabel title = new JLabel("Implementation Types");
        title.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton eagerBtn = new JButton("Eager");
        JButton lazyBtn = new JButton("Lazy");
        JButton syncBtn = new JButton("Synchronous");
        JButton enumBtn = new JButton("Enum");
        eagerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JPanel> cards = new ArrayList<>();


                JTextArea text1 = new JTextArea("In eager initialization, the instance \n" +
                        "of Singleton Class is created at the time of class loading. \n" +
                        "This is the easiest method to create a singleton class.\n" +
                        "However, it is not recommended to use because it might create \n" +
                        "multiple instances of the class even when it is not required.");
                text1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text1.setEditable(false);
                JTextArea text2 = new JTextArea(
                        "If your singleton class is not using a lot of resources, this is the approach to use.\n" +
                                "In most of the scenarios, Singleton classes are created for resources such as File System, Database connections, etc.\n" +
                                "We should avoid the instantiation unless client calls the getInstance method.\n" +
                                "Also, this method doesn't provide any options for exception handling.");
                text2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text2.setEditable(false);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
                label.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text2.setAlignmentX(Component.LEFT_ALIGNMENT);
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                picLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
                panel2.add(label);
                panel2.add(picLabel1);
                panel2.add(text2);
                cards.add(panel2);


                text1.setAlignmentX(Component.LEFT_ALIGNMENT);

                TutorialDialogs dialog = new TutorialDialogs("Eager Implementation", null, cards);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });
        lazyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JPanel> cards = new ArrayList<>();


                JTextArea text1 = new JTextArea("The Lazy initialization method creates the instance in the global access method.\n" +
                        "In the next page you will find sample code for creating Singleton class with this approach.");
                text1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text1.setEditable(false);
                JTextArea text2 = new JTextArea(
                        "The above implementation works fine in case of the single-threaded environment\n" +
                                "However, when it comes to multithreaded systems,\n" +
                        "it can cause issues when multiple threads are inside an if condition at the same time.\n" +
                        "The Lazy implementation may destroy the singleton pattern because both threads may\n" +
                        "get the different instances of the singleton class.");
                text2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text2.setEditable(false);
                text1.setAlignmentX(Component.LEFT_ALIGNMENT);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
                label.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                picLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
                text2.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
                panel2.add(label);
                panel2.add(picLabel2);
                panel2.add(text2);
                cards.add(panel2);


                text1.setAlignmentX(Component.LEFT_ALIGNMENT);

                TutorialDialogs dialog = new TutorialDialogs("Lazy Implementation", null, cards);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });
        syncBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JPanel> cards = new ArrayList<>();


                JTextArea text1 = new JTextArea("The easier way to create a thread-safe singleton class is to make the global access method synchronized.\n" +
                        "This way, only one thread can execute the getInstance method at a time.\n" +
                        "General implementation of this approach can be seen in the next page.");
                text1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text1.setEditable(false);
                JTextArea text2 = new JTextArea(
                        "To avoid this extra overhead every time, double checked locking principle is used.\n" +
                                "In this approach, the synchronized block is used inside an if condition.\n"+
                                "In addition a check is used to ensure that only one instance of a singleton class is created.");
                text2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                text2.setEditable(false);
                text1.setAlignmentX(Component.LEFT_ALIGNMENT);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
                label.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                picLabel4.setAlignmentX(Component.LEFT_ALIGNMENT);
                text2.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
                panel2.add(label);
                panel2.add(picLabel4);
                panel2.add(text2);
                cards.add(panel2);


                text1.setAlignmentX(Component.LEFT_ALIGNMENT);

                TutorialDialogs dialog = new TutorialDialogs("Sync Implementation", null, cards);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });
        enumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea(
                        "We can use the cache as a singleton object as it can have\n" +
                                "a global point of reference and for all future calls to\n" +
                                "the cache object the client application will use the in-memory object."
                );
                text.setEditable(false);
                text.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                TutorialDialogs dialog = new TutorialDialogs("Caching", text, null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        page.add(title);
        eagerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        lazyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        syncBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        enumBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(eagerBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(lazyBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(syncBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
//        page.add(enumBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        cards.add(page);
    }
    private void singletonPage3(){
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("What are the application of Singleton?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton hwInterfaceBtn = new JButton("Hardware Interfaces");
        JButton loggerBtn = new JButton("Loggers");
        JButton configBtn = new JButton("Config Files");
        JButton cacheBtn = new JButton("Cache");
        hwInterfaceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea("The use of singleton depends on the\n" +
                        "requirements. Singleton classes are also used to prevent concurrent access of class.\n" +
                        "Practically singleton can be used in case external hardware resource usage\n" +
                        "limitation required e.g. Hardware printers where the print spooler can be made a\n" +
                        "singleton to avoid multiple concurrent accesses and creating deadlock");
                text.setAlignmentX(Component.LEFT_ALIGNMENT);
                text.setEditable(false);
                text.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                TutorialDialogs dialog = new TutorialDialogs("Hardware Interfaces", text, null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });
        loggerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text1 = new JTextArea(
                        "Singleton classes are used in log file generations.\n" +
                        "Log files are created by the logger class object.\n" +
                        "Suppose an application where the logging utility has to produce one log file\n" +
                        "based on the messages received from the users.\n" +
                        "If there is multiple client application using this\n" +
                        "logging utility class they might create multiple instances\n" +
                        "of this class and it can potentially cause issues during concurrent\n" +
                        "access to the same logger file. We can use the logger utility class\n" +
                        "as a singleton and provide a global point of reference\n" +
                        "so that each user can use this utility and no 2 users access it at the same time."
                );
                text1.setEditable(false);
                text1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                TutorialDialogs dialog = new TutorialDialogs("Logger", text1, null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        configBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea(
                        "This is another potential candidate\n" +
                        "for Singleton pattern because this has a performance benefit as\n" +
                        "it prevents multiple users to repeatedly access and read the configuration file\n" +
                        "or properties file. It creates a single instance of the configuration file\n" +
                        "which can be accessed by multiple calls concurrently as it will provide\n" +
                        "static config data loaded into in-memory objects.\n" +
                        "The application only reads from the configuration file\n" +
                        "for the first time and thereafter from second call onwards\n" +
                        "the client applications read the data from in-memory objects."
                        );
                text.setEditable(false);
                text.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                TutorialDialogs dialog = new TutorialDialogs("Config files", text, null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        cacheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea(
                        "We can use the cache as a singleton object as it can have\n" +
                                "a global point of reference and for all future calls to\n" +
                                "the cache object the client application will use the in-memory object."
                );
                text.setEditable(false);
                text.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                TutorialDialogs dialog = new TutorialDialogs("Caching", text, null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        page.add(title);
        hwInterfaceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loggerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        configBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        cacheBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(hwInterfaceBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(loggerBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(configBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(cacheBtn);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        cards.add(page);
    }

}
