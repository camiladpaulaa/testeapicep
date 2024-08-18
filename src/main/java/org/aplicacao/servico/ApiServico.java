package org.aplicacao.servico;

import com.fasterxml.jackson.databind.ObjectMapper; // Biblioteca para converter JSON em objetos Java e vice-versa
import org.aplicacao.dto.EnderecoDto; // DTO usado para mapear os dados da resposta da API
import org.aplicacao.excecoes.ApiException; // Exceção personalizada para tratar erros na chamada da API

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServico {

    // Metodo que faz a requisição à API ViaCEP e retorna um objeto EnderecoDto
    public EnderecoDto getEndereco(String cep) throws IOException, InterruptedException {

        try {
            HttpClient client = HttpClient.newHttpClient(); // Criação de um cliente HTTP para fazer a requisição

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://viacep.com.br/ws/" + cep + "/json/")).build();
            // Construção de uma requisição HTTP GET para a URL da API ViaCEP

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Envia a requisição e captura a resposta como uma string

            if(response.statusCode() != 200){
                throw new ApiException("Erro ao efetuar requisição - Status " + response.statusCode());
                // Verifica se o status da resposta não é 200 (OK), caso contrário, lança uma exceção personalizada
            }

            ObjectMapper mapper = new ObjectMapper();
            // ObjectMapper é usado para converter a string JSON da resposta em um objeto Java

            if(enderecoDto != null){
                enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);
                System.out.println(enderecoDto);
                // Converte a resposta JSON em um objeto EnderecoDto se o objeto não for nulo
            } else {
                throw new ApiException("Erro ao efetuar a requisição - Objeto Nulo");
                // Lança uma exceção se o objeto EnderecoDto for nulo (isso é desnecessário e pode ser removido)
            }

            enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);
            // Converte a resposta JSON em um objeto EnderecoDto (linha repetida que pode ser removida)

        } catch (ApiException e) {
            System.err.println("Erro geral ao efetuar a requisição na API: " + e.getMessage());
            // Captura exceções personalizadas e imprime uma mensagem de erro
        }

        return enderecoDto; // Retorna o objeto EnderecoDto
    }

    private EnderecoDto enderecoDto; // Variável de instância para armazenar o objeto EnderecoDto
}
