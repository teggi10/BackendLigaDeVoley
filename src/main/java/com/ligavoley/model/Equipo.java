package com.ligavoley.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name="equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEquipo;
	
	@Column(name="nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name="localidad", length = 50, nullable = false)
	private String localidad;
	
	@Column(name="sexo", length = 50, nullable = false)
	private String sexo;
	
	@Column(name="puntos")
	private Integer puntos;
	
	@Column(name="localidad", length = 50, nullable = false)
	private String nombreClave;
	
	
	
	@OneToMany(mappedBy="equipo", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Jugador> jugadores = new ArrayList<>();

	public Equipo() {
		super();
	}

	public Equipo(Integer idEquipo, String nombre, String localidad,String sexo, Integer puntos,String nombreClave, List<Jugador> jugadores) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.localidad = localidad;
		this.puntos = puntos;
		this.jugadores = jugadores;
		this.nombreClave = nombreClave;
		this.sexo = sexo;
	}

	public String getNombreClave() {
		return nombreClave;
	}

	public void setNombreClave(String nombreClave) {
		this.nombreClave = nombreClave;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
	public void eliminarJugador(Jugador jugador) {
		this.jugadores.remove(jugador);
		jugador.setEquipo(null);
	}

	@JsonManagedReference
	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
	//	this.jugadores = jugadores;
	this.jugadores.addAll(jugadores);
	for(Jugador jugador: jugadores) {
		jugador.setEquipo(this);
	}
	}
}
