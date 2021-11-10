package view;

import controller.AlunoController;
import model.Aluno;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class AlunoFormList {
    private JPanel panelMain;
    private JButton salvarOuEditarBtn;
    private JButton cancelarBtn;
    private JTextField nomeTxt;
    private JTextField dt_nascimentoTxt;
    private JTextField matriculaTxt;
    private JButton listarBtb;
    private JTable alunoJTable;
    private JPanel panelTable;
    private JTextField idTxt;
    private JButton deletarBtn;
    private JButton buscarBtn;
    String[] colunaNomes = {"Employee 1", "Employee 2"};


    public AlunoFormList() {

        listar();
        salvarOuEditarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoController alunoController = new AlunoController();
                Aluno aluno = new Aluno();
                aluno.setNome(nomeTxt.getText());
                aluno.setMatricula(matriculaTxt.getText());
                aluno.setDt_nascimento(dt_nascimentoTxt.getText());

                if (idTxt.getText().isEmpty()) {
                    alunoController.salvar(aluno);
                } else {
                    aluno.setId(Integer.parseInt(idTxt.getText()));
                    alunoController.atualizar(aluno);
                }

                //alunoController.testarConexao();
                //System.out.println("Cliquei no botão");
                listar();
                limpar();

                salvarOuEditarBtn.setText("Salvar");
            }
        });
        listarBtb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });
        alunoJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                idTxt.setText(alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 0).toString());

                //Object objNome = alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 1);
                matriculaTxt.setText(alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 1).toString());
                nomeTxt.setText(alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 2).toString());
                dt_nascimentoTxt.setText(alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 3).toString());

                deletarBtn.setVisible(true);
                salvarOuEditarBtn.setText("Editar");
            }
        });
        deletarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = JOptionPane.showConfirmDialog(
                        null,
                        "Deseja confirma a remoção do registro?"
                );

                if (i == JOptionPane.YES_OPTION && !idTxt.getText().isEmpty()) {

                    AlunoController alunoController = new AlunoController();
                    int id = Integer.parseInt(alunoJTable.getValueAt(alunoJTable.getSelectedRow(), 0).toString());

                    alunoController.remover(id);
                    listar();

                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para ser deletado");
                }
            }
        });
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
                listar();
            }
        });
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoController alunoController = new AlunoController();

                Aluno aluno = new Aluno();
                aluno.setNome(nomeTxt.getText());
                aluno.setMatricula(matriculaTxt.getText());
                aluno.setDt_nascimento(dt_nascimentoTxt.getText());

                ResultSet rs = alunoController.buscar(aluno);

                alunoJTable.setModel(DbUtils.resultSetToTableModel(rs));

                setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
            }
        });
    }

    public void limpar() {
        idTxt.setText(null);
        matriculaTxt.setText(null);
        nomeTxt.setText(null);
        dt_nascimentoTxt.setText(null);

        salvarOuEditarBtn.setText("Salvar");
    }

    public void listar() {
        AlunoController alunoController = new AlunoController();
        ResultSet rs = alunoController.listar();

        alunoJTable.setModel(DbUtils.resultSetToTableModel(rs));

        setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
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
