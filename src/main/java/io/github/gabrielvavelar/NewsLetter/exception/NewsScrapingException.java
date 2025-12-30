package io.github.gabrielvavelar.NewsLetter.exception;

public class NewsScrapingException extends RuntimeException{
    public NewsScrapingException(String message,  Throwable cause) {
        super(message, cause);
    }
}
