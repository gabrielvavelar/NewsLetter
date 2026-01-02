package io.github.gabrielvavelar.Noticia.service.sender;

public interface MessageSender {
    void send(String content, String to);
}
