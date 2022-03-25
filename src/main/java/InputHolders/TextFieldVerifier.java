package InputHolders;

import javax.swing.*;

public class TextFieldVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String textToVerify = textField.getText();
        if(textToVerify.equals("class")){
            JOptionPane.showMessageDialog(textField, "The name 'class' cannot be assigned, Please choose a different name","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        if(textToVerify.equals("package")){
            JOptionPane.showMessageDialog(textField, "The name 'package' cannot be assigned, Please choose a different name","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        if(textToVerify.equals("interface")) {
            JOptionPane.showMessageDialog(textField, "The name 'interface' cannot be assigned, Please choose a different name","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        if(textToVerify.isEmpty()){
            JOptionPane.showMessageDialog(textField, "Empty name found, Please define a name for all inputs","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        if(textToVerify==null){
            JOptionPane.showMessageDialog(textField, "Empty name found, Please define a name for all inputs","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        if(textToVerify.equals("")){
            JOptionPane.showMessageDialog(textField, "Empty name found, Please define a name for all inputs","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }
        return true;
    }
}
