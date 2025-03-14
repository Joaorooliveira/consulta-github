package com.joaorooliveira.consultagithub;

public class ErroConsultaGitHubException extends RuntimeException{

    public ErroConsultaGitHubException(String mensagem){
        super(mensagem);
    }
}
