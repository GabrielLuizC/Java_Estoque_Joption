package View;

import Control.EstoqueController;
import Model.Estoque;

import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

public class EstoqueView extends JFrame{

    public static void menuEstoque() throws ParseException {
        int escolhe = 0;

        Estoque estoque = new Estoque();
        Menu menu = new Menu();



        Object[] selectionValues = { "1", "2", "3", "4", "5" };
        String initialSelection = "1";
        Object recebeInt = showInputDialog(null, "1 - Cadastrar Estoque " +
                                                                "\n2 - Listar Estoque " +
                                                                "\n3 - Editar Estoque " +
                                                                "\n4 - Excluir Estoque " +
                                                                "\n5 - Voltar\n",
                "ESTOQUE", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (recebeInt == "1") {
            cadastrarEstoque();
            avancar();
        }
        if (recebeInt == "2") {
            listarEstoque();
            avancar();
        }
        if (recebeInt == "3") {
            editarEstoque(estoque, escolhe);
            avancar();
        }
        if (recebeInt == "4") {
            excluirEstoque();
            avancar();
        }
        if (recebeInt == "5") {
            menu.menu();
        }

        if(recebeInt == null) menu.menu();
    }

    public static void avancar() throws ParseException{
        javax.swing.JOptionPane.showMessageDialog(null, "APERTE ENTER PARA VALIDAR");
        menuEstoque();
    }

    public static void cadastrarEstoque() throws ParseException{
        EstoqueController estoqueController = new EstoqueController();
        Estoque estoque = new Estoque();

        String recebeNome = showInputDialog(null, "Digite o Nome do Insumo:" );
        if(recebeNome == null) menuEstoque();
        estoque.setNomeInsumo(recebeNome.toUpperCase());

        String recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
        if(recebeQnt == null) menuEstoque();
        estoque.setQntInsumo(Integer.parseInt(recebeQnt));

        String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
        if(recebePreco == null) menuEstoque();
        estoque.setPrecoInsumo(Double.parseDouble(recebePreco));

        estoqueController.cadastrarEstoque(estoque);
    }


    public static void listarEstoque(){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";

        for (Estoque estoque : estoqueController.listarEstoque()){
            recebe = recebe + " " + "ID: " + estoque.getIdInsumo() +
                    " | NOME: " + estoque.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque.getQntInsumo() +
                    " | PREÇO: " + estoque.getPrecoInsumo() +
                    " | VALIDADE: " + estoque.getRecebeData() + "\n";
        }
        JOptionPane.showMessageDialog(null, recebe);
    }

    /*public static void listarEstoque(){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";

        for (Estoque estoque : estoqueController.listarEstoque()){
            recebe = recebe + " " + "ID: " + estoque.getIdInsumo() +
                    " | NOME: " + estoque.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque.getQntInsumo() +
                    " | PREÇO: " + estoque.getPrecoInsumo() +
                    " | VALIDADE: " + estoque.getRecebeData() + "\n";
        }

    }*/

    public static void editarEstoque(Estoque estoque, int escolhe) throws ParseException{
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";

        for (Estoque estoque1 : estoqueController.listarEstoque()){
            recebe = recebe + " " + "       ID: " + estoque1.getIdInsumo() +
                    " | NOME: " + estoque1.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque1.getQntInsumo() +
                    " | PREÇO: " + estoque1.getPrecoInsumo() +
                    " | VALIDADE: " + estoque1.getRecebeData() + "                 \n";
        }

        String recebeInt = showInputDialog(null,recebe + "Digite qual o ID do Insumo a ser Editado: ", INFORMATION_MESSAGE);
        if(recebeInt == null) menuEstoque();
        estoque.setIdInsumo(Integer.parseInt(recebeInt));

        recebeInt = showInputDialog("Digite o que Deseja Editar: " +
                                    "\n1 - Nome" +
                                    "\n2 - Quantidade" +
                                    "\n3 - Preco" +
                                    "\n4 - Validade" +
                                    "\n5 - Editar Todas Opções");
        escolhe = Integer.parseInt(recebeInt);

        switch (escolhe){
            case 1:
                String recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque();
                estoque.setNomeInsumo(recebeNome.toUpperCase());
                break;
            case 2:
                String recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque();
                estoque.setQntInsumo(Integer.parseInt(recebeQnt));
                break;
            case 3:
                String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque();
                estoque.setPrecoInsumo(Double.parseDouble(recebePreco));
                break;
            case 4:
                estoque.setRecebeData(estoque.getData());
                break;
            case 5:
                recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque();
                estoque.setNomeInsumo(recebeNome.toUpperCase());

                recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque();
                estoque.setQntInsumo(Integer.parseInt(recebeQnt));

                recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque();
                estoque.setPrecoInsumo(Double.parseDouble(recebePreco));
                break;
        }
        estoqueController.editarEstoque(estoque, escolhe);
    }

    public static void excluirEstoque() throws ParseException{
        EstoqueController estoqueController = new EstoqueController();
        Estoque estoque = new Estoque();
        String recebe = "";

        for (Estoque estoque1 : estoqueController.listarEstoque()){
            recebe = recebe + " " + "       ID: " + estoque1.getIdInsumo() +
                    " | NOME: " + estoque1.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque1.getQntInsumo() +
                    " | PREÇO: " + estoque1.getPrecoInsumo() +
                    " | VALIDADE: " + estoque1.getRecebeData() + "                 \n";
        }

        String recebeInt = showInputDialog(null,recebe + "Digite qual o ID do Insumo a ser Excluido: ", INFORMATION_MESSAGE);
        if(recebeInt == null) menuEstoque();
        estoque.setIdInsumo(Integer.parseInt(recebeInt));

        estoqueController.excluirEstoque(estoque);
    }
}
