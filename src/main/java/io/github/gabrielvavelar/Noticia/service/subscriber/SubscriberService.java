package io.github.gabrielvavelar.Noticia.service.subscriber;

import io.github.gabrielvavelar.Noticia.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.Noticia.exception.InvalidUnsubscribeTokenException;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;

    public void subscribe(String email) {
        if(repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);
        subscriber.setUnsubscribeToken(UUID.randomUUID());

        repository.save(subscriber);
    }

    public void unsubscribe(UUID unsubscribeToken) {
        Subscriber subscriber = repository.findByUnsubscribeToken(unsubscribeToken)
                .orElseThrow(() ->
                        new InvalidUnsubscribeTokenException("Invalid unsubscribe token")
                );

        repository.delete(subscriber);
    }

    public List<Subscriber> getAllSubscribers() {
        return repository.findAll();
    }
}
