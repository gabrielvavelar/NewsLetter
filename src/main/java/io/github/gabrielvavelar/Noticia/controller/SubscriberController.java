package io.github.gabrielvavelar.Noticia.controller;

import io.github.gabrielvavelar.Noticia.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService service;

    @GetMapping("/home")
    public String showSubscribePage() {
        return "home";
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam String email, Model model) {
        service.subscribe(email);

        model.addAttribute("success", true);
        model.addAttribute("message", "Inscrição realizada com sucesso!");
        model.addAttribute("email", email);

        return "home";
    }

    @GetMapping("/unsubscribe/{unsubscribeToken}")
    public String unsubscribe(@PathVariable UUID unsubscribeToken) {
        service.unsubscribe(unsubscribeToken);
        return "unsubscribe-success";
    }
}
