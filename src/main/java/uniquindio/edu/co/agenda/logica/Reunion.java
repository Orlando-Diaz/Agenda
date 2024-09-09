package uniquindio.edu.co.agenda.logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reunion {

    private String descripcion;
    private LocalDate fecha;
    private String hora;
    private List<Contacto> asistentes;

    public Reunion(String descripcion, LocalDate fecha, String hora, List<Contacto> asistentes) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
    }

    public Reunion() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Contacto> getAsistentes() {
        return asistentes;
    }

    public void agregarAsistente(Contacto contacto) {
        this.asistentes.add(contacto);
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", asistentes=" + asistentes +
                '}';
    }

}
