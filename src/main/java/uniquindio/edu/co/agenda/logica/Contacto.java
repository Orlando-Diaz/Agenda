package uniquindio.edu.co.agenda.logica;

import java.util.ArrayList;
import java.util.List;

public class Contacto {

    private String nombre;
    private String alias;
    private String direccion;
    private String telefono;
    private String email;

    public static List<Contacto> contactos = new ArrayList<>();

    //CONSTRUCTOR
    public Contacto(String nombre, String alias, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.alias = alias;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;

        // Añadir el nuevo contacto al ArrayList
        contactos.add(this);
    }
    //CONSTRUCTOR VACIO
    public Contacto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para obtener la lista de todos los contactos
    public static List<Contacto> getContactos() {
        return contactos;
    }

    // Método para eliminar un contacto
    public static void eliminarContacto(Contacto contacto) {
        contactos.remove(contacto);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", alias='" + alias + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
