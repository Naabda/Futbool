/**
 * 
 * @date 13 jun. 2021
 * @author David Abellán Navarro (davidabellannavarro@hotmail.com)
 * @course 1º DAM
 */
package Ejercicio3;

import java.io.Serializable;

/**
 * The Class Titulo.
 */
public class Titulo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7865433204111177298L;

	/** The anyo. */
	private int anyo;
	
	/** The nombre. */
	private String nombre;


	
	/**
	 * Instantiates a new titulo.
	 *
	 * @param anyo the anyo
	 * @param nombre the nombre
	 */
	public Titulo(int anyo, String nombre) {
		this.anyo = anyo;
		this.nombre = nombre;
	}
	
	/**
	 * Gets the anyo.
	 *
	 * @return the anyo
	 */
	public int getAnyo() {
		return anyo;
	}
	
	/**
	 * Sets the anyo.
	 *
	 * @param anyo the new anyo
	 */
	public void setAnyo(int anyo) {
		this.anyo = anyo;
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
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
