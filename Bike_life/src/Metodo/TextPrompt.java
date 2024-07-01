package Metodo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * La clase TextPrompt muestra un texto de ayuda sobre un componente de texto cuando el Documento del campo está vacío.
 * La propiedad Show determina la visibilidad del prompt.
 *
 * La fuente y el color de primer plano del prompt por defecto son los mismos que los del componente de texto padre.
 * Después de la construcción de la clase, se pueden cambiar estas propiedades.
 */
public class TextPrompt extends JLabel implements FocusListener, DocumentListener {
    private static final long serialVersionUID = 1L;

    public TextPrompt(String ingresa_tu_contraseña_de_usuario, TextPrompt password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum Show {
        ALWAYS, FOCUS_GAINED, FOCUS_LOST;
    }

    private JTextComponent component;
    private Document document;

    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    /**
     * Constructor de TextPrompt.
     *
     * @param text      Texto que se mostrará como prompt.
     * @param component Componente de texto sobre el cual se mostrará el prompt.
     */
    public TextPrompt(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    /**
     * Constructor de TextPrompt con opción para especificar cuándo mostrar el prompt.
     *
     * @param text      Texto que se mostrará como prompt.
     * @param component Componente de texto sobre el cual se mostrará el prompt.
     * @param show      Enum Show que especifica cuándo mostrar el prompt.
     */
    public TextPrompt(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());
        setForeground(Color.gray); // Color del texto del prompt (gris por defecto)
        setHorizontalAlignment(JLabel.LEADING); // Alineación del texto a la izquierda

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    /**
     * Cambia el valor alfa (transparencia) del color de primer plano actual.
     *
     * @param alpha Valor alfa en el rango de 0 - 1.0.
     */
    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }

    /**
     * Cambia el valor alfa (transparencia) del color de primer plano actual.
     *
     * @param alpha Valor alfa en el rango de 0 - 255.
     */
    public void changeAlpha(int alpha) {
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground(withAlpha);
    }

    /**
     * Cambia el estilo de la fuente actual.
     *
     * @param style Valor que representa el nuevo estilo de la fuente.
     */
    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));
    }

    /**
     * Obtiene la propiedad Show.
     *
     * @return La propiedad Show.
     */
    public Show getShow() {
        return show;
    }

    /**
     * Establece la propiedad Show para controlar cuándo se muestra el prompt.
     *
     * @param show Enum Show válido para especificar cuándo mostrar el prompt.
     */
    public void setShow(Show show) {
        this.show = show;
    }

    /**
     * Obtiene la propiedad showPromptOnce.
     *
     * @return Valor de la propiedad showPromptOnce.
     */
    public boolean getShowPromptOnce() {
        return showPromptOnce;
    }

    /**
     * Establece la propiedad showPromptOnce para mostrar el prompt solo una vez o repetidamente.
     *
     * @param showPromptOnce true para mostrar el prompt solo una vez, false para mostrarlo repetidamente.
     */
    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    /**
     * Verifica si el prompt debe estar visible o no. La visibilidad cambia con las actualizaciones del Document y los cambios de foco.
     */
    private void checkForPrompt() {
        // Si se ha ingresado texto, oculta el prompt
        if (document.getLength() > 0) {
            setVisible(false);
            return;
        }

        // Si showPromptOnce es true y se ha perdido el foco al menos una vez, oculta el prompt
        if (showPromptOnce && focusLost > 0) {
            setVisible(false);
            return;
        }

        // Verifica la propiedad Show y el foco del componente para determinar si mostrar el prompt
        if (component.hasFocus()) {
            if (show == Show.ALWAYS || show == Show.FOCUS_GAINED)
                setVisible(true);
            else
                setVisible(false);
        } else {
            if (show == Show.ALWAYS || show == Show.FOCUS_LOST)
                setVisible(true);
            else
                setVisible(false);
        }
    }

    // Implementación de FocusListener

    /**
     * Método que se llama cuando el componente gana el foco.
     *
     * @param e Evento de foco.
     */
    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    /**
     * Método que se llama cuando el componente pierde el foco.
     *
     * @param e Evento de foco.
     */
    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    // Implementación de DocumentListener

    /**
     * Método que se llama cuando se inserta texto en el Documento del componente.
     *
     * @param e Evento de documento.
     */
    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    /**
     * Método que se llama cuando se elimina texto del Documento del componente.
     *
     * @param e Evento de documento.
     */
    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    /**
     * Método que se llama cuando cambia el contenido del Documento del componente.
     *
     * @param e Evento de documento.
     */
    public void changedUpdate(DocumentEvent e) {
    }
}