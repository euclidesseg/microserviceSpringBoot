package com.example.userservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// esta clase nos ayudara a comunicarnos con los demas microservicios
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restemplate(){
        return new RestTemplate();
    }
}




/* La anotación @Configuration 
 * en Java se utiliza para marcar una clase como una clase de configuración. 
 * Esta anotación indica que la clase contiene métodos de configuración que definen 
 * cómo se deben configurar y crear los componentes de una aplicación.
 */

/* Ayuda a mantener un código modular y flexible, permitiendo una fácil modificación 
 * de la configuración sin afectar otras partes del sistema. 
 */



/* @Bean se utiliza para marcar un método dentro de una clase de configuración 
 * (@Configuration) como un método de creación de un bean. 
 * Un bean es un objeto gestionado y almacenado por el contenedor de Spring(ApplicationContext), que se crea, 
 * configura y administra mediante Spring IoC (Inversion of Control).
 */

 /*
  * Un bean debe retornar siempre el objeto bean creado como en este ejemplo lo hemos visto
  */