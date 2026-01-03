package io.github.gabrielvavelar.Noticia.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ModelAndView handleEmailAlreadyExists(
            EmailAlreadyExistsException ex,
            HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("home");
        mav.addObject("success", false);
        mav.addObject("message", ex.getMessage());
        mav.addObject("email", request.getParameter("email"));

        return mav;
    }

    @ExceptionHandler(InvalidUnsubscribeTokenException.class)
    public ModelAndView handleInvalidToken(InvalidUnsubscribeTokenException ex) {

        ModelAndView mav = new ModelAndView("unsubscribe-error");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneric(Exception ex) {

        log.error("Unexpected error", ex);

        ModelAndView mav = new ModelAndView("error");

        return mav;
    }
}

