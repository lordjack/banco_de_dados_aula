package view;

import controller.AlunoController;
import model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AlunoFormList {
    private JPanel panelMain;
    private JButton salvarBtn;
    private JButton cancelarBtn;
    private JTextField nomeTxt;
    private JTextField dt_nascimentoTxt;
    private JTextField matriculaTxt;


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

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaAlunoFormList");
        frame.setContentPane(new AlunoFormList().panelMain);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
