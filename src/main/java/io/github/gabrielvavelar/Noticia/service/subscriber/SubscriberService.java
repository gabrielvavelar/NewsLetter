package io.github.gabrielvavelar.Noticia.service.subscriber;

import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.Noticia.exception.EmailDoesntExistsException;
import io.github.gabrielvavelar.Noticia.mapper.SubscriberMapper;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper mapper;

    public SubscriberResponseDto subscribe(SubscriberRequestDto requestDto) {
        if(repository.existsByEmail(requestDto.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Subscriber subscriber = mapper.toEntity(requestDto);
        Subscriber saved = repository.save(subscriber);

        return mapper.toResponse(saved);
    }

    public void unsubscribe(SubscriberRequestDto requestDto) {
        Subscriber subscriber = repository.findByEmail(requestDto.email())
                .orElseThrow(() ->
                        new EmailDoesntExistsException("Email doesn't exists")
                );

        if (subscriber.isActive()) {
            subscriber.setActive(false);
            repository.save(subscriber);
        }
    }

    public List<String> getAllActiveEmails() {
        return repository.findAllByActiveTrue()
                .stream()
                .map(Subscriber::getEmail)
                .toList();
    }
}
