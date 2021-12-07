package View;

import javax.swing.*;
import java.text.ParseException;


public class Menu extends JFrame{

    public void menu() throws ParseException {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = { "1 - ESTOQUE", "2 - SAIR" };
        String initialSelection = "1 - ESTOQUE";
        Object selection = JOptionPane.showInputDialog(null, "ESCOLHA UMA OPÇÃO PARA CONTINUAR",
                "ESTOQUE", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);

        if(selection == "1 - ESTOQUE"){
            EstoqueView.menuEstoque();
        }
        if(selection == "2 - SAIR"){
            javax.swing.JOptionPane.showMessageDialog(null, "APERTE ENTER PARA CONFIRMAR A SAIDA");
        }
    }
}
