package demodevops;


import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import demodevops.models.BeanRpta;
import demodevops.models.Persona;
import demodevops.models.PersonaApiRpta;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class DevopTest {

    @Inject
    @Client("/")
    RxHttpClient client;
    
    
    @Test
    public void addPersona() {
    	Persona personaInput = new Persona();
    	personaInput.setDni(43425181);
    	personaInput.setNombre("Juan Manuel");
    	personaInput.setApellido("Peña Uribe");
    	personaInput.setCelular(950951007);
    	personaInput.setDireccion("Jr. Francisco Gutierrez 685");
    	personaInput.setEdad(34);
    	BeanRpta result = client.toBlocking().retrieve(HttpRequest.POST("/api/personas/add", personaInput), BeanRpta.class);
    	assertEquals("0000",result.getCodigo_retorno());
    }
    
    
    @Test
    public void updatePersona() {
    	Persona personaInput = new Persona();
    	personaInput.setDni(43425181);
    	personaInput.setNombre("Juan Manuel Jecsan");
    	personaInput.setApellido("Peña Uribe");
    	personaInput.setCelular(950951007);
    	personaInput.setDireccion("Jr. Francisco Gutierrez 685");
    	personaInput.setEdad(34);
    	BeanRpta result = client.toBlocking().retrieve(HttpRequest.POST("/api/personas/update", personaInput), BeanRpta.class);
    	assertEquals("0000",result.getCodigo_retorno());
    }
    
    @Test
    public void findAll() {
    	PersonaApiRpta result = client.toBlocking().retrieve(HttpRequest.GET("/api/personas/findall"), PersonaApiRpta.class);
    	assertEquals("0000",result.getCodigo_retorno());
    }
    
}
