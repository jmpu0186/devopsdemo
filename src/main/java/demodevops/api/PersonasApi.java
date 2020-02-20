package demodevops.api;

import java.util.List;

import javax.inject.Inject;

import demodevops.models.BeanRpta;
import demodevops.models.Persona;
import demodevops.models.PersonaApiRpta;
import demodevops.services.PersonasService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/api/personas")
public class PersonasApi {

	@Inject
	PersonasService personaService;
	
	@Get("/findall")
	public PersonaApiRpta getPersonas(){
		
		return personaService.getPersonas();
	}
	
	@Post("/add")
	public BeanRpta addPersona(Persona persona) {
		return personaService.addPersona(persona);
	}
	
	@Post("/update")
	public BeanRpta updatePersona(Persona persona) {
		return personaService.updatePersona(persona);
	}
	
	@Post("/delete")
	public BeanRpta deletePersona(int dni) {
		return personaService.deletePersona(dni);
	}
	
}
