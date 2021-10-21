package controller;

import database.Conexao;

public class AlunoController {
    private Conexao bd;

    public AlunoController() {
        this.bd = new Conexao();
    }

    public void testarConexao() {
        this.bd.getConexao();
        System.out.println("Conectou");
    }
}
