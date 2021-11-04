package view;

import controller.AlunoController;
import model.Aluno;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoFormList {
    private JPanel panelMain;
    private JButton salvarBtn;
    private JButton cancelarBtn;
    private JTextField nomeTxt;
    private JTextField dt_nascimentoTxt;
    private JTextField matriculaTxt;
    private JButton listarBt;
    private JTable alunoTb;
    private JPanel panalTb;
    private JPanel panelBtn;


    public AlunoFormList() {
        salvarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Aluno aluno = new Aluno();

                    aluno.setNome(nomeTxt.getText());
                    aluno.setDt_nascimento(dt_nascimentoTxt.getText());
                    aluno.setMatricula(matriculaTxt.getText());

                    nomeTxt.setText("");
                    dt_nascimentoTxt.setText("");
                    matriculaTxt.setText("");

                    AlunoController alunoController = new AlunoController();

                    alunoController.salvar(aluno);
                    System.out.println("Registro inserido com sucesso!");

                    listar();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        listarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listar();

            }
        });
    }

    public void listar() {
        AlunoController alunoController = new AlunoController();
        ResultSet rs = alunoController.listar();

        alunoTb.setModel(DbUtils.resultSetToTableModel(rs));
        //alunoTb.setTableHeader(null);
        setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
    }

    public void setNomeColuna(String[] colunas) {
        for (int i = 0; i < colunas.length; i++) {
            alunoTb.getColumnModel().getColumn(i).setHeaderValue(colunas[i]);
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
