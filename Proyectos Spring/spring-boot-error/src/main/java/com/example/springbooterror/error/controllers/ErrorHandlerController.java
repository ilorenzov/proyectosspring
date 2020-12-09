package com.example.springbooterror.error.controllers;

import com.example.springbooterror.error.errors.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice //manejador errores
public class ErrorHandlerController {
    @ExceptionHandler(ArithmeticException.class)
    public String aritmeticaError(Exception ex, Model model) {
        model.addAttribute("error", "Error Aritmetica");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/aritmetica";
    }
    @ExceptionHandler(NumberFormatException.class)
    public String formatoError(Exception ex, Model model) {
        model.addAttribute("error", "Formato numero incorrecto");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/numero-error";
    }
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public String usuarioNoEncontrado(UsuarioNoEncontradoException ex, Model model) {
        model.addAttribute("error", "Usuario no existe");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/500";
    }

}
