package com.bolsadeideas.springboot.app.sprintbootform.editors;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class NombreMayusculaEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase());

    }
}
