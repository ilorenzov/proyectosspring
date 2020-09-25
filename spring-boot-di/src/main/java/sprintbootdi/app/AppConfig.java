package sprintbootdi.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import sprintbootdi.app.models.domain.ItemFactura;
import sprintbootdi.app.models.domain.Producto;
import sprintbootdi.app.models.services.IServicio;
import sprintbootdi.app.models.services.MiServicio;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean("miServicioSimple")
    public IServicio registrarMiServicio() {
        return new MiServicio();
    }

    @Primary
    @Bean("miServicioComplejo")
    public IServicio registrarMiServicioComplejo() {
        return new MiServicio();
    }

    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems() {
        Producto producto1 = new Producto("Camara sony", 100);
        Producto producto2 = new Producto("Bici", 200);
        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);
        return Arrays.asList(linea1, linea2);
    }
    @Bean("itemsFacturaOficina")
    public List<ItemFactura> registrarItemsOficina() {
        Producto producto1 = new Producto("Monitor LG", 250);
        Producto producto2 = new Producto("Notebook", 500);
        Producto producto3 = new Producto("Impresora", 300);
        Producto producto4 = new Producto("Escritorio", 1100);
        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);
        ItemFactura linea3 = new ItemFactura(producto3, 44);
        ItemFactura linea4 = new ItemFactura(producto4, 43);
        return Arrays.asList(linea1, linea2,linea3,linea4);
    }

}
