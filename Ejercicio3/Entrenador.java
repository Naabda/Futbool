/**
 * 
 * @date 13 jun. 2021
 * @author David Abellán Navarro (davidabellannavarro@hotmail.com)
 * @course 1º DAM
 */

package Ejercicio3;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class Entrenador.
 */
public class Entrenador extends Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4399866646748262926L;

	/** The liderazgo. */
	//atributos sobre caracteristicas tecnicas
	int liderazgo;//1-100.
	
	/** The estrategia. */
	int estrategia;//1-100.
	
	/** The comunicacion. */
	int comunicacion;//1-100.
	
	//provocamos la ocultación del atributo heredado de Persona cambiando por ejemplo el tipo de dato a double
//	private double peso;
//	
//	@Override
//	getPeso() {
//		return this.peso;
//	}
	
	
	/**
	 * Instantiates a new entrenador.
	 *
	 * @param nombre the nombre
	 * @param fechaNacimiento the fecha nacimiento
	 * @param altura the altura
	 * @param peso the peso
	 * @param nomEquipo the nom equipo
	 * @param sueldo the sueldo
	 * @param experiencia the experiencia
	 * @param liderazgo the liderazgo
	 * @param estrategia the estrategia
	 * @param comunicacion the comunicacion
	 */
	
	// Constructor Sobrecargado con todos los atributos
	public Entrenador(String nombre, LocalDate fechaNacimiento, float altura, float peso, String nomEquipo,
			float sueldo, int experiencia, int liderazgo, int estrategia, int comunicacion) {
		
		super(nombre,fechaNacimiento,altura,peso,experiencia,nomEquipo,sueldo);
		
		this.liderazgo = liderazgo;
		this.estrategia = estrategia;
		this.comunicacion = comunicacion;
	}
	
	/**
	 * Instantiates a new entrenador.
	 *
	 * @param nombre the nombre
	 */
	// sobrecarga del constructor con solo atributo nombre
	public Entrenador(String nombre) {
		this.setNombre(nombre);
		
	}

	/**
	 * Instantiates a new entrenador.
	 */
	public Entrenador() { // constructor vacio 
		super();
		liderazgo=0;//1-100.
		estrategia=0;//1-100.
		comunicacion=0;//1-100.
	}
	
	/**
	 * Instantiates a new entrenador.
	 *
	 * @param e the e
	 */
	public Entrenador(Entrenador e) {
		super((Persona)e);
		this.liderazgo=e.getLiderazgo() ;//1-100.
		this.estrategia=e.getEstrategia();//1-100.
		this.comunicacion=e.getComunicacion();//1-100.
	}
	
	
	/**
	 * Copiar.
	 *
	 * @param e the e
	 */
	public void copiar(Entrenador e) {
		super.copiar((Persona)e);
		this.liderazgo=e.getLiderazgo() ;//1-100.
		this.estrategia=e.getEstrategia();//1-100.
		this.comunicacion=e.getComunicacion();//1-100.
	}
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return  super.toString() + ", liderazgo=" + liderazgo + ", estrategia=" + estrategia + ", comunicacion=" + comunicacion + "]";
	}



	/* Metodos implementados */
	
	/**
	 * Calcula el valor que aporta el entrenador al equipo.
	 * @return int
	 */
	public int calcValor() {
		int valor = Math.round((this.liderazgo + this.estrategia + this.comunicacion) /3);
		return valor;
	}
	
	
	
	/**
	 * Organizar entrenamiento.
	 *
	 * @param jugadores the jugadores
	 */
	public void organizarEntrenamiento(ArrayList<Futbolista> jugadores) {
		
	}
	
	
	/*
	 * 
		Para hacer las alineaciones de cada partido el entrenador debe de 
		ordenar sus jugadores (de mejor a peor) en base al valor (más es mejor) que aportan al equipo 
		y su posición en el terreno de juego de manera que elegirá a los mejores jugadores para el partido
		para cada posición (Portero, Defensa, Centro y Delantero) teniendo en cuenta que la alineación
		siempre será un 1-4-4-2.
	 */
	
	/*
	 * Obtener la lista de jugadores titulares para un partido.
	 * */
	
	/**
	 * Poner alineacion.
	 *
	 * @param jugadores the jugadores
	 * @return the array list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Futbolista> ponerAlineacion( ArrayList<Futbolista> jugadores ) {
		
//		Preparamos las estructuras de datos a utilizar
		
		ArrayList<Futbolista> porteros = new ArrayList<>();
		ArrayList<Futbolista> defensas = new ArrayList<>();
		ArrayList<Futbolista> centrocampistas = new ArrayList<>();
		ArrayList<Futbolista> delanteros = new ArrayList<>();
		
		
//		Parseamos el ArrayList con todos todos los jugadores y los añadimos según su posición en su respectivo ArrayList
		
		for (Futbolista futbolista : jugadores) {
			
			if(futbolista.getPosicion().equalsIgnoreCase("portero"))
				porteros.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("defensa"))
				defensas.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("centrocampista"))
				centrocampistas.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("delantero"))
				delanteros.add(futbolista);
			
		}
//		Ordenamos cada uno de los arrayList en orden "de mejor a peor" con respecto al método calcValor()
		Collections.sort(porteros);
		Collections.sort(defensas);
		Collections.sort(centrocampistas);
		Collections.sort(delanteros);

		
		//Vamos ahora a construir el ArrayList<Futbolista> alineacion en el que irán solo los jugadores titulares
		ArrayList<Futbolista> alineacion = new ArrayList<>();
		
		alineacion.addAll(porteros.subList(0, 1));//.subList() empieza en 0 y el último indicado no se incluye
		alineacion.addAll(defensas.subList(0, 4));
		alineacion.addAll(centrocampistas.subList(0, 4));
		alineacion.addAll(delanteros.subList(0, 2));
		
		return alineacion;
	}
	

	/* Getters and Setters */


	/**
	 * Gets the liderazgo.
	 *
	 * @return the liderazgo
	 */
	public int getLiderazgo() {
		return liderazgo;
	}


	/**
	 * Sets the liderazgo.
	 *
	 * @param liderazgo the liderazgo to set
	 */
	public void setLiderazgo(int liderazgo) {
		this.liderazgo = liderazgo;
	}


	/**
	 * Gets the estrategia.
	 *
	 * @return the estrategia
	 */
	public int getEstrategia() {
		return estrategia;
	}


	/**
	 * Sets the estrategia.
	 *
	 * @param estrategia the estrategia to set
	 */
	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}


	/**
	 * Gets the comunicacion.
	 *
	 * @return the comunicacion
	 */
	public int getComunicacion() {
		return comunicacion;
	}


	/**
	 * Sets the comunicacion.
	 *
	 * @param comunicacion the comunicacion to set
	 */
	public void setComunicacion(int comunicacion) {
		this.comunicacion = comunicacion;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + comunicacion;
		result = prime * result + estrategia;
		result = prime * result + liderazgo;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrenador other = (Entrenador) obj;
		if (comunicacion != other.comunicacion)
			return false;
		if (estrategia != other.estrategia)
			return false;
		if (liderazgo != other.liderazgo)
			return false;
		return true;
	}

}
