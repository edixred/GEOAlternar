    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author mateo
 */
public class Capamap {
    private String nombreCapa;
    private String valorCapa;

    public String getNombreCapa() {
        return nombreCapa;
    }

    public void setNombreCapa(String nombreCapa) {
        this.nombreCapa = nombreCapa;
    }

    public String getValorCapa() {
        return valorCapa;
    }

    public void setValorCapa(String valorCapa) {
        this.valorCapa = valorCapa;
    }

    public Capamap(String nombreCapa, String valorCapa) {
        this.nombreCapa = nombreCapa;
        this.valorCapa = valorCapa;
    }
}
