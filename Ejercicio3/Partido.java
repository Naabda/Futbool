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
 * The Class Partido.
 */
public class Partido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 564059396839536262L;

	/** The local. */
	Equipo local;
	
	/** The visitante. */
	Equipo visitante;
	
	/** The ganador. */
	Equipo ganador;
	
	/** The perdedor. */
	Equipo perdedor;
	
	/** The empate. */
	boolean empate = false;
	
	/** The localidad. */
	//int [] resultado;
	String localidad;
	
	/** The mvp. */
	Futbolista mvp;
	//ArrayList<Futbolista> goleadores;
	//Futbolista [] sancionados;
	//Futbolista [] lesionados;
	//Tarjeta [] listaSanciones; // ¿como hacerlo?

	/** The fecha. */
	LocalDate fecha;

	/**
	 * Instantiates a new partido.
	 *
	 * @param a Se asigna como equipo local
	 * @param b Se asigna como equipo visitante
	 */
	public Partido (Equipo a, Equipo b) {
		this.local = a;
		this.visitante = b;
	}

	/**
	 * Calcular ganador.
	 */
	public void calcularGanador() {
		
		int valorLocal = Math.round(local.calcValor() * modificadorAleatorio());
		int valorVisitante = Math.round(visitante.calcValor() * modificadorAleatorio());
		
		if ((valorLocal - valorVisitante)>10 && valorLocal > valorVisitante) {
			this.empate = false;
			//Gana equipo local
			asignarPuntos(this.local, this.visitante, false);
			this.local.setPatidosGanados(1);
			this.ganador = this.local;
			this.visitante.setPartidosPerdidos(1);
			this.perdedor = this.visitante;
			
		} else if ((valorLocal - valorVisitante)>10 && valorVisitante > valorLocal) {
			this.empate = false;
			// Gana equipo visitante
			asignarPuntos(this.visitante, this.local, false);
			this.local.setPartidosPerdidos(1);
			this.perdedor = this.local;
			this.visitante.setPatidosGanados(1);
			this.ganador = this.visitante;
			asignarPuntos(visitante, local, false);
		} else {
			this.empate = true;
			// Empate
			this.local.setPartidosEmpatados(1);
			this.visitante.setPartidosEmpatados(1);
			asignarPuntos(this.local, this.visitante, true);
			
		}
	}

	/**
	 * Modificador aleatorio.
	 *
	 * @return float
	 * 	En función del valor obtenido en numero nos
	 * 	retorna un multiplcador de -10% ó +10%.
	 */
	public float modificadorAleatorio() {

		int numero = (int) Math.round(Math.random());

		switch (numero) {
			case 0:
				return 0.90f;
			case 1:
				return 1.10f;
		}
		return 1;
	}



	/**
	 * Calcular MVP.
	 */
	public void calcularMVP() {			//Quien es el mejor jugador.
		/*En caso de empate a cero se le asigna a suerte a un portero
		 * si hay un máximo goleador a él 
		 * si hay más de uno se sortea a partes iguales.
		 */

	}
	
	/**
	 * Calcular goleadores.
	 */
	/*	
	public Equipo getGanador() {//En caso de empate resultado null.

	}

	public Equipo getPerdedor() {//En caso de empate resultado null.

	}

	private Futbolista[] getGoleadores() {//Obtenemos del calculo quienes son los goleadores.

	}


	 */	
	public void calcularGoleadores() {//Calcula quienes son los goleadores.
		/*Para la asignacion del gol el porcentaje según posición es:
		 * Delantero 70%, Centro 20%, Defensa 9% y portero 1%.
		 * Y después dividiremos entre los jugadores que hayan en esa categoría.
		 */

	}

	/**
	 * Asignar puntos.
	 *
	 * @param ganador the ganador
	 * @param perdedor the perdedor
	 * @param empate the empate
	 */
	public void asignarPuntos (Equipo ganador, Equipo perdedor, boolean empate) {
		int puntosG = ganador.getPuntos();
		int puntosP = perdedor.getPuntos();
		if (empate) {
			puntosG += 1;
			puntosP += 1;
			ganador.setPuntos(puntosG);
			perdedor.setPuntos(puntosP);
		} else {
			puntosG += 3;
			ganador.setPuntos(puntosG);
		}
	}

	/**
	 * Gets the local.
	 *
	 * @return the local
	 */
	public Equipo getLocal() {
		return local;
	}

	/**
	 * Sets the local.
	 *
	 * @param local the new local
	 */
	public void setLocal(Equipo local) {
		this.local = local;
	}

	/**
	 * Gets the visitante.
	 *
	 * @return the visitante
	 */
	public Equipo getVisitante() {
		return visitante;
	}

	/**
	 * Sets the visitante.
	 *
	 * @param visitante the new visitante
	 */
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	/**
	 * Gets the ganador.
	 *
	 * @return the ganador
	 */
	public Equipo getGanador() {
		return ganador;
	}

	/**
	 * Sets the ganador.
	 *
	 * @param ganador the new ganador
	 */
	public void setGanador(Equipo ganador) {
		this.ganador = ganador;
	}

	/**
	 * Gets the perdedor.
	 *
	 * @return the perdedor
	 */
	public Equipo getPerdedor() {
		return perdedor;
	}

	/**
	 * Sets the perdedor.
	 *
	 * @param perdedor the new perdedor
	 */
	public void setPerdedor(Equipo perdedor) {
		this.perdedor = perdedor;
	}

	/**
	 * Checks if is empate.
	 *
	 * @return true, if is empate
	 */
	public boolean isEmpate() {
		return empate;
	}

	/**
	 * Sets the empate.
	 *
	 * @param empate the new empate
	 */
	public void setEmpate(boolean empate) {
		this.empate = empate;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad the new localidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the mvp.
	 *
	 * @return the mvp
	 */
	public Futbolista getMvp() {
		return mvp;
	}

	/**
	 * Sets the mvp.
	 *
	 * @param mvp the new mvp
	 */
	public void setMvp(Futbolista mvp) {
		this.mvp = mvp;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	//	public int getGolesLocal(){}
	//	public int getGolesVisitante(){}
	//	
	//	public int getGoles(Equipo equipo) {}




	
	
}