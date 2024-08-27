package uniquindio.edu.co.agenda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uniquindio.edu.co.agenda.logica.Categoria;
import uniquindio.edu.co.agenda.logica.Contacto;
import uniquindio.edu.co.agenda.logica.Grupo;
import uniquindio.edu.co.agenda.logica.Reunion;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    Contacto con = new Contacto();
    Grupo g = new Grupo();
    List<Grupo> listaGrupos = new ArrayList<>();
    List<Contacto> asistentes = new ArrayList<>();

    @FXML
    private Button boton_CrearGrupo;

    @FXML
    private Button boton_agregarContacto;

    @FXML
    private Button boton_agregarContacto_Reunion;

    @FXML
    private Button boton_crearContacto;

    @FXML
    private Button boton_crearReunion;

    @FXML
    private Button boton_eliminarContacto;

    @FXML
    private Button boton_modificarContacto;

    @FXML
    private ComboBox<Categoria> combo_categoria;

    @FXML
    private TextField txt_alias;

    @FXML
    private TextField txt_descripcion_reunion;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_eliminarContacto;

    @FXML
    private TextField txt_email;

    @FXML
    private DatePicker txt_fecha;

    @FXML
    private TextField txt_hora;

    @FXML
    private TextArea txt_listaContactos;

    @FXML
    private TextArea txt_listaContactos_Reunion;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_nombreContacto_agregar;

    @FXML
    private TextField txt_nombreGrupo;

    @FXML
    private TextField txt_nombreGrupo_agregar;

    @FXML
    private TextField txt_nombreModificar;

    @FXML
    private TextField txt_nombre_AsignarReunion;

    @FXML
    private TextField txt_queDeseaModificar;

    @FXML
    private TextField txt_telefono;

    @FXML
    private TextField txt_valorAModificar;

    @FXML
    void accion_agregarAReunion(ActionEvent event) {

        String nombre = txt_nombre_AsignarReunion.getText();

        Contacto contac = null;
        for (Contacto c: Contacto.getContactos()){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                contac = c;
                break;
            }
        }

        asistentes.add(contac);

        String contactosTexto = "";
        for (Contacto c : asistentes) {
            contactosTexto += c.toString() + "\n";
        }

        // Mostrar todos los contactos del grupo en el TextArea
        txt_listaContactos_Reunion.setText(contactosTexto);

    }

    @FXML
    void accion_agregarContacto(ActionEvent event) {


        String nombre = txt_nombreContacto_agregar.getText();
        String nombreGrupo = txt_nombreGrupo_agregar.getText();

        Contacto contactoBuscar = null;
        for(Contacto c: Contacto.getContactos()){
            if(c.getNombre().equals(nombre)){
                contactoBuscar = c;
                break;
            }
        }

        Grupo grupoBuscar = null;
        for(Grupo g: listaGrupos){
            if(g.getNombre().equals(nombreGrupo)){
                grupoBuscar = g;
                break;
            }
        }

        // Si se encuentra tanto el contacto como el grupo
        if (contactoBuscar != null && grupoBuscar != null) {
            // Obtener la lista existente de contactos en el grupo
            List<Contacto> li = grupoBuscar.getContactos();

            if (li.size() < 5) {

                // Agregar el nuevo contacto a la lista
                li.add(contactoBuscar);

                // Actualizar la lista de contactos en el grupo
                grupoBuscar.setContactos(li);

                // Crear un texto con todos los contactos
                String contactosTexto = "";
                for (Contacto c : li) {
                    contactosTexto += c.toString() + "\n";
                }

                // Mostrar todos los contactos del grupo en el TextArea
                txt_listaContactos.setText(contactosTexto);

                JOptionPane.showMessageDialog(null, "Contacto agregado al grupo con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Contacto o Grupo no encontrado");
            }

            // Limpiar los campos de texto después de agregar el contacto
            txt_nombreContacto_agregar.clear();
            txt_nombreGrupo_agregar.clear();
            }


    }

    //BOTON CREAR CONTACTO
    @FXML
    void accion_crearContacto(ActionEvent event) {

        String nombre = txt_nombre.getText();
        String alias = txt_alias.getText();
        String direccion = txt_direccion.getText();
        String telefono = txt_telefono.getText();
        String email = txt_email.getText();


        boolean contactoExiste = false;
        for (Contacto c : Contacto.getContactos()) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getTelefono().equalsIgnoreCase(telefono)) {
                contactoExiste = true;
                break;
            }
        }

        if (contactoExiste) {
            JOptionPane.showMessageDialog(null, "Este contacto ya existe");
        } else {
            Contacto nuevoContacto = new Contacto(nombre, alias, direccion, telefono, email);
            Contacto.getContactos().add(nuevoContacto);
            JOptionPane.showMessageDialog(null, "creado con exito");
        }

        txt_nombre.clear();
        txt_alias.clear();
        txt_direccion.clear();
        txt_telefono.clear();
        txt_email.clear();

    }

    @FXML
    void accion_crearGrupo(ActionEvent event) {


        String nombre = txt_nombreGrupo.getText();
        Categoria cate = combo_categoria.getSelectionModel().getSelectedItem();
        List<Contacto> listaGrupo = new ArrayList<>();


        Grupo grupo = new Grupo(nombre , cate , listaGrupo);
        listaGrupos.add(grupo);
        JOptionPane.showMessageDialog(null, "Grupo creado");

        txt_listaContactos.clear();

    }

    @FXML
    void accion_crearReunion(ActionEvent event) {

        String descripcion = txt_descripcion_reunion.getText();
        LocalDate fecha = txt_fecha.getValue();
        String hora = txt_hora.getText();


        Reunion reunion = new Reunion(descripcion, fecha, hora, asistentes);
        JOptionPane.showMessageDialog(null, "Reunión creada con éxito");

        txt_descripcion_reunion.clear();
        txt_fecha.commitValue();
        txt_hora.clear();

        txt_listaContactos_Reunion.clear();

    }

    @FXML
    void accion_eliminarContacto(ActionEvent event) {

        String nombre = txt_eliminarContacto.getText();

        Contacto contactoEliminar = null;
        for (Contacto c: Contacto.getContactos()){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                contactoEliminar = c;
                break;
            }
        }

        if(contactoEliminar != null){
            con.eliminarContacto(contactoEliminar);
            JOptionPane.showMessageDialog(null, "Contacto eliminado");
            txt_eliminarContacto.clear();
        }else{
            JOptionPane.showMessageDialog(null, "No se encontro el contacto");

        }

        txt_eliminarContacto.clear();
    }

    @FXML
    void accion_modificarContacto(ActionEvent event) {

        String nombre = txt_nombreModificar.getText();
        String cosaAmodificar = txt_queDeseaModificar.getText();
        String campoActualizado = txt_valorAModificar.getText();

        Contacto contactoAModificar = null;
        for(Contacto c : Contacto.getContactos()){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                contactoAModificar = c;
            }
        }

        if (contactoAModificar != null) {
            if (cosaAmodificar.equalsIgnoreCase("nombre")) {
                contactoAModificar.setNombre(campoActualizado);
                JOptionPane.showMessageDialog(null, "Contacto actualizado");
            } else if (cosaAmodificar.equalsIgnoreCase("alias")) {
                contactoAModificar.setAlias(campoActualizado);
                JOptionPane.showMessageDialog(null, "Contacto actualizado");
            } else if (cosaAmodificar.equalsIgnoreCase("direccion")) {
                contactoAModificar.setDireccion(campoActualizado);
                JOptionPane.showMessageDialog(null, "Contacto actualizado");
            } else if (cosaAmodificar.equalsIgnoreCase("telefono")) {
                contactoAModificar.setTelefono(campoActualizado);
                JOptionPane.showMessageDialog(null, "Contacto actualizado");
            } else {
                contactoAModificar.setEmail(campoActualizado);
                JOptionPane.showMessageDialog(null, "Contacto actualizado");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Contacto no encontrado");
        }

        txt_nombreModificar.clear();
        txt_queDeseaModificar.clear();
        txt_valorAModificar.clear();
    }

    @FXML
    void accion_mostrarCategoria(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combo_categoria.getItems().addAll(Categoria.values());
    }
}

