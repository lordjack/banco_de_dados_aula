package view;

import controller.AlunoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                AlunoController alunoController = new AlunoController();
                alunoController.testarConexao();

                System.out.println("Cliquei no botão");
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
