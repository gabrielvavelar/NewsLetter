package io.github.gabrielvavelar.Noticia.exception;

public class EmailDoesntExistsException extends RuntimeException{
    public EmailDoesntExistsException(String message){
        super(message);
    }
}
