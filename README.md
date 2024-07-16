#Challenge de Literatura: Aplicación de Consola Spring Boot
Este proyecto es una aplicación de consola desarrollada en Spring Boot que interactúa con una API externa de libros y una base de datos local. Permite realizar búsquedas de libros, listar autores, filtrar por idioma y obtener información sobre autores vivos en un año específico.

#Desarrollado por: Jhan Carlos Arévalo Pérez



Funcionalidades 🚀
Buscar libros por título:

Consulta una API externa para obtener información sobre libros a partir de su título.
Almacena los datos de los libros encontrados en la base de datos local.
Listar libros registrados:

Muestra todos los libros almacenados en la base de datos, incluyendo detalles como título, autor e idioma.
Listar autores registrados:

Presenta todos los autores almacenados en la base de datos, ordenados por apellido y luego por nombre.
Listar autores vivos en un año específico:

Permite al usuario ingresar un año y muestra los autores que estaban vivos en ese año.
Filtrar libros por idioma:

Permite al usuario filtrar los libros por idioma (ES, EN) y muestra los resultados.
Tecnologías Utilizadas 🛠️
Spring Boot: Framework para el desarrollo de aplicaciones Java empresariales.
Spring Data JPA: Simplifica el acceso a datos en bases de datos relacionales.
Maven: Herramienta de gestión de dependencias y construcción de proyectos.
MySQL/PostgreSQL: (o tu base de datos de elección) para el almacenamiento de datos.
RestTemplate/WebClient: (o tu librería preferida) para consumir la API externa.
Lombok: (opcional) Para reducir la cantidad de código repetitivo (getters, setters, etc.).
