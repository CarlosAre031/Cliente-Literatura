package com.challengeliteratura.challengeliteratura.entity;

import com.challengeliteratura.challengeliteratura.model.Autor;
import com.challengeliteratura.challengeliteratura.util.CadenasUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Autor")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToOne
    @JoinTable(
            name = "Libro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private LibroEntity libros;

    public AutorEntity() {
    }

    public AutorEntity(Autor autor) {
        this.nombre = CadenasUtil.limitarLongitud(autor.name(), 200);
        this.fechaNacimiento = (autor.birthYear() == null) ? 1980 : autor.birthYear();
        this.fechaFallecimiento = (autor.deathYear() == null) ? 3022 : autor.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public LibroEntity getLibros() {
        return libros;
    }

    public void setLibros(LibroEntity libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "AutorEntity [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
                + ", fechaFallecimiento=" + fechaFallecimiento + ", libros=" + libros + "]";
    }
}
