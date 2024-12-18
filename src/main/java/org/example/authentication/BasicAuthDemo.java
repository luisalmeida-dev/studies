package org.example.authentication;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BasicAuthDemo {
    public static void main(String[] args) {
        /*
        - Criando um cliente com username e password para ser autenticado
        - Tambem existe a opcao de fazer a autenticacao passando pelo Header, so é mais trabalhoso.
        */
        HttpClient client = HttpClient.newBuilder().authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("postman", "password".toCharArray());
            }
        }).build();
        try {
            /*
            - Criando a requisicao a ser feita
            */
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://postman-echo.com/basic-auth")) //Utilizando uma URL que necessita de autenticacao
                    .build();
            /*
             - Enviando a requisicao utilizando o cliente a ser autentica e recebendo a resposta
            */
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
