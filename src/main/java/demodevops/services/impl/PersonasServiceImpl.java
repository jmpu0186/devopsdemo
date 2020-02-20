package demodevops.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

import demodevops.constantes.Constantes;
import demodevops.models.BeanRpta;
import demodevops.models.BeanUpdate;
import demodevops.models.Persona;
import demodevops.models.PersonaApiRpta;
import demodevops.services.PersonasService;
@Singleton
public  class PersonasServiceImpl implements PersonasService{

	@Override
	public PersonaApiRpta getPersonas() {
		PersonaApiRpta rpta = new PersonaApiRpta();
		// TODO Auto-generated method stub
		if(Constantes.BDPERSONAS==null)
		{
			Constantes.BDPERSONAS = new ArrayList();
		}
		rpta.setCodigo_retorno("0000");
		rpta.setMensaje("Consulta OK");
		rpta.setPersonas(Constantes.BDPERSONAS);
		return rpta;
	}

	@Override
	public BeanRpta addPersona(Persona persona) {
		// TODO Auto-generated method stub
		BeanRpta rpta = new BeanRpta();
		if(!this.existeDni(persona.getDni()).isExiste())
		{
			Constantes.BDPERSONAS.add(persona);
			rpta.setCodigo_retorno("0000");
			rpta.setMensaje("Se registro persona con exito");
		}
		else {
			rpta.setCodigo_retorno("0001");
			rpta.setMensaje("Existe DNI "+persona.getDni());
		}
		return rpta;
	}

	@Override
	public BeanRpta updatePersona(Persona persona) {
		// TODO Auto-generated method stub
		BeanRpta rpta = new BeanRpta();
		BeanUpdate update = this.existeDni(persona.getDni());
		if(update.isExiste())
		{
			Constantes.BDPERSONAS.set(update.getId(),persona);
			rpta.setCodigo_retorno("0000");
			rpta.setMensaje("Se actualizo con exito");
		}
		else {
			rpta.setCodigo_retorno("0001");
			rpta.setMensaje("No existe persona con DNI "+persona.getDni());
		}
		return rpta;
	}

	@Override
	public BeanRpta deletePersona(int dni) {
		// TODO Auto-generated method stub
		BeanRpta rpta = new BeanRpta();
		BeanUpdate update = this.existeDni(dni);
		if(update.isExiste())
		{
			Constantes.BDPERSONAS.remove(update.getId());
			rpta.setCodigo_retorno("0000");
			rpta.setMensaje("Se borro registro con exito");
		}
		else {
			rpta.setCodigo_retorno("0001");
			rpta.setMensaje("No existe persona con DNI "+dni);
		}
		return rpta;
	}
	
	private BeanUpdate existeDni(int dni) {
		BeanUpdate rpta = new BeanUpdate();
		if(Constantes.BDPERSONAS==null)
			rpta.setExiste(false);
		Iterator it = Constantes.BDPERSONAS.iterator();
		int i=0;
		while(it.hasNext()) {
			
			Persona p = (Persona) it.next();
			if(p.getDni()==dni)
			{
				rpta.setExiste(true);
				rpta.setId(i);
			}
			i++;
		}
		return rpta;
	}

}
