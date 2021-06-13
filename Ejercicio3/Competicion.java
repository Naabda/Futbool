/**
 * 
 * @date 13 jun. 2021
 * @author David Abellán Navarro (davidabellannavarro@hotmail.com)
 * @course 1º DAM
 */
package Ejercicio3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Competicion.
 */
public class Competicion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080520799018255589L;

	/** The equipos. */
	static ArrayList<Equipo> equipos = new ArrayList<>();

	/** The partidos. */
	static ArrayList<Partido> partidos = new ArrayList<>();
	
	/** The partidos Ida. */
	static ArrayList<Partido> ida = new ArrayList<>();
	
	/** The partidos Vuelta. */
	static ArrayList<Partido> vuelta = new ArrayList<>();
	
	/** The clasificacion. */
	static ArrayList<Equipo> clasificacion = new ArrayList<>();
	
	/** The titulo. */
	static Titulo titulo;
	
	/** The nom competicion. */
	static String nombreArchivo = "", nomCompeticion = "";
	
	/** The num equipos. */
	int numEquipos;
	
	/**
	 * Constructor vacio.
	 */
	public Competicion () {}
	
	/**
	 * Instantiates a new competicion.
	 *
	 * @param nomCompeticion the nom competicion
	 * @param titulo the titulo
	 * @param numEqParticipantes the num eq participantes
	 */
	public Competicion (String nomCompeticion, Titulo titulo, int numEqParticipantes) {
		Competicion.nombreArchivo = nomCompeticion+".txt";
		this.nomCompeticion = nomCompeticion;
		this.titulo = titulo;
		this.numEquipos = numEqParticipantes;
	}
	
	/**
	 * Utilizamos el @param equipos para saber los equipos de la competición.
	 * Con un primer bucle recorremos todos los equipos, con un segundo bucle
	 * los enfrentamos contra los demás equipos de forma que se generan los
	 * partidos de ida en el primero y de vuelta en el segundo bucle.
	 * 
	 * Con el if controlamos que no coincida un partido contra si mismo.
	 * 
	 */
	private static void ordenarPartidosCompeticion() {
		for (int i = 0; i < equipos.size(); i++) {
			for (int j = 0; j < equipos.size(); j++) {
				if (i!=j) {
					Partido partido = new Partido(equipos.get(i), equipos.get(j));
					partidos.add(partido);
				}
			}
		}
	}
	
	/**
	 * Organizar ida competicion.
	 */
	private static void organizarIdaCompeticion() {
		for (int i = 0; i < equipos.size(); i++) {
			for (int j = i; j < equipos.size(); j++) {
				if (i!=j) {
					Partido partido = new Partido(equipos.get(i), equipos.get(j));
					ida.add(partido);
				}
			}
		}
	}
	

	/**
	 * Organizar vuelta competicion.
	 */
	private static void organizarVueltaCompeticion() {
		for (int i = 0; i < equipos.size(); i++) {
			for (int j = i; j < equipos.size(); j++) {
				if (i!=j) {
					Partido partido = new Partido(equipos.get(j), equipos.get(i));
					vuelta.add(partido);
				}
			}
		}
	}
	/**
	 * Primero llamamos al metodo ordenarPartidosCompeticion()
	 * para que genere los partidos a jugar.
	 * Cogemos el @param partidos y lo recorremos de partido en partido
	 * calculando quien es el ganador o si hay empates a través de @method 
	 * calcularGanador de @class Partido y donde les anotará los puntos 
	 * obtenidos a cada uno.
	 * 
	 */
	public static void jugarLiga() {
		ordenarPartidosCompeticion();
		for (Partido partido : partidos) {
			partido.calcularGanador();
		}
		crearClasificacion();
	}
	
	/**
	 * Jugar liga por jornadas.
	 */
	public static void jugarLigaPorJornadas() {
		organizarIdaCompeticion();
		System.out.println("Jornadas Ida"+System.lineSeparator()+"Local \t| Visitante \t| Ganador | Perdedor");
		for (Partido partido : ida) {
			partido.calcularGanador();
			if (partido.empate) {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     X     |     X    ");
			} else if (!partido.empate && partido.ganador.equals(partido.local)) {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     1     |     0    ");
			} else {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     0     |     1    ");
			}
			partidos.add(partido);
		}
		organizarVueltaCompeticion();
		System.out.println("Jornadas Vuelta"+System.lineSeparator()+"Local \t| Visitante \t| Ganador | Perdedor");
		for (Partido partido : vuelta) {
			partido.calcularGanador();
			if (partido.empate) {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     X     |     X    ");
			} else if (!partido.empate && partido.ganador.equals(partido.local)) {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     1     |     0    ");
			} else {
				System.out.println(partido.getLocal().getNombre()+"\t| "+partido.getVisitante().getNombre()+"\t|     0     |     1    ");
			}
			partidos.add(partido);
		}
		crearClasificacion();
	}
	
	/**
	 * Este metodo va a crear los ArrayList de entrenadores
	 * jugadores y equipos a traves de la llamada de los
	 * metodos que le corresponda.
	 *
	 * @param numEquipos the num equipos
	 */
	public void crearDatosEquipos (int numEquipos) {
		this.numEquipos = numEquipos;
		ArrayList<Entrenador> entrenadores = crearEntrenadores();
		ArrayList<Futbolista> jugadores = crearJugadores();
		crearEquipos(entrenadores,jugadores);
	}
	
	//Implementar todas estas funciones.
	/**
	 * Crear entrenadores.
	 *
	 * @return ArrayList <Entrenador>
	 * 
	 * Generamos ArrayList de entrenadores y definimos un Entrenador.
	 * Abrimos un bucle con el numero de equipos que vamosa tener,
	 * y en cada iteración vamos definiendo el Entrenador
	 * y añadiendolo al ArrayList, los datos 
	 * del Entrenador nos los proporcionan otros metodos.
	 */
	private ArrayList<Entrenador> crearEntrenadores() {
		
		ArrayList<Entrenador> entrenadores = new ArrayList<>();
		Entrenador entrenador;
		
		for (int i = 0; i < numEquipos; i++) {
			entrenadores.add(entrenador = new Entrenador(ponerNombre(), ponerFnacimiento(true), altura(), peso(), ("Equipo"+i), sueldo(), 0, caracteristicas(), caracteristicas(), caracteristicas()));
		}
		return entrenadores;
	}

	/**
	 * Crear jugadores.
	 *
	 * @return ArrayList <Futbolista>
	 * 
	 * Generamos ArrayList de jugadores y definimos un futbolista.
	 * abrimos un bucle con el numero de equipos que queremos generar
	 * y un segundo bloque con la cantidad de jugadores que queremos 
	 * tener en cada equipo, y en cada iteración vamos creando los 
	 * futbolistas y añadiendolos al ArrayList, según la posicion del 
	 * bucle de futbolistas creará un portero, delantero... y los datos 
	 * de los futbolistas nos los proporcionan otros metodos.
	 */
	private ArrayList<Futbolista> crearJugadores() {
		
		ArrayList<Futbolista> jugadores = new ArrayList<>();
		Futbolista futbolista = new Futbolista();
		
		for (int i = 0; i < numEquipos; i++) {
			for (int j = 0; j < 22; j++) {
				if (j<4) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "delantero", caracteristicas(), categoria()));
				} else if (j>3 && j<12) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "centrocampista", caracteristicas(), categoria()));
				} else if (j>11 && j<20) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "defensa", caracteristicas(), categoria()));
				} else if (j>19 && j<22) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "portero", caracteristicas(), categoria()));
				}
			}
		}
		System.out.println("Creados");
		return jugadores;
	}
	
	/**
	 * Crear equipos.
	 *
	 * @param entrenadores Contiene todos los entrenadores que habrá en los equipos.
	 * @param jugadores Contiene todos los jugadores que habrá en los equipos.
	 * 
	 * Bucle que nos indica la cantidad de equipos que hay y en 
	 * cada iteracion crea un nuevo equipo con un nombre, fecha de fundacion 
	 * aleatoria y un nombre de presidente. Se le añade al equipo el entrenador
	 * que corresponde que coincide con el equipo. Y se genera un segundo bucle
	 * para ir añadiendo los jugadores que pertenecen a ese equipo. Por ultimo
	 * añadimos el equipo al ArrayList de equipos.
	 */
	private void crearEquipos(ArrayList<Entrenador> entrenadores, ArrayList<Futbolista> jugadores) {
		
		for (int i = 0; i < numEquipos; i++) {
			Equipo equipo = new Equipo(("Equipo"+i), ponerFnacimiento(false), ponerNombre());
			equipo.setEntrenador(entrenadores.get(i));
			ArrayList<Futbolista> futbolistas = new ArrayList<>();
			for (int j = 0; j < jugadores.size(); j++) {
				if (entrenadores.get(i).getNomEquipo().equals(jugadores.get(j).getNomEquipo())) {
					futbolistas.add(jugadores.get(j));
					equipo.setaEquipo(futbolistas);
				}
			}
			equipos.add(equipo);
		}
	}

	/**
	 * Poner nombre.
	 *
	 * @return String
	 * 
	 * Se genera un número aleatorio que es el indice del ArrayList
	 * de nombres y devuelve es nombre.
	 */
	private String ponerNombre () {
		ArrayList<String> nombres = new ArrayList<String>(Arrays.asList( "Alejandro", "Alex", "Alfonso", "Carlo", "Carlos", "David", "Domingo", "Fermín", "Ismael", "Israel", "Jaimito", "James", "Javier", "Juan", "JuanCarlos", "Pedro", "Pep", "Pepe", "Santos", "Sergio", "Tiverius", "Tomás", "Vicent", "Vicente" ));
		int num = (int) Math.round(Math.random()*23);
		return nombres.get(num);
	}
	
	/**
	 * Poner fnacimiento.
	 *
	 * @param bool Nos indicara si la fecha de nacimiento es para un 
	 * entrenador (true) o para un jugador (false)
	 * @return LocalDate
	 * 
	 * Recepcionamos un true/false, lo pasamos al if para que
	 * nos devuelva un valor y se lo entregamos a nacimiento
	 * para que nos calcula la fecha de nacimiento.
	 */
	private LocalDate ponerFnacimiento (boolean bool) {
		int num;
		LocalDate nacimiento = LocalDate.now();
		if (bool) {
			num = (int) Math.round(Math.random()*(55-30)+30);
		} else {
			num = (int) Math.round(Math.random()*(35-18)+18);
		}
		 nacimiento = nacimiento.minusYears(num);
		return nacimiento;
	}
	
	/**
	 * Altura.
	 *
	 * @return float
	 *  Nos devuelve una altura entre 1.60 metros y 2.00 metros.
	 */
	private float altura () {
		return (float) (Math.random()*(2.00-1.60)+1.60);
	}
	
	/**
	 * Peso.
	 *
	 * @return float
	 *  Nos devuelve un peso entre 60 Kilogramos y 100 kilogramos.
	 */
	private float peso () {
		return(float) (Math.random()*(100-60)+60);
	}
	
	/**
	 * Sueldo.
	 *
	 * @return float
	 * 
	 * Nos devuelve un sueldo mensual entre 1500€ y 5000€.
	 */
	private float sueldo () {
		return (float) (Math.random()*(5000-1500)+1500);
	}
	
	/**
	 * Caracteristicas.
	 *
	 * @return int
	 *  Nos devuelve una característica X entre 1 y 100.
	 */
	private int caracteristicas () {
		return (int) Math.round(Math.random()*100+1);
	}

	/**
	 * Categoria.
	 *
	 * @return String
	 * 
	 * Escoge aleatoriamente entre una de las categorías 
	 * disponibles y la retorna.
	 */
	private String categoria () {
		String [] cat = {"baja", "media", "alta", "estrella"};
		int indice = (int) Math.round(Math.random()*3);
		return cat[indice];
	}
	
	/**
	 * Genera la clasificacion de la competicion
	 * a traves de la recogida de los equipos y añadiendolos
	 * al ArrayList clasificacion para posteriormente 
	 * ordenarlos a traves de Collections sort.
	 */
	private static void crearClasificacion() {
		
		for (Equipo equipo : equipos) {
			clasificacion.add(equipo);
		}
		Collections.sort(clasificacion);
		guardarClasificacion();
	}

	/**
	 * Mostrar clasificacion.
	 *
	 * @return String
	 * 
	 * Genera una seduoTabla con la clasificacion de la 
	 * competicion extraida del ArrayList clasificacion
	 * y la devuelve en formato String.
	 */
	public static String mostrarClasificacion() {
		String clasificacionOrdenada = "Nombre Competición: "+nomCompeticion+" -- Título: "+titulo.getNombre()+" -- Año Competición: "+titulo.getAnyo()+System.lineSeparator()+"Equipo \t| Ganados | Perdidos | Empatados | Puntuación"+System.lineSeparator();
		for (Equipo equipo : clasificacion) {
			clasificacionOrdenada += equipo.getNombre()+" | "+equipo.getPatidosGanados()+" \t| "+equipo.getPartidosPerdidos()+" \t| "+equipo.getPartidosEmpatados()+" \t| "+equipo.getPuntos()+System.lineSeparator();
		}
		return clasificacionOrdenada;
	} //muestra por pantalla la tabla de clasificación

	/**
	 * Generamos un fichero de texto con el nombre de 
	 * la competicion y le añadimos la seudoTabla que
	 * nos devuelve el metodo mostrarClasificacion().
	 */
	public static void guardarClasificacion() {
		
		FileWriter fw = null;
		PrintWriter salida = null;
		
		try {
			fw = new FileWriter(nombreArchivo, true);
			salida = new PrintWriter(fw);
			salida.println(mostrarClasificacion());
			
			salida.flush();
	            
	        } catch (IOException ex) {
	            Logger.getLogger(Competicion.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	        }
		
	} //guarda en archivo de texto la tabla de clasificación 
	
	/**
	 * Cogemos el ArrayList clasificacion que tiene la 
	 * clasificacion ordenada de maxima puntuacion a menor
	 * y .
	 */
	private void asignarTitulo() {
		Competicion.clasificacion.get(0).anyadirTitulo(titulo);
	}
	
	/**
	 * Mostrar jugadores.
	 */
	/*
	 * Mostrar por pantalla una tabla con las características de cada jugador 
	 * que participa en la competición. Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	private void mostrarJugadores() {} 
	
	/**
	 * Mostrar entrenadores.
	 */
	/*
	 * Mostrar por pantalla una tabla con las características de cada entrenador 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	private void mostrarEntrenadores() {}
	
	
	/**
	 * Mostrar equipos.
	 */
	/*
	 * Mostrar por pantalla una tabla con las características de cada Equipo 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarEquipos() {
		System.out.println("Equipo \t| Fundación \t| Presidente \t| Entrenador \t| Patrimonio");
		for (Equipo equipo : equipos) {
			System.out.println(equipo.getNombre()+"\t|"+equipo.getFechFundacion()+"\t|"+equipo.getNombrePresidente()+"\t|"+equipo.getEntrenador().getNombre()+"\t|"+equipo.getPatrimonio());
		}
	}

	/**
	 * Gets the equipos.
	 *
	 * @return the equipos
	 */
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}

	/**
	 * Gets the partidos.
	 *
	 * @return the partidos
	 */
	public ArrayList<Partido> getPartidos() {
		return partidos;
	}


	/**
	 * Gets the clasificacion.
	 *
	 * @return the clasificacion
	 */
	public ArrayList<Equipo> getClasificacion() {
		return clasificacion;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public Titulo getTitulo() {
		return titulo;
	}

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Gets the num equipos.
	 *
	 * @return the num equipos
	 */
	public int getNumEquipos() {
		return numEquipos;
	}

	/**
	 * Gets the nom competicion.
	 *
	 * @return the nom competicion
	 */
	public static String getNomCompeticion() {
		return nomCompeticion;
	}

	/**
	 * Sets the nom competicion.
	 *
	 * @param nomCompeticion the new nom competicion
	 */
	public static void setNomCompeticion(String nomCompeticion) {
		Competicion.nomCompeticion = nomCompeticion;
	}

	/**
	 * Sets the equipos.
	 *
	 * @param equipos the new equipos
	 */
	public void setEquipos(ArrayList<Equipo> equipos) {
		Competicion.equipos = equipos;
	}

	/**
	 * Sets the partidos.
	 *
	 * @param partidos the new partidos
	 */
	public static void setPartidos(ArrayList<Partido> partidos) {
		Competicion.partidos = partidos;
	}

	/**
	 * Sets the clasificacion.
	 *
	 * @param clasificacion the new clasificacion
	 */
	public static void setClasificacion(ArrayList<Equipo> clasificacion) {
		Competicion.clasificacion = clasificacion;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public static void setTitulo(Titulo titulo) {
		Competicion.titulo = titulo;
	}

	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombreArchivo the new nombre archivo
	 */
	public static void setNombreArchivo(String nombreArchivo) {
		Competicion.nombreArchivo = nombreArchivo;
	}

	/**
	 * Sets the num equipos.
	 *
	 * @param numEquipos the new num equipos
	 */
	public void setNumEquipos(int numEquipos) {
		this.numEquipos = numEquipos;
	}
	
}
