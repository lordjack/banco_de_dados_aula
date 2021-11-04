package view;

import controller.AlunoController;
import model.Aluno;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AlunoFormList {
    private JPanel panelMain;
    private JButton salvarBtn;
    private JButton cancelarBtn;
    private JTextField nomeTxt;
    private JTextField dt_nascimentoTxt;
    private JTextField matriculaTxt;
    private JButton listarBtb;
    private JTable alunoJTable;
    private JPanel panelTable;
    String[] colunaNomes = {"Employee 1", "Employee 2"};


    public AlunoFormList() {
        salvarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Aluno aluno = new Aluno();
                aluno.setNome(nomeTxt.getText());
                aluno.setMatricula(matriculaTxt.getText());
                aluno.setDt_nascimento(dt_nascimentoTxt.getText());

                AlunoController alunoController = new AlunoController();
                alunoController.salvar(aluno);

                //alunoController.testarConexao();
                //System.out.println("Cliquei no botão");
            }
        });
        listarBtb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoController alunoController = new AlunoController();
                ResultSet rs = alunoController.listar();

                alunoJTable.setModel(DbUtils.resultSetToTableModel(rs));

                setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
            }
        });
    }


    public void setNomeColuna(String[] colunas) {
        for (int i = 0; i < colunas.length; i++) {
            alunoJTable.getColumnModel().getColumn(i).setHeaderValue(colunas[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaAlunoFormList");
        frame.setContentPane(new AlunoFormList().panelMain);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
