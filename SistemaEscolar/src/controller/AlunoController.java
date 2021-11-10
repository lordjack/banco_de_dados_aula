package controller;

import database.Conexao;
import model.Aluno;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoController {
    private Conexao bd;

    public AlunoController() {
        this.bd = new Conexao();
    }

    public void salvar(Aluno aluno) {
        try {
            String sql;
            sql = "INSERT INTO aluno (nome, matricula, dt_nascimento) VALUES(?,?,?)";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDt_nascimento());
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void testarConexao() {
        this.bd.getConexao();
        System.out.println("Conectou");
    }

    public ResultSet listar() {
        try {

            //ArrayList dados = new ArrayList();
            PreparedStatement ps = bd.getConexao().prepareStatement("SELECT * FROM aluno");
            ResultSet rs = ps.executeQuery();
            /*
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("nome"));
                System.out.println(rs.getString("matricula"));
                System.out.println(rs.getString("dt_nascimento"));
            }*/
            // System.out.println(dados);
            return rs;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }

    public void atualizar(Aluno aluno) {
        try {
            String sql;
            sql = "UPDATE aluno SET nome = ?, matricula = ?, dt_nascimento = ? WHERE id = ?";

            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);

            stmt.setInt(4, aluno.getId());
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDt_nascimento());
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void remover(int id) {
        try {
            System.out.println("id: " + id);
            String sql;
            if (id != 0) {
                sql = "DELETE FROM aluno WHERE id = ?";
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);

                stmt.setInt(1, id);
                stmt.execute();
                stmt.close();
            } else {
                throw new RuntimeException("Selecione um registro para ser deletado");
            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ResultSet buscar(Aluno aluno) {
        try {
            String sql = "";
            if (!aluno.getNome().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE nome LIKE '%" + aluno.getNome() + "%' ";

            } else if (!aluno.getMatricula().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE matricula LIKE '%" + aluno.getMatricula() + "%' ";

            } else if (!aluno.getDt_nascimento().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE dt_nascimento LIKE '%" + aluno.getDt_nascimento() + "%' ";
            }

            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            return rs;

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }
}
