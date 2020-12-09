package sprintbootdi.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
public class MiServicioComplejo implements IServicio {
    @Override
    public String operacion() {
        return "ejecutando algun proceso importante... complicado";
    }
}
