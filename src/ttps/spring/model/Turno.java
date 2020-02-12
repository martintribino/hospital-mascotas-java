package ttps.spring.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ttps.spring.helpers.GenericHelper;
import ttps.spring.helpers.LocalDateToStringConverter;
import ttps.spring.helpers.StringToLocalDateConverter;
import ttps.spring.helpers.LocalTimeToStringConverter;
import ttps.spring.helpers.StringToLocalTimeConverter;

@Entity
@Table( name="turno",
		indexes={@Index(name="turno_fk",columnList="fecha,inicio,fin",unique=true)}
)
public class Turno implements Serializable {

	/**
	 * Clase Turno
	 */
	private static final long serialVersionUID = -1377243596982081385L;
	
	//constantes
	public static final int TURNO = 30;
	public static final LocalTime HORARIO_INICIO = LocalTime.of(9, 00);
	public static final LocalTime HORARIO_FIN = LocalTime.of(18, 00);
	public static enum Estados {
		CANCELADO, //cancelado
		CONCURRIO, //para consltar historial
		DISPONIBLE, //disponible y aun no transcurrio la fecha
		INACTIVO, //ya transcurrio la fecha
		NOCONCURRIO, //para consltar historial
		NODISPONIBLE, //no disponible porque ya transcurrio la fecha
		RESERVADO, //reservado y aun no transcurrio la fecha
	}
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
    @Column(name = "fecha", nullable = false)
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATE_FORMAT)
	private LocalDate fecha;
    @Column(name = "inicio", nullable = false)
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALTIME_FORMAT)
	private LocalTime inicio;
    @Column(name = "fin", nullable = false)
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALTIME_FORMAT)
	private LocalTime fin;
	private Turno.Estados estado;
	@OneToOne(mappedBy="turno")
	private Evento evento;

	public Turno() {
		this.setFecha(LocalDate.now());
		this.setInicio(LocalTime.now());
		this.setFin(this.getInicio().plusMinutes(Turno.TURNO).minusSeconds(1));
		this.setEstado(Turno.Estados.DISPONIBLE);
		this.setEvento(null);
	}

	public Turno(
			LocalDate fechaP,
			LocalTime inicioP,
			LocalTime finP,
			Turno.Estados estado
		) {
		this.setFecha(fechaP);
		this.setInicio(inicioP);
		this.setFin(finP);
		this.setEstado(estado);
		this.setEvento(null);
	}

	public Turno(
			LocalDate fechaP,
			LocalTime inicioP,
			LocalTime finP,
			Turno.Estados estado,
			Evento evento
		) {
		this.setFecha(fechaP);
		this.setInicio(inicioP);
		this.setFin(finP);
		this.setEstado(estado);
		this.setEvento(evento);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}

	public LocalTime getFin() {
		return fin;
	}

	public void setFin(LocalTime fin) {
		this.fin = fin;
	}

	public Turno.Estados getEstado() {
		return estado;
	}

	public void setEstado(Turno.Estados estado) {
		this.estado = estado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Boolean isOverlapped(Turno t) {
		return (this.fecha.isEqual(t.fecha)) &&
				(!this.inicio.isAfter(t.fin)) &&
				(!t.inicio.isAfter(this.fin));
	}

}
