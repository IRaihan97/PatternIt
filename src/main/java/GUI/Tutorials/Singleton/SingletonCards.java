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
        JLabel title = new JLabel("What is a Singleton?");
        JTextArea text1 = new JTextArea(
                "Singleton pattern is a design pattern which restricts a\n" +
                        "class to instantiate its multiple objects." +
                        "\nIt is nothing but a way of defining a class." +
                        "\nClass is defined in such a way that only one instance of \n" +
                        "the class is created in the complete\n" +
                        "execution of a program or project." +
                        "\nIt is used where only a single instance of a class is required\n" +
                        "to control the action throughout the execution." +
                        "\nA singleton class shouldn't have multiple instances in any case and at any cost." +
                        "\nSingleton classes are used for logging, driver objects,\n" +
                        "caching and thread pool, database connections.");
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
                        "of Singleton Class is created\n" +
                        "at the time of class loading, this is the easiest method to create\n" +
                        "a singleton class but it has a drawback that instance is created \n" +
                        "even though client application might not be using it.");
                JTextArea text2 = new JTextArea("If your singleton class is not using a lot of resources,\n" +
                        "this is the approach to use. But in most of the scenarios,\n" +
                        "Singleton classes are created for resources such as File System,\n" +
                        "Database connections, etc. We should avoid the instantiation until\n" +
                        "unless client calls the getInstance method. Also, this method doesn't provide\n" +
                        "any options for exception handling.");
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
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


                JTextArea text1 = new JTextArea("Lazy initialization method to implement Singleton pattern\n" +
                        "creates the instance in the global access method.\n" +
                        "In the next page you will find sample code for creating Singleton class with this approach.");
                JTextArea text2 = new JTextArea(
                        "The above implementation works fine in case of the single-threaded\n" +
                        "environment but when it comes to multithreaded systems,\n" +
                        "it can cause issues if multiple threads are inside the if condition at the same time.\n" +
                        "It will destroy the singleton pattern and both threads will\n" +
                        "get the different instances of the singleton class.\n" +
                        "In next section, we will see different ways to create a thread-safe singleton class.");
                text1.setAlignmentX(Component.LEFT_ALIGNMENT);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
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


                JTextArea text1 = new JTextArea("The easier way to create a thread-safe singleton class is to \n" +
                        "make the global access method synchronized, so that only one thread can execute\n" +
                        " this method at a time. General implementation of this approach is like the class in the next page.");
                JTextArea text2 = new JTextArea(
                        "To avoid this extra overhead every time, double checked locking principle is used.\n" +
                                "In this approach, the synchronized block is used inside\n" +
                                "the if condition with an additional check to ensure that only one instance of a singleton class is created.");
                text1.setAlignmentX(Component.LEFT_ALIGNMENT);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
                panel1.add(text1);
                cards.add(panel1);
                JPanel panel2 = new JPanel();
                JLabel label = new JLabel("Code Example:");
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                picLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);
                text2.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
                panel2.add(label);
                panel2.add(picLabel3);
                panel2.add(text2);
                cards.add(panel2);


                text1.setAlignmentX(Component.LEFT_ALIGNMENT);

                TutorialDialogs dialog = new TutorialDialogs("Eager Implementation", null, cards);
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
        page.add(enumBtn);
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
