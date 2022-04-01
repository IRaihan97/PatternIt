package InputHolders;

import javax.lang.model.SourceVersion;
import javax.swing.*;

public class TextFieldVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String textToVerify = textField.getText();
        //Succesful entries

        try
        {
            if(SourceVersion.isKeyword(textToVerify)){
                JOptionPane.showMessageDialog(textField, "Cannot assign a Java restricted keyword, Please choose a different name","Error Dialog",
                        JOptionPane.ERROR_MESSAGE );
                return false;
            }
            if(textToVerify.length() > 20){
                JOptionPane.showMessageDialog(textField, "The given name is too long, Please choose a different name","Error Dialog",
                        JOptionPane.ERROR_MESSAGE );
                return false;
            }
            if(!textToVerify.matches("((?<![0-9])[a-zA-Z0-9\\_])+")){
                JOptionPane.showMessageDialog(textField, "The names provided are invalid and cannot be assigned, Please enter valid names","Error Dialog",
                        JOptionPane.ERROR_MESSAGE );
                return false;
            }
            if(Character.isDigit(textToVerify.charAt(0))){
                JOptionPane.showMessageDialog(textField, "Names provided cannot start with a number, Please enter valid names","Error Dialog",
                        JOptionPane.ERROR_MESSAGE );
                return false;
            }

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(textField, "The names provided are invalid and cannot be assigned, Please enter valid names","Error Dialog",
                    JOptionPane.ERROR_MESSAGE );
            return false;
        }

        return true;
    }
}
