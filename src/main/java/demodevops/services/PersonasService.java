package demodevops.services;

import java.util.List;


import demodevops.models.BeanRpta;
import demodevops.models.Persona;
import demodevops.models.PersonaApiRpta;


public interface PersonasService {

	public PersonaApiRpta getPersonas();
	public BeanRpta addPersona(Persona persona);
	public BeanRpta updatePersona(Persona persona);
	public BeanRpta deletePersona(int dni);
}
