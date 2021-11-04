package controller;

import database.Conexao;
import model.Aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoController {
    private Conexao bd;

    public AlunoController() {
        this.bd = new Conexao();
    }

    public void testarConexao() {
        this.bd.getConexao();
        System.out.println("Conectou");
    }

    public void salvar(Aluno aluno) throws SQLException {
        String sql;
        try {
            sql = "INSERT INTO aluno (nome, matricula, dt_nascimento) values (?, ?, ?)";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDt_nascimento());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public ResultSet listar() {
        String sql;
        try {
            sql = "SELECT * FROM aluno";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
