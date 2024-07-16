package com.challengeliteratura.challengeliteratura.client;

import java.util.List;
import java.util.Scanner;

import com.challengeliteratura.challengeliteratura.entity.AutorEntity;
import com.challengeliteratura.challengeliteratura.entity.LibroEntity;
import com.challengeliteratura.challengeliteratura.mapper.ConvierteDatos;
import com.challengeliteratura.challengeliteratura.model.Respuesta;
import com.challengeliteratura.challengeliteratura.repository.AutorRepository;
import com.challengeliteratura.challengeliteratura.repository.LibroRepository;
import com.challengeliteratura.challengeliteratura.service.ConsumoAPI;

public class ClienteLiteratura {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public ClienteLiteratura(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    Elija la opción a través de su número:
                    1.- Buscar libro por título
                    2.- Lista libros registrados
                    3.- Lista autores registrados
                    4.- Lista autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> buscarLibroWeb();
                case 2 -> buscarLibros();
                case 3 -> buscarAutores();
                case 4 -> buscarAutoresVivos();
                case 5 -> buscarPorIdiomas();
                case 0 -> System.out.println("Adiós, vuelva pronto...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibros() {
        List<LibroEntity> libros = libroRepositorio.findAll();

        if (!libros.isEmpty()) {
            System.out.println("\n\n---------- LIBROS -------\n");
            for (LibroEntity libro : libros) {
                System.out.println(" Título: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarAutores() {
        List<AutorEntity> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            System.out.println("\n\n---------- AUTORES -------\n");
            for (AutorEntity autor : autores) {
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarAutoresVivos() {
        System.out.println("Escriba el año en el que vivió: ");
        int anio = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        List<AutorEntity> autores = autorRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            System.out.println("\n\n---------- AUTORES VIVOS -------\n");
            for (AutorEntity autor : autores) {
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarPorIdiomas() {
        String menu = """
                Seleccione un Idioma:
                1.- Español
                2.- Inglés
                """;
        System.out.println(menu);
        int idioma = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        String seleccion = switch (idioma) {
            case 1 -> "es";
            case 2 -> "en";
            default -> "";
        };

        List<LibroEntity> libros = libroRepositorio.findForLanguaje(seleccion);

        if (!libros.isEmpty()) {
            System.out.println("\n\n---------- LIBROS POR IDIOMA -------\n");
            for (LibroEntity libro : libros) {
                System.out.println(" Título: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarLibroWeb() {
        Respuesta datos = getDatosSerie();

        if (!datos.results().isEmpty()) {
            LibroEntity libro = new LibroEntity(datos.results().get(0));
            libroRepositorio.save(libro);
        }

        System.out.println("Datos: ");
        System.out.println(datos);
    }

    private Respuesta getDatosSerie() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        String titulo = teclado.nextLine().replace(" ", "%20");
        String url = URL_BASE + titulo;
        System.out.println("Título: " + titulo);
        System.out.println(url);
        String json = consumoApi.obtenerDatos(url);
        System.out.println(json);
        return conversor.obtenerDatos(json, Respuesta.class);
    }
}
