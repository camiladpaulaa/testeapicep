package org.aplicacao.excecoes;

// Exceção personalizada para tratar erros específicos da API
public class ApiException extends Exception{

    public ApiException(String mensagem) {
        super(mensagem);
        // Construtor que recebe uma mensagem de erro e passa para a classe base Exception
    }
}