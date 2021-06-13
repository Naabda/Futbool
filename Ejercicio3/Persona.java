/**
 * 
 * @date 13 jun. 2021
 * @author David Abellán Navarro (davidabellannavarro@hotmail.com)
 * @course 1º DAM
 */
package Ejercicio3;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Class Persona.
 */
public class Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6358320166985170705L;

	/** The nombre. */
	private String nombre;// Modificador de acceso private ,  Nombre entrenador.
	
	/** The fecha nacimiento. */
	private LocalDate fechaNacimiento;//Fecha de nacimineto.
	
	/** The altura. */
	private float altura; 
	
	/** The peso. */
	private float peso;
	
	/** The experiencia. */
	private int experiencia;//Numero acumulativo.
	
	/** The nom equipo. */
	private String nomEquipo;
	
	/** The sueldo. */
	private float sueldo;//Sueldo.
	
	
	
	/**
	 * Instantiates a new persona.
	 */
	public Persona() {//constructor vacío 
		this.nombre="";
		this.fechaNacimiento=null;
		this.altura=0;
		this.peso=0;
		this.experiencia=0;
		this.nomEquipo="";
		this.sueldo=0;
	}
	
	/**
	 * Instantiates a new persona.
	 *
	 * @param p the p
	 */
	public Persona(Persona p) { // constructor copia
		this.nombre=p.getNombre();
		this.fechaNacimiento=p.getFechaNacimiento();
		this.altura=p.getAltura();
		this.peso=p.getPeso();
		this.experiencia=p.getExperiencia();
		this.nomEquipo=p.getNomEquipo();
		this.sueldo=p.getSueldo();
	}
	
	
	
	/**
	 * Instantiates a new persona.
	 *
	 * @param nombre the nombre
	 * @param fechaNacimiento the fecha nacimiento
	 * @param altura the altura
	 * @param peso the peso
	 * @param experiencia the experiencia
	 * @param nomEquipo the nom equipo
	 * @param sueldo the sueldo
	 */
	public Persona(String nombre, LocalDate fechaNacimiento, float altura, float peso, int experiencia,
			String nomEquipo, float sueldo) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.experiencia = experiencia;
		this.nomEquipo = nomEquipo;
		this.sueldo = sueldo;
	}
	

	/**
	 * Instantiates a new persona.
	 *
	 * @param nombre the nombre
	 */
	public Persona(String nombre) {
		this.nombre = nombre;
	}
	
	
	/**
	 * Copiar.
	 *
	 * @param p the p
	 */
	public void copiar(Persona p) {
		this.nombre=p.getNombre();
		this.fechaNacimiento=p.getFechaNacimiento();
		this.altura=p.getAltura();
		this.peso=p.getPeso();
		this.experiencia=p.getExperiencia();
		this.nomEquipo=p.getNomEquipo();
		this.sueldo=p.getSueldo();
				
	}

//	@Override
//	public boolean equals(Object persona) {
//		boolean iguales=false;
//		if(persona != null) {
//			if( this.fechaNacimiento != null ) {
//				if( this.nombre.equals( ( (Persona)persona ).getNombre() ) && this.fechaNacimiento.equals( ((Persona)persona).getFechaNacimiento())  ) {
//					iguales=true;
//				}
//			}
//		}
//		
//		return iguales;
//	}
	
	
	/**
 * Modificar experiencia.
 *
 * @param exp the exp
 */
public void modificarExperiencia(int exp) {
		this.experiencia += exp;
	}
	
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Gets the altura.
	 *
	 * @return the altura
	 */
	public float getAltura() {
		return altura;
	}
	
	/**
	 * Sets the altura.
	 *
	 * @param altura the altura to set
	 */
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public float getPeso() {
		return peso;
	}
	
	/**
	 * Sets the peso.
	 *
	 * @param peso the peso to set
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	/**
	 * Gets the experiencia.
	 *
	 * @return the experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}
	
	/**
	 * Gets the nom equipo.
	 *
	 * @return the nomEquipo
	 */
	public String getNomEquipo() {
		return nomEquipo;
	}
	
	/**
	 * Sets the nom equipo.
	 *
	 * @param nomEquipo the nomEquipo to set
	 */
	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}
	
	/**
	 * Gets the sueldo.
	 *
	 * @return the sueldo
	 */
	public float getSueldo() {
		return sueldo;
	}
	
	/**
	 * Sets the sueldo.
	 *
	 * @param sueldo the sueldo to set
	 */
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", altura=" + altura + ", peso="
				+ peso + ", experiencia=" + experiencia + ", nomEquipo=" + nomEquipo + ", sueldo=" + sueldo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(altura);
		result = prime * result + experiencia;
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((nomEquipo == null) ? 0 : nomEquipo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode()); //¿Cómo funciona los parentesis ? 0 : nombre ?
		result = prime * result + Float.floatToIntBits(peso);// ¿Qué son floatToIntBits()?
		result = prime * result + Float.floatToIntBits(sueldo);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (Float.floatToIntBits(altura) != Float.floatToIntBits(other.altura))
			return false;
		if (experiencia != other.experiencia)
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (nomEquipo == null) {
			if (other.nomEquipo != null)
				return false;
		} else if (!nomEquipo.equals(other.nomEquipo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Float.floatToIntBits(peso) != Float.floatToIntBits(other.peso))
			return false;
		if (Float.floatToIntBits(sueldo) != Float.floatToIntBits(other.sueldo))
			return false;
		return true;
	}

}
