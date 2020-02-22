package demodevops.models;

import java.util.List;

public class PersonaApiRpta {

	private String codigo_retorno;
	private String mensaje;
	private String version;
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	private List<Persona> personas;
	public String getCodigo_retorno() {
		return codigo_retorno;
	}
	public void setCodigo_retorno(String codigo_retorno) {
		this.codigo_retorno = codigo_retorno;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	
}
