package co.com.nisum.usuarios.domain.mensajes;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MensajePersonalizado {

    public static String obtenerMensaje(String identificadorMensaje){
        ResourceBundle bundle = ResourceBundle.getBundle("message", Locale.getDefault());
        return bundle.getString(identificadorMensaje);
    }

    public static String obtenerMensaje(String identificadorMensaje, String parametro){
        ResourceBundle bundle = ResourceBundle.getBundle("mensajes", Locale.getDefault());
        return MessageFormat.format(bundle.getString(identificadorMensaje), parametro);
    }
}
