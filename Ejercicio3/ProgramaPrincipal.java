/**
 * 
 * @date 13 jun. 2021
 * @author David Abellán Navarro (davidabellannavarro@hotmail.com)
 * @course 1º DAM
 * @github https://github.com/Naabda/Futbool
 */
package Ejercicio3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Class ProgramaPrincipal.
 */
public class ProgramaPrincipal {

	/** The competicion. */
	private static Competicion competicion = new Competicion();
	
	/** The equipos. */
	private static ArrayList <Equipo> equipos;
	
	/** The sc. */
	private static Scanner sc = new Scanner(System.in);
	
	/** The controlador jugadores. */
	private static boolean controladorCompeticion = false, controladorJugadores = false;

	/**
	 * Menu.
	 */
	private static void menu() {

		int opcion = -1;
		boolean salida = false;

		do {
			System.out.println("\t _-MENU PRINCIPAL-_");
			System.out.println("1. Menú competición.");
			System.out.println("2. Menú Jugadores.");
			System.out.println("3. Menú Entrenadores.");
			System.out.println("4. Menú Equipos.");
			System.out.println("0. Salir.");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Formato incorrecto, seleccione un número de 0 a 4");
			}

			switch (opcion) {
			case 1:
				menuCompeticion();
				break;

			case 2:
				if (controladorCompeticion)
					menuJugadores();
				else
					System.out.println("Primero crea una competición");
				break;

			case 3:
				if (controladorCompeticion)
					menuEntrenadores();
				else
					System.out.println("Primero crea una competición");
				break;

			case 4:
				if (controladorCompeticion)
					menuEquipos();
				else
					System.out.println("Primero crea una competición");
				break;

			case 0:
				salida=true;
				break;

			default:
				System.out.println("Inserte una opción correcta");
				break;
			}

		}while (!salida);
	}

	/**
	 * Menu competicion.
	 */
	private static void menuCompeticion() {

		int opcion = -1, numEq = 0, anyo = 0;
		boolean salida = false;

		do {
			System.out.println("\t _-MENU COMPETICION-_");
			System.out.println("1. Generar equipos, jugadores y entrenadores.");
			System.out.println("2. Jugar Liga completa.");
			System.out.println("3. Jugar Liga jornada a jornada.");
			System.out.println("4. Mostrar Clasificación final.");
			System.out.println("0. Salir.");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Formato incorrecto, seleccione un número de 0 a 3");
			}

			switch (opcion) {
			case 1:
				controladorCompeticion = true;
				System.out.println("Nombre de la competicion");
				String nombreCompeticion = sc.nextLine();
				System.out.println("Nombre del título");
				String nombreTitulo = sc.nextLine();
				System.out.println("Año de la competición");
				try {
					anyo = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException ex) {
					System.out.println("Formato incorrecto, seleccione un número positivo correcto");
				}
				Titulo titulo = new Titulo(anyo, nombreTitulo);
				try {
					System.out.println("¿Cuantos equipos tenemos para esta competición?");
					numEq = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException ex) {
					System.out.println("Formato incorrecto, seleccione un número positivo correcto");
				}
				competicion = new Competicion(nombreCompeticion, titulo, numEq);
				competicion.crearDatosEquipos(numEq);
				System.out.println("Equipos con nuevos hinchas..."+System.lineSeparator()+"Entrenadores entrenando..."+System.lineSeparator()+"Jugadores poniendose las espinilleras"+System.lineSeparator());
				break;

			case 2:
				if (competicion == null || competicion.getEquipos().isEmpty()) {
					System.out.println("Genera primero la opcion 1.");
				} else {
					System.out.println(competicion.getEquipos());
					Competicion.jugarLiga();
				}
				break;
				
			case 3:
				if (competicion == null || competicion.getEquipos().isEmpty()) {
					System.out.println("Genera primero la opcion 1.");
				} else {
					System.out.println(competicion.getEquipos());
					Competicion.jugarLigaPorJornadas();
				}
				break;

			case 4:
				if (competicion == null || competicion.getClasificacion().isEmpty()) {
					System.out.println("Genera primero la opcion 2.");
				} else {
					guardarClasificacion();
					System.out.println(Competicion.mostrarClasificacion());
				}
				break;

			case 0:
				salida=true;
				break;

			default:
				System.out.println("Inserte una opción correcta");
				break;
			}

		}while (!salida);
	}

	/**
	 * Menu jugadores.
	 */
	private static void menuJugadores() {

		int opcion = -1;
		boolean salida = false;

		do {
			System.out.println("\t _-MENU JUGADORES-_");
			System.out.println("1. Crear jugador.");
			System.out.println("2. Eliminar jugador.");
			System.out.println("3. Editar jugador.");
			System.out.println("4. Mostrar jugador.");
			System.out.println("5. Mostrar todos los jugadores.");
			System.out.println("0. Salir.");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Formato incorrecto, seleccione un número de 0 a 5");
			}

			switch (opcion) {
			case 1:
				crearJugador();
				controladorJugadores = true;
				break;

			case 2:
				eliminarJugador();
				break;

			case 3:
				editarJugador();
				break;

			case 4:
					mostrarJugador();
				break;
				
			case 5:
				mostrarJugadores();
				break;

			case 0:
				salida=true;
				break;

			default:
				System.out.println("Inserte una opción correcta");
				break;
			}

		}while (!salida);
	}

	/**
	 * Menu entrenadores.
	 */
	private static void menuEntrenadores() {

		int opcion = -1;
		boolean salida = false;

		do {
			System.out.println("\t _- MENU ENTRENADORES -_");
			System.out.println("1. Crear entrenador.");
			System.out.println("2. Eliminar entrenador.");
			System.out.println("3. Editar entrenador.");
			System.out.println("4. Mostrar todos los entrenadores.");
			System.out.println("0. Salir.");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Formato incorrecto, seleccione un número de 0 a 4");
			}

			switch (opcion) {
			case 1:
				crearEntrenador();
				break;

			case 2:
				eliminarEntrenador();
				break;

			case 3:
				editarEntrenador();
				break;

			case 4:
				mostrarEntrenadores();
				break;

			case 0:
				salida=true;
				break;

			default:
				System.out.println("Inserte una opción correcta");
				break;
			}

		}while (!salida);
	}

	/**
	 * Menu equipos.
	 */
	private static void menuEquipos() {

		int opcion = -1;
		boolean salida = false;

		do {
			System.out.println("\t _- MENU EQUIPOS -_");
			System.out.println("1. Crear Equipo.");
			System.out.println("2. Eliminar equipo.");
			System.out.println("3. Editar equipo.");
			System.out.println("4. Mostrar equipo.");
			System.out.println("5. Mostrar todos los equipos.");
			System.out.println("6. Guardar equipos en fichero.");
			System.out.println("7. Cargar los equipos desde fichero.");
			System.out.println("0. Salir.");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Formato incorrecto, seleccione un número de 0 a 7");
			}

			switch (opcion) {
			case 1:
				crearEquipo();
				break;

			case 2:
				eliminarEquipo();
				break;

			case 3:
				editarEquipo();
				break;

			case 4:
				mostrarEquipo();
				break;

			case 5:
				mostrarEquipos();
				break;

			case 6:
				guardarEquipos();
				break;

			case 7:
				cargarEquipos();
				break;
				
			case 0:
				salida=true;
				break;

			default:
				System.out.println("Inserte una opción correcta");
				break;
			}

		}while (!salida);
	}

	/**
	 * Guardar clasificacion.
	 */
	private static void guardarClasificacion() {
		
		FileWriter fw = null;
		PrintWriter salida = null;
		String nombreArchivo = "tablas_clasificatorias.txt";
		
		try {
			fw = new FileWriter(nombreArchivo, true);
			salida = new PrintWriter(fw);
			salida.println(competicion.mostrarClasificacion());
			
			salida.flush();
	            
	        } catch (IOException ex) {
	            Logger.getLogger(Competicion.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	        }
	}
	
	/**
	 * Crear jugador.
	 */
	private static void crearJugador() {

		float altura = 0, peso = 0, sueldo = 0;
		int anyos = 0, dorsal = 0, velocidad = 0, resistencia = 0, fuerza = 0, potencia = 0, control = 0, pase = 0, chute = 0, goles = 0, experiencia = 0;
		LocalDate nacimiento = LocalDate.now();
		String nomeq = "", nombre = "", posicion = "", categoria = "";

		System.out.println("Equipos disponibles ¿En cuál fichamos el futbolista?");
		competicion.mostrarEquipos();
		System.out.println("Nombre del equipo al que pertenece el jugador: ");
		nomeq = sc.nextLine();
		System.out.println("Nombre del jugador: ");
		nombre = sc.nextLine();
		System.out.println("Posición: ");
		posicion = sc.nextLine();
		System.out.println("Categoría: ");
		categoria = sc.nextLine();

		try {
			System.out.println("Años: ");
			anyos = Integer.parseInt(sc.nextLine());
			System.out.println("Altura: ");
			altura = Float.parseFloat(sc.nextLine());
			System.out.println("Peso: ");
			peso = Float.parseFloat(sc.nextLine());
			System.out.println("Dorsal: ");
			dorsal = Integer.parseInt(sc.nextLine());
			System.out.println("Sueldo: ");
			sueldo = Float.parseFloat(sc.nextLine());
			System.out.println("Velocidad: ");
			velocidad = Integer.parseInt(sc.nextLine());
			System.out.println("Resistencia: ");
			resistencia = Integer.parseInt(sc.nextLine());
			System.out.println("Fuerza: ");
			fuerza = Integer.parseInt(sc.nextLine());
			System.out.println("potencia: ");
			potencia = Integer.parseInt(sc.nextLine());
			System.out.println("Control: ");
			control = Integer.parseInt(sc.nextLine());
			System.out.println("Pase: ");
			pase = Integer.parseInt(sc.nextLine());
			System.out.println("Chute: ");
			chute = Integer.parseInt(sc.nextLine());
			System.out.println("Goles: ");
			goles = Integer.parseInt(sc.nextLine());
			System.out.println("Experiencia: ");
			experiencia = Integer.parseInt(sc.nextLine());

		} catch (NumberFormatException ex) {
			System.out.println("Formato incorrecto");
		}

		nacimiento = nacimiento.minusYears(anyos);

		Futbolista jugador = new Futbolista(altura, peso, nacimiento, nombre, dorsal, nomeq, sueldo, velocidad, resistencia, fuerza, potencia, control, pase, chute, goles, posicion, experiencia, categoria);
		equipos = new ArrayList<>();
		equipos = competicion.getEquipos();
		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				equipo.anyadirFutbolista(jugador);
			}
		}
	}

	/**
	 * Eliminar jugador.
	 */
	private static void eliminarJugador() {
		ArrayList <Futbolista> jugadores;
		equipos = competicion.getEquipos();
		Futbolista eliminado = new Futbolista();

		System.out.println("_-- Listado Jugadores --_");
		for (Equipo equipo : equipos) {
			jugadores = new ArrayList<>();
			jugadores = equipo.getaEquipo();
			System.out.println("Futbolistas del equipo: "+equipo.getNombre());
			for (Futbolista futbolista : jugadores) {
				System.out.println("\t"+futbolista.getNombre());
			}
		}

		System.out.println("¿De qué equipo es el jugador?");
		String nomeq = sc.nextLine();
		System.out.println("¿Qué jugador del equipo es el que quieres eliminar?");
		String nomJug = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				jugadores = new ArrayList<>();
				jugadores = equipo.getaEquipo();
				for (Futbolista futbolista : jugadores) {
					if (futbolista.getNombre().equals(nomJug)) {
						eliminado = futbolista;
					}
				}
			}
			equipo.eliminarFutbolista(eliminado);
			System.out.println("Jugador eliminado");
		}

	}

	/**
	 * Editar jugador.
	 */
	private static void editarJugador() {
		ArrayList <Futbolista> jugadores;
		equipos = competicion.getEquipos();
		int opcion = -1, subopcion = -1;
		boolean salida = false, volver = false;

		System.out.println("_-- Listado Jugadores --_");
		for (Equipo equipo : equipos) {
			jugadores = new ArrayList<>();
			jugadores = equipo.getaEquipo();
			System.out.println("Futbolistas del equipo: "+equipo.getNombre());
			for (Futbolista futbolista : jugadores) {
				System.out.println("\t"+futbolista.getNombre());
			}
		}

		System.out.println("¿De qué equipo es el jugador?");
		String nomeq = sc.nextLine();

		System.out.println("¿Qué jugador del equipo es el que quieres editar?");
		String nomJug = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				jugadores = new ArrayList<>();
				jugadores = equipo.getaEquipo();
				for (Futbolista futbolista : jugadores) {
					if (futbolista.getNombre().equals(nomJug)) {
						do {
							System.out.println("\t _-MODIFICAR-_");
							System.out.println("1. Datos personales.");
							System.out.println("2. Datos estadísticos.");
							System.out.println("3. Datos técnicos.");
							System.out.println("0. Salir.");
							volver = false;
							subopcion = -1;

							try {
								opcion = Integer.parseInt(sc.nextLine());
							} catch (NumberFormatException ex) {
								System.out.println("Formato incorrecto, seleccione un número de 0 a 3");
							}

							switch (opcion) {
							case 1:
								do {
									System.out.println("\t _- Datos personales -_");
									System.out.println("1. Nombre.");
									System.out.println("2. Edad.");
									System.out.println("3. Altura.");
									System.out.println("4. Peso.");
									System.out.println("5. Sueldo.");
									System.out.println("0. Volver.");

									try {
										subopcion = Integer.parseInt(sc.nextLine());
									} catch (NumberFormatException ex) {
										System.out.println("Formato incorrecto, seleccione un número de 0 a 5");
									}

									switch (subopcion) {
									case 1:
										System.out.println("Nuevo nombre: ");
										String nuevoNom = sc.nextLine();
										futbolista.setNombre(nuevoNom);
										System.out.println("Nombre editado a "+nuevoNom);
										break;

									case 2:
										try {
											System.out.println("¿Cuántos años tiene el jugador?");
											int edad = Integer.parseInt(sc.nextLine());
											LocalDate nacimiento = LocalDate.now();
											nacimiento = nacimiento.minusYears(edad);
											futbolista.setFechaNacimiento(nacimiento);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 3:
										try {
											System.out.println("¿Cuánto mide el jugador?");
											float altura = Float.parseFloat(sc.nextLine());
											futbolista.setAltura(altura);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número decimal válido");
										}
										break;

									case 4:
										try {
											System.out.println("¿Cuánto pesa el jugador?");
											float peso = Float.parseFloat(sc.nextLine());
											futbolista.setPeso(peso);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número decimal válido");
										}
										break;

									case 5:
										try {
											System.out.println("¿Cual es el sueldo del jugador?");
											float sueldo = Float.parseFloat(sc.nextLine());
											futbolista.setSueldo(sueldo);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número decimal válido");
										}
										break;

									case 0:
										volver=true;
										break;

									default:
										System.out.println("Inserte una opción correcta");
										break;
									}

								}while (!volver);
								break;

							case 2:

								do {
									System.out.println("\t _- Datos estadísticos -_");
									System.out.println("1. Velocidad.");
									System.out.println("2. Resistencia.");
									System.out.println("3. Fuerza.");
									System.out.println("4. Potencia.");
									System.out.println("5. Control.");
									System.out.println("6. Pase.");
									System.out.println("7. Chute.");
									System.out.println("0. Volver.");

									try {
										subopcion = Integer.parseInt(sc.nextLine());
									} catch (NumberFormatException ex) {
										System.out.println("Formato incorrecto, seleccione un número de 0 a 7");
									}

									switch (subopcion) {
									case 1:
										try {
											System.out.println("Velocidad: ");
											int velocidad = Integer.parseInt(sc.nextLine());
											futbolista.setVelocidad(velocidad);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 2:
										try {
											System.out.println("Resistencia: ");
											int resistencia = Integer.parseInt(sc.nextLine());
											futbolista.setResitencia(resistencia);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 3:
										try {
											System.out.println("Fuerza: ");
											int fuerza = Integer.parseInt(sc.nextLine());
											futbolista.setFuerza(fuerza);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 4:
										try {
											System.out.println("Potencia: ");
											int potencia = Integer.parseInt(sc.nextLine());
											futbolista.setPotencia(potencia);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 5:
										try {
											System.out.println("Control: ");
											int control = Integer.parseInt(sc.nextLine());
											futbolista.setControl(control);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número decimal válido");
										}
										break;
									case 6:
										try {
											System.out.println("Pase: ");
											int pase = Integer.parseInt(sc.nextLine());
											futbolista.setPase(pase);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 7:
										try {
											System.out.println("Chute: ");
											int chute = Integer.parseInt(sc.nextLine());
											futbolista.setChute(chute);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 0:
										volver=true;
										break;

									default:
										System.out.println("Inserte una opción correcta");
										break;
									}

								}while (!volver);
								break;

							case 3:

								do {
									System.out.println("\t _- Datos técnicos -_");
									System.out.println("1. Número dorsal.");
									System.out.println("2. Posición de juego.");
									System.out.println("3. Categoría.");
									System.out.println("4. Goles.");
									System.out.println("0. Volver.");

									try {
										subopcion = Integer.parseInt(sc.nextLine());
									} catch (NumberFormatException ex) {
										System.out.println("Formato incorrecto, seleccione un número de 0 a 4");
									}

									switch (subopcion) {
									case 1:
										try {
											System.out.println("Número dorsal: ");
											int dorsal = Integer.parseInt(sc.nextLine());
											futbolista.setDorsal(dorsal);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 2:
										System.out.println("Posición de juego: "+System.lineSeparator()+"[portero, defensa, centrocampista, delantero"+System.lineSeparator());
										String posicion = sc.nextLine();
										futbolista.setPosicion(posicion);
										break;

									case 3:
										System.out.println("Categoría del futbolista: "+System.lineSeparator()+"[baja, media, alta, estrella"+System.lineSeparator());
										String cat = sc.nextLine();
										futbolista.setCategoría(cat);
										break;

									case 4:
										try {
											System.out.println("Goles metidos en su carrera: ");
											int goles = Integer.parseInt(sc.nextLine());
											futbolista.setGoles(goles);
										} catch (NumberFormatException ex) {
											System.out.println("Formato incorrecto, seleccione un número válido");
										}
										break;

									case 0:
										volver=true;
										break;

									default:
										System.out.println("Inserte una opción correcta");
										break;
									}

								}while (!volver);
								break;

							case 0:
								salida=true;
								break;

							default:
								System.out.println("Inserte una opción correcta");
								break;
							}

						}while (!salida);
						salida = false;
						opcion = -1;
					}
				}
			}
		}
	}


	/**
	 * Mostrar jugador.
	 */
	private static void mostrarJugador() {
		ArrayList <Futbolista> jugadores;
		equipos = competicion.getEquipos();

		System.out.println("_-- Listado Jugadores --_");
		for (Equipo equipo : equipos) {
			jugadores = new ArrayList<>();
			jugadores = equipo.getaEquipo();
			System.out.println("Futbolistas del equipo: "+equipo.getNombre());
			for (Futbolista futbolista : jugadores) {
				System.out.println("\t"+futbolista.getNombre());
			}
		}

		System.out.println("¿De qué equipo es el jugador?");
		String nomeq = sc.nextLine();
		System.out.println("¿Qué jugador del equipo quieres ver?");
		String nomJug = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				jugadores = equipo.getaEquipo();
				for (Futbolista futbolista : jugadores) {
					if (futbolista.getNombre().equals(nomJug)) {
						System.out.println(futbolista.toString());
					}
				}
			}
		}
	}

	/**
	 * Mostrar jugadores.
	 */
	private static void mostrarJugadores() {
		ArrayList <Futbolista> jugadores;
		equipos = competicion.getEquipos();

		System.out.println("_-- Listado Jugadores --_");
		for (Equipo equipo : equipos) {
			jugadores = new ArrayList<>();
			jugadores = equipo.getaEquipo();
			System.out.println("Futbolistas del equipo: "+equipo.getNombre());
			for (Futbolista futbolista : jugadores) {
				System.out.println("\t"+futbolista.getNombre());
			}
		}
	}
	
	/**
	 * Crear entrenador.
	 */
	private static void crearEntrenador() {

		float altura = 0, peso = 0, sueldo = 0;
		int anyos = 0, experiencia = 0, liderazgo = 0, estrategia = 0, comunicacion = 0;
		LocalDate nacimiento = LocalDate.now();
		String nomeq = "", nombre = "";

		System.out.println("Equipos disponibles: ");
		competicion.mostrarEquipos();
		System.out.println("Nombre del equipo al que pertenece el entrenador: ");
		nomeq = sc.nextLine();
		System.out.println("Nombre del entrenador: ");
		nombre = sc.nextLine();

		try {
			System.out.println("Años del entrenador: ");
			anyos = Integer.parseInt(sc.nextLine());
			System.out.println("Altura: ");
			altura = Integer.parseInt(sc.nextLine());
			System.out.println("Peso: ");
			peso = Integer.parseInt(sc.nextLine());
			System.out.println("Experiencia: ");
			experiencia = Integer.parseInt(sc.nextLine());
			System.out.println("Liderazgo: ");
			liderazgo = Integer.parseInt(sc.nextLine());
			System.out.println("Estrategia: ");
			estrategia = Integer.parseInt(sc.nextLine());
			System.out.println("Comunicación: ");
			comunicacion = Integer.parseInt(sc.nextLine());

		} catch (NumberFormatException ex) {
			System.out.println("Formato incorrecto");
		}

		nacimiento = nacimiento.minusYears(anyos);

		Entrenador entrenador = new Entrenador(nombre, nacimiento, altura, peso, nomeq, sueldo, experiencia, liderazgo, estrategia, comunicacion);
		equipos = new ArrayList<>();
		equipos = competicion.getEquipos();
		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				equipo.cambiarEntrenador(entrenador);
			}
		}
	}
	
	/**
	 * Eliminar entrenador.
	 */
	private static void eliminarEntrenador() {

		System.out.println("_-- Listado entrenadores --_");
		for (Equipo equipo : equipos) {
			System.out.println("Entrenador del equipo: "+equipo.getNombre()+System.lineSeparator()+equipo.getEntrenador());
		}

		System.out.println("¿De qué equipo es el entrenador?");
		String nomeq = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				Entrenador entrenador = new Entrenador();
				equipo.cambiarEntrenador(entrenador);
			}
		}
	}
	
	/**
	 * Editar entrenador.
	 */
	private static void editarEntrenador() {
		int opcion = -1, subopcion = -1;
		boolean salida = false, volver = false;

		System.out.println("_-- Listado Entrenadores --_");
		for (Equipo equipo : equipos) {
			System.out.println("Entrenador del equipo: "+equipo.getNombre()+System.lineSeparator()+equipo.getEntrenador());
		}

		System.out.println("¿De qué equipo es el entrenador?");
		String nomeq = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				Entrenador entrenador = new Entrenador();
				entrenador = equipo.getEntrenador();
				do {
					System.out.println("\t _-MODIFICAR-_");
					System.out.println("1. Datos personales.");
					System.out.println("2. Datos estadísticos.");
					System.out.println("0. Salir.");
					volver = false;
					subopcion = -1;

					try {
						opcion = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException ex) {
						System.out.println("Formato incorrecto, seleccione un número de 0 a 2");
					}

					switch (opcion) {
					case 1:
						do {
							System.out.println("\t _- Datos personales -_");
							System.out.println("1. Nombre.");
							System.out.println("2. Edad.");
							System.out.println("3. Altura.");
							System.out.println("4. Peso.");
							System.out.println("5. Sueldo.");
							System.out.println("6. Equipo.");
							System.out.println("0. Volver.");

							try {
								subopcion = Integer.parseInt(sc.nextLine());
							} catch (NumberFormatException ex) {
								System.out.println("Formato incorrecto, seleccione un número de 0 a 5");
							}

							switch (subopcion) {
							case 1:
								System.out.println("Nuevo nombre: ");
								String nuevoNom = sc.nextLine();
								entrenador.setNombre(nuevoNom);
								System.out.println("Nombre editado a "+nuevoNom);
								break;

							case 2:
								try {
									System.out.println("¿Cuántos años tiene el entrenador?");
									int edad = Integer.parseInt(sc.nextLine());
									LocalDate nacimiento = LocalDate.now();
									nacimiento = nacimiento.minusYears(edad);
									entrenador.setFechaNacimiento(nacimiento);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número válido");
								}
								break;

							case 3:
								try {
									System.out.println("¿Cuánto mide el entrenador?");
									float altura = Float.parseFloat(sc.nextLine());
									entrenador.setAltura(altura);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número decimal válido");
								}
								break;

							case 4:
								try {
									System.out.println("¿Cuánto pesa el entrenador?");
									float peso = Float.parseFloat(sc.nextLine());
									entrenador.setPeso(peso);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número decimal válido");
								}
								break;

							case 5:
								try {
									System.out.println("¿Cual es el sueldo del entrenador?");
									float sueldo = Float.parseFloat(sc.nextLine());
									entrenador.setSueldo(sueldo);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número decimal válido");
								}
								break;
								
							case 6:
								
								System.out.println("Equipos disponibles:");
								
								for (Equipo equipo2 : equipos) {
									System.out.println(equipo2.getNombre());
								}
								
								System.out.println("Equipo que va a entrenar: ");
								String nomeqnew = sc.nextLine();
								
								for (Equipo equipo2 : equipos) {
									if (equipo2.getNombre().equals(nomeqnew)) {
										equipo2.cambiarEntrenador(entrenador);
									}
								}
								
								Entrenador reemplazo = new Entrenador();
								equipo.cambiarEntrenador(reemplazo);
								
								break;

							case 0:
								volver=true;
								break;

							default:
								System.out.println("Inserte una opción correcta");
								break;
							}

						}while (!volver);
						break;

					case 2:
						do {
							System.out.println("\t _- Datos estadísticos -_");
							System.out.println("1. Liderazgo.");
							System.out.println("2. Estratégia.");
							System.out.println("3. Comunición.");
							System.out.println("0. Volver.");

							try {
								subopcion = Integer.parseInt(sc.nextLine());
							} catch (NumberFormatException ex) {
								System.out.println("Formato incorrecto, seleccione un número de 0 a 3");
							}

							switch (subopcion) {
							case 1:
								try {
									System.out.println("Liderazgo: ");
									int liderazgo = Integer.parseInt(sc.nextLine());
									entrenador.setLiderazgo(liderazgo);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número válido");
								}
								break;

							case 2:
								try {
									System.out.println("Estratégia: ");
									int estrategia = Integer.parseInt(sc.nextLine());
									entrenador.setEstrategia(estrategia);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número válido");
								}
								break;

							case 3:
								try {
									System.out.println("Comunición: ");
									int comunicacion = Integer.parseInt(sc.nextLine());
									entrenador.setComunicacion(comunicacion);
								} catch (NumberFormatException ex) {
									System.out.println("Formato incorrecto, seleccione un número válido");
								}
								break;

							case 0:
								volver=true;
								break;

							default:
								System.out.println("Inserte una opción correcta");
								break;
							}

						}while (!volver);
						break;

					case 0:
						salida=true;
						break;

					default:
						System.out.println("Inserte una opción correcta");
						break;
					}

				}while (!salida);
				salida = false;
				opcion = -1;
			}
		}
	}

	/**
	 * Mostrar entrenadores.
	 */
	private static void mostrarEntrenadores() {

		System.out.println("_-- Listado Entrenadores --_");
		for (Equipo equipo : equipos) {
			System.out.println("Equipo:"+ equipo.getNombre()+System.lineSeparator()+"\t Entrenador: "+equipo.getEntrenador());
		}
	}

	/**
	 * Crear equipo.
	 */
	private static void crearEquipo() {
		LocalDate fundacion = LocalDate.now();
		LocalDate nacimiento = LocalDate.now();
		Futbolista futbolista;
		ArrayList <Futbolista> aEquipo = new ArrayList<>();
		ArrayList<String> nombres = new ArrayList<String>(Arrays.asList( "Alejandro", "Alex", "Alfonso", "Carlo", "Carlos", "David", "Domingo", "Fermín", "Ismael", "Israel", "Jaimito", "James", "Javier", "Juan", "JuanCarlos", "Pedro", "Pep", "Pepe", "Santos", "Sergio", "Tiverius", "Tomás", "Vicent", "Vicente" ));
		int num = -1, indice = -1, edad = -1;
		String [] cat = {"baja", "media", "alta", "estrella"};
		
		System.out.println("Nombre del equipo: ");
		String nomEq = sc.nextLine();
		System.out.println("Presidente del equipo: ");
		String presidente = sc.nextLine();
		try {
			System.out.println("¿Cuántos años tiene el equipo?");
			int anyos = Integer.parseInt(sc.nextLine());
			fundacion = fundacion.minusYears(anyos);
		} catch (NumberFormatException ex) {
			System.out.println("Formato incorrecto, seleccione un número válido");
		}
		
		Equipo equipo = new Equipo(nomEq, fundacion, presidente);
		
		for (int j = 0; j < 22; j++) {
			edad =(int) Math.round(Math.random()*(35-18)+18);
			num = (int) Math.round(Math.random()*23);
			indice = (int) Math.round(Math.random()*3);
			nacimiento = nacimiento.minusYears(num);
			if (j<4) {
				aEquipo.add(futbolista = new Futbolista((float) (Math.random()*(2.00-1.60)+1.60), (float) (Math.random()*(100-60)+60),
						nacimiento, nombres.get(num), j, equipo.getNombre(), (float) (Math.random()*(5000-1500)+1500), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1),
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), "delantero", (int) Math.round(Math.random()*100+1), 
						cat[indice]));
				return ;
			} else if (j>3 && j<12) {
				aEquipo.add(futbolista = new Futbolista((float) (Math.random()*(2.00-1.60)+1.60), (float) (Math.random()*(100-60)+60),
						nacimiento, nombres.get(num), j, equipo.getNombre(), (float) (Math.random()*(5000-1500)+1500), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1),
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), "centrocampista", (int) Math.round(Math.random()*100+1), 
						cat[indice]));
			} else if (j>11 && j<20) {
				aEquipo.add(futbolista = new Futbolista((float) (Math.random()*(2.00-1.60)+1.60), (float) (Math.random()*(100-60)+60),
						nacimiento, nombres.get(num), j, equipo.getNombre(), (float) (Math.random()*(5000-1500)+1500), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), 
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1),
						(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), "defensa", (int) Math.round(Math.random()*100+1), 
						cat[indice]));
			} else if (j>19 && j<22) {aEquipo.add(futbolista = new Futbolista((float) (Math.random()*(2.00-1.60)+1.60), (float) (Math.random()*(100-60)+60),
					nacimiento, nombres.get(num), j, equipo.getNombre(), (float) (Math.random()*(5000-1500)+1500), 
					(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), 
					(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1),
					(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1), "portero", (int) Math.round(Math.random()*100+1), 
					cat[indice]));
			}
		}
		
		equipo.setaEquipo(aEquipo);
		edad =(int) Math.round(Math.random()*(55-18)+18);
		num = (int) Math.round(Math.random()*23);
		indice = (int) Math.round(Math.random()*3);
		nacimiento = nacimiento.minusYears(num);
		Entrenador entrenador = new Entrenador(nombres.get(num), nacimiento, (float) (Math.random()*(2.00-1.60)+1.60), (float) (Math.random()*(100-60)+60),
				equipo.getNombre(), (float) (Math.random()*(5000-1500)+1500), (int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1),
				(int) Math.round(Math.random()*100+1), (int) Math.round(Math.random()*100+1));
		equipo.setEntrenador(entrenador);
		
		equipos = competicion.getEquipos();
		equipos.add(equipo);
		competicion.setEquipos(equipos);
	}

	/**
	 * Eliminar equipo.
	 */
	private static void eliminarEquipo() {
		
		equipos = competicion.getEquipos();
		int indice = -1;
		
		System.out.println("_-- Listado equipos --_");
		for (Equipo equipo : equipos) {
			System.out.println("\tEquipo:"+ equipo.getNombre());
		}
		
		System.out.println("¿Qué equipo quieres eliminar");
		String nomeq = sc.nextLine();
		
		for (Equipo equipo : equipos) {
			indice++;
			if (equipo.getNombre().equals(nomeq)) {
				equipos.remove(indice);
			}
		}
	}

	/**
	 * Editar equipo.
	 */
	private static void editarEquipo() {

		equipos = competicion.getEquipos();
		int opcion = -1, anyo = -1;
		boolean salida = false;
		LocalDate fundacion = LocalDate.now();

		System.out.println("_-- Listado equipos --_");
		for (Equipo equipo : equipos) {
			System.out.println("\tEquipo:"+ equipo.getNombre());
		}

		System.out.println("¿Qué equipo quieres editar");
		String nomeq = sc.nextLine();

		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				do {
					System.out.println("\t _-MODIFICAR-_");
					System.out.println("1. Nombre equipo.");
					System.out.println("2. Nombre presidente.");
					System.out.println("3. Localidad.");
					System.out.println("4. Año fundación.");
					System.out.println("5. Patrimonio.");
					System.out.println("6. Equipacion.");
					System.out.println("0. Salir.");

					try {
						opcion = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException ex) {
						System.out.println("Formato incorrecto, seleccione un número de 0 a 6");
					}

					switch (opcion) {
					case 1:
						System.out.println("Nuevo nombre del equipo: ");
						String nomeqnew = sc.nextLine();
						
						equipo.setNombre(nomeqnew);
						break;

					case 2:
						System.out.println("Nuevo presidente del equipo: ");
						String presidente = sc.nextLine();
						
						equipo.setNombrePresidente(presidente);
						break;

					case 3:
						System.out.println("Nueva localidad del equipo: ");
						String localidad = sc.nextLine();
						
						equipo.setLocalidad(localidad);
						break;
						
					case 4:
						System.out.println("Años desde la fundación del equipo: ");
						try {
							anyo = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException ex) {
							System.out.println("Formato incorrecto, seleccione un número válido.");
						}

						fundacion = fundacion.minusYears(anyo);
						
						equipo.setFechFundacion(fundacion);
						break;

					case 5:
						try {
							System.out.println("¿Cual es el patrimonio del equipo?");
							int patrimonio = Integer.parseInt(sc.nextLine());
							
							equipo.setPatrimonio(patrimonio);
						} catch (NumberFormatException ex) {
							System.out.println("Formato incorrecto, seleccione un número válido.");
						}
						break;

					case 6:
						System.out.println("¿Que color tiene la equipación principal?");
						String color1 = sc.nextLine();
						
						System.out.println("¿Que color tiene la equipación secundaria?");
						String color2 = sc.nextLine();
						
						String [] equipacion = {color1, color2};
						
						equipo.setEquipacion(equipacion);

						break;

					case 0:
						salida=true;
						break;

					default:
						System.out.println("Inserte una opción correcta");
						break;
					}

				}while (!salida);
				salida = false;
			}
		}
	}
	
	/**
	 * Mostrar equipo.
	 */
	private static void mostrarEquipo() {
		equipos = competicion.getEquipos();

		System.out.println("_-- Listado equipos --_");
		for (Equipo equipo : equipos) {
			System.out.println("\tEquipo:"+ equipo.getNombre());
		}

		System.out.println("¿Qué equipo quieres ver?");
		String nomeq = sc.nextLine();
		
		for (Equipo equipo : equipos) {
			if (equipo.getNombre().equals(nomeq)) {
				System.out.println(equipo.toString());
			}
		}
	}
	
	/**
	 * Mostrar equipos.
	 */
	private static void mostrarEquipos() {
		equipos = competicion.getEquipos();

		System.out.println("_-- Listado equipos registrados --_");
		for (Equipo equipo : equipos) {
			System.out.println("\tEquipo:"+ equipo.getNombre());
		}
	}

	/**
	 * Guardar equipos.
	 */
	private static void guardarEquipos() {

		equipos = competicion.getEquipos();
		String fichero="equiposCompeticion.txt";
		ObjectOutputStream salida = null;

		try {

			salida = new ObjectOutputStream( new FileOutputStream(fichero));
			salida.writeObject(equipos);
			salida.flush();
			salida.close();
			System.out.println("Equipos registrados correctamente...");

		} catch (IOException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Cargar equipos.
	 */
	private static void cargarEquipos() {
		try {
			
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("equiposCompeticion.txt"));
			equipos = (ArrayList<Equipo>) entrada.readObject();
			System.out.println("Equipos cargados correctamente...");
			
			competicion.setEquipos(equipos);

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		}
		
	}
	
	
	/**
	 * The Main1 method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) { // punto de entrada al programa

		menu();

	}

}
