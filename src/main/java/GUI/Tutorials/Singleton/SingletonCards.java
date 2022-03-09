package GUI.Tutorials.Singleton;

import GUI.Tutorials.TutorialDialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SingletonCards {
    JPanel[] cards = new JPanel[2];
    JPanel parentContainer;
    SingletonCards(){
        singletonPage1();
        singletonPage2();

    }
    public JPanel[] getCards(){
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
        cards[0] = page;
    }
    private void singletonPage2(){
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("What are the application of Singleton?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton hwInterfaceBtn = new JButton("Hardware Interfaces");
        JButton loggerBtn = new JButton("Loggers");
        JButton configBtn = new JButton("Config Files");
        hwInterfaceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JPanel> panels = new ArrayList();


                JPanel panel1 = new JPanel();
                JLabel label1 = new JLabel("Hardware Interfaces");
                label1.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(5,10)));
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
//                panel1.add(new JTextArea("The use of singleton depends on the\n" +
//                        "requirements. Singleton classes are also used to prevent concurrent access of class.\n" +
//                        "Practically singleton can be used in case external hardware resource usage\n" +
//                        "limitation required e.g. Hardware printers where the print spooler can be made a\n" +
//                        "singleton to avoid multiple concurrent accesses and creating deadlock"));
                panels.add(panel1);


                JPanel panel2 = new JPanel();
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
                JLabel label = new JLabel("Logger");
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel2.add(label);
                panel2.add(Box.createRigidArea(new Dimension(5,10)));
                panel2.add(new JTextArea("Singleton classes are used in log file generations.\n" +
                        "Log files are created by the logger class object.\n" +
                        "Suppose an application where the logging utility has to produce one log file\n" +
                        "based on the messages received from the users.\n" +
                        "If there is multiple client application using this\n" +
                        "logging utility class they might create multiple instances\n" +
                        "of this class and it can potentially cause issues during concurrent\n" +
                        "access to the same logger file. We can use the logger utility class\n" +
                        "as a singleton and provide a global point of reference\n" +
                        "so that each user can use this utility and no 2 users access it at the same time."));
                panels.add(panel2);

                TutorialDialogs dialog = new TutorialDialogs("Common Uses", null, panels);
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


//        JTextArea text2 = new JTextArea(
//                "Logger : Singleton classes are used in log file generations.\n" +
//                        "Log files are created by the logger class object.\n" +
//                        "Suppose an application where the logging utility has to produce one log file\n" +
//                        "based on the messages received from the users.\n" +
//                        "If there is multiple client application using this\n" +
//                        "logging utility class they might create multiple instances\n" +
//                        "of this class and it can potentially cause issues during concurrent\n" +
//                        "access to the same logger file. We can use the logger utility class\n" +
//                        "as a singleton and provide a global point of reference\n" +
//                        "so that each user can use this utility and no 2 users access it at the same time."
//        );
//        JTextArea text3 = new JTextArea(
//                "Configuration File: This is another potential candidate\n" +
//                        "for Singleton pattern because this has a performance benefit as\n" +
//                        "it prevents multiple users to repeatedly access and read the configuration file\n" +
//                        "or properties file. It creates a single instance of the configuration file\n" +
//                        "which can be accessed by multiple calls concurrently as it will provide\n" +
//                        "static config data loaded into in-memory objects.\n" +
//                        "The application only reads from the configuration file\n" +
//                        "for the first time and thereafter from second call onwards\n" +
//                        "the client applications read the data from in-memory objects."
//        );
//        page.add(title);
//        page.add(Box.createRigidArea(new Dimension(5,10)));
//        page.add(text1);
//        page.add(Box.createRigidArea(new Dimension(5,10)));
//        page.add(text2);
//        page.add(Box.createRigidArea(new Dimension(5,10)));
//        page.add(text3);
//        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(title);
        hwInterfaceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loggerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        page.add(Box.createRigidArea(new Dimension(5,10)));
        page.add(hwInterfaceBtn);
        page.add(loggerBtn);
        cards[1] = page;
    }

}
