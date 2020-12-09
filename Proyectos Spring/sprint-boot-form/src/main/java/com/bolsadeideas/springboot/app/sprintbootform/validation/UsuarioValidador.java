package com.bolsadeideas.springboot.app.sprintbootform.validation;

import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);

    }
    @Override
    public void validate(Object target, Errors errors) {
 /*       Usuario usuario = (Usuario) target;*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nombre", "requerido.usuario.nombre"); //rechazar si es vacio o espacios en blanco
/*        if(!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
            errors.rejectValue("identificador","pattern.usuario.identificador");
        }*/

    }

}
