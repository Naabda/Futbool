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

public class Equipo implements Comparable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3378929832944516568L;

	/** The nombre. */
	private String nombre;
	
	/** The a equipo. */
	private ArrayList<Futbolista> aEquipo;
	
	/** The entrenador. */
	private Entrenador entrenador;
	
	/** The localidad. */
	private String localidad;
	
	/** The fech fundacion. */
	private LocalDate fechFundacion;
	
	/** The alineacion. */
	private ArrayList<Futbolista> alineacion;//los once jugadores que seran titulares en un determinado partido
	
	/** The titulos. */
	private ArrayList<Titulo> titulos;
	
	/** The patrimonio. */
	private float patrimonio;
	
	/** The nombre presidente. */
	private String nombrePresidente;
	
	/** The equipacion. */
	private String [] equipacion; // tres equipaciones. Ej: 1."Blanco" , 2. "Azul", 3. "BlanquiAzul"  

	/** The puntos. */
	private int puntos; // representa los puntos del equipo en una competicion
	
	/** The patidos ganados. */
	private int patidosGanados=0;
	
	/** The partidos perdidos. */
	private int partidosPerdidos=0;
	
	/** The partidos empatados. */
	private int partidosEmpatados=0;

	
	// implementar todos los métodos
	/**
	 * Instantiates a new equipo.
	 *
	 * @param nombrEq the nombr eq
	 * @param fundacion the fundacion
	 * @param presidente the presidente
	 * @return int
	 * 
	 * Este método solicitará la alineación al entrenador, cogeremos esos jugadores y junto
	 * con el entrenador calcularemos su promedio de valor y lo retornaremos.
	 */
	public Equipo (String nombrEq, LocalDate fundacion, String presidente) {
		this.nombre = nombrEq;
		this.fechFundacion = fundacion;
		this.patrimonio = 0;
		this.nombrePresidente = presidente;
	}

	/**
	 * Calc valor.
	 *
	 * @return the int
	 */
	public  int calcValor() {//Capacidad de ganar un partido, es la media de sus jugadores alineados y su entrenador.
		int divisor = 0, valorEq = 0;
		
		this.alineacion = entrenador.ponerAlineacion(aEquipo);
		
		for (Futbolista futbolista : this.alineacion) {
			divisor++;
			valorEq += futbolista.calcValor();
		}
		
		valorEq += entrenador.calcValor();
		divisor++;
		
		valorEq = Math.round((valorEq/divisor));
		return valorEq; 
	}
	
	/**
	 * Anyadir puntos.
	 *
	 * @param punto the punto
	 */
	public  void anyadirPuntos(int punto) {
		this.puntos += punto;
	}
	
	/**
	 * Anyadir titulo.
	 *
	 * @param titulo the titulo
	 */
	public  void anyadirTitulo(Titulo titulo) {
		this.titulos.add(titulo);
	}
	
	/**
	 * Actualizar patrimonio.
	 *
	 * @param dinero Cantidad de dinero que puede ser positiva o 
	 * negativa en función de si es incremento o 
	 * decremento que al sumarlo al patrimonio 
	 * actual lo incrementará o decrementará
	 */
	public  void actualizarPatrimonio (float dinero) {
		this.patrimonio += dinero;
	}
	
	/**
	 * Anyadir futbolista.
	 *
	 * @param fichaje Recepcionamos el nuevo fichaje.
	 * 
	 * Recibimos un nuevo futbolista y lo añadimos al
	 * ArrayList del equipo.
	 */
	public  void anyadirFutbolista (Futbolista fichaje) {
		this.aEquipo.add(fichaje);
	}
	
	/**
	 * Eliminar futbolista.
	 *
	 * @param despedido Futbolista que se quiere quitar del ArrayList del equipo.
	 * 
	 * Inicializamos el indice en cero, recorremos el ArrayList
	 * del equipo y comprobamos si coincide con el que queremos
	 * eliminar, en caso de coincidir lo elimanos.
	 */
	public  void eliminarFutbolista (Futbolista despedido) {
		int contador = 0, indice = 0;
		for (Futbolista futbolista : aEquipo) {
			if (futbolista.equals(despedido)) {
				indice = contador;
			}
			contador++;
		}
		aEquipo.remove(indice);
	}
	
	/**
	 * Cambiar entrenador.
	 *
	 * @param nuevo the nuevo
	 */
	public  void cambiarEntrenador (Entrenador nuevo) {
		this.entrenador = nuevo;
	}
	
	/**
	 * Compare to.
	 *
	 * @param equipoComparado the equipo comparado
	 * @return the int
	 */
	@Override
	public int compareTo(Object equipoComparado) {
		/*
		 * El que más puntos tenga.
		 * A empate de puntos se pasa a ver quien tiene más goles.
		 * En caso de empate de todo orden alfabético del nombre de equipo.
		 */
		int resultado=0;

		if( equipoComparado instanceof Equipo ) {

			float puntuacionEquipoComparado=((Equipo)equipoComparado).getPuntos();

			if( this.getPuntos() >  puntuacionEquipoComparado ) {
				resultado = 1; //valor positivo, yo soy mayor.
			}
			else if(this.getPuntos() <  puntuacionEquipoComparado) {
				resultado = -1; // valor negativo, el objetoComparado es mayor
			}
			else {
				resultado=0; // valor cero indica igualdad
			}
			
		} 
		else {
			//si el objeto comparado no es de este tipo equipoComparable devolveremos 0 igualmente, podríamos hacer otras acciones.
			System.err.println("Error: comparando objeto Futbolista con otro tipo de objeto:");
			System.err.print(equipoComparado.getClass());
			
		}

		// Si queremos invertir el orden devuelto para que sea descendente
		resultado*= -1;
	
		return resultado;
	
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
	 * Gets the a equipo.
	 *
	 * @return the aEquipo
	 */
	public ArrayList<Futbolista> getaEquipo() {
		return aEquipo;
	}
	
	/**
	 * Sets the a equipo.
	 *
	 * @param aEquipo the aEquipo to set
	 */
	public void setaEquipo(ArrayList<Futbolista> aEquipo) {
		this.aEquipo = aEquipo;
	}
	
	/**
	 * Gets the entrenador.
	 *
	 * @return the entrenador
	 */
	public Entrenador getEntrenador() {
		return entrenador;
	}
	
	/**
	 * Sets the entrenador.
	 *
	 * @param entrenador the entrenador to set
	 */
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
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
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	/**
	 * Gets the fech fundacion.
	 *
	 * @return the fechFundacion
	 */
	public LocalDate getFechFundacion() {
		return fechFundacion;
	}
	
	/**
	 * Sets the fech fundacion.
	 *
	 * @param fechFundacion the fechFundacion to set
	 */
	public void setFechFundacion(LocalDate fechFundacion) {
		this.fechFundacion = fechFundacion;
	}
	
	/**
	 * Gets the alineacion.
	 *
	 * @return the alineacion
	 */
	public ArrayList<Futbolista> getAlineacion() {
		return alineacion;
	}
	
	/**
	 * Sets the alineacion.
	 *
	 * @param alineacion the alineacion to set
	 */
	public void setAlineacion(ArrayList<Futbolista> alineacion) {
		this.alineacion = alineacion;
	}
	
	/**
	 * Gets the titulos.
	 *
	 * @return the titulos
	 */
	public ArrayList<Titulo> getTitulos() {
		return titulos;
	}
	
	/**
	 * Sets the titulos.
	 *
	 * @param titulos the titulos to set
	 */
	public void setTitulos(ArrayList<Titulo> titulos) {
		this.titulos = titulos;
	}
	
	/**
	 * Gets the patrimonio.
	 *
	 * @return the patrimonio
	 */
	public float getPatrimonio() {
		return patrimonio;
	}
	
	/**
	 * Sets the patrimonio.
	 *
	 * @param patrimonio the patrimonio to set
	 */
	public void setPatrimonio(float patrimonio) {
		this.patrimonio = patrimonio;
	}
	
	/**
	 * Gets the nombre presidente.
	 *
	 * @return the nombrePresidente
	 */
	public String getNombrePresidente() {
		return nombrePresidente;
	}
	
	/**
	 * Sets the nombre presidente.
	 *
	 * @param nombrePresidente the nombrePresidente to set
	 */
	public void setNombrePresidente(String nombrePresidente) {
		this.nombrePresidente = nombrePresidente;
	}
	
	/**
	 * Gets the equipacion.
	 *
	 * @return the equipacion
	 */
	public String[] getEquipacion() {
		return equipacion;
	}
	
	/**
	 * Sets the equipacion.
	 *
	 * @param equipacion the equipacion to set
	 */
	public void setEquipacion(String[] equipacion) {
		this.equipacion = equipacion;
	}
	
	/**
	 * Gets the puntos.
	 *
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Sets the puntos.
	 *
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	/**
	 * Gets the patidos ganados.
	 *
	 * @return the patidosGanados
	 */
	public int getPatidosGanados() {
		return patidosGanados;
	}
	
	/**
	 * Sets the patidos ganados.
	 *
	 * @param patidosGanados the patidosGanados to set
	 */
	public void setPatidosGanados(int patidosGanados) {
		this.patidosGanados += patidosGanados;
	}
	
	/**
	 * Gets the partidos perdidos.
	 *
	 * @return the partidosPerdidos
	 */
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	
	/**
	 * Sets the partidos perdidos.
	 *
	 * @param partidosPerdidos the partidosPerdidos to set
	 */
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos += partidosPerdidos;
	}
	
	/**
	 * Gets the partidos empatados.
	 *
	 * @return the partidosEmpatados
	 */
	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}
	
	/**
	 * Sets the partidos empatados.
	 *
	 * @param partidosEmpatados the partidosEmpatados to set
	 */
	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados += partidosEmpatados;
	}
	
}
