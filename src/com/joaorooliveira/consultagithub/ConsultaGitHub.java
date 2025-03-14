package com.joaorooliveira.consultagithub;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaGitHub {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 1) {
            System.out.println("Informe o nome do usuário:");
            String name = leitura.nextLine();
            String endereco = "https://api.github.com/users/" + name;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Se o status da resposta não for 200, lança a exceção personalizada
                if (response.statusCode() != 200) {
                    throw new ErroConsultaGitHubException("Nome de usuário não foi encontrado! Código HTTP: " + response.statusCode());
                }

                System.out.println(response.body());
                System.out.println("Para sair do programa digite 1");
                opcao = leitura.nextInt();
                leitura.nextLine(); // Consumir o '\n' do nextInt()

            } catch (ErroConsultaGitHubException e) {
                System.out.println(e.getMessage()); // Exibe a mensagem de erro sem quebrar a execução
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao realizar a requisição: " + e.getMessage());
            }
        }
        leitura.close();
    }
}

