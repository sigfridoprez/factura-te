/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sig.derbyclient.dto;

/**
 *
 * @author sigfrido.perez
 */
public class FacturaDto {

    private int idFactura;
    private String folioFactura;
    private int numFactura;

    public FacturaDto(int idFactura, String folioFactura, int numFactura) {
        this.idFactura = idFactura;
        this.folioFactura = folioFactura;
        this.numFactura = numFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(String folioFactura) {
        this.folioFactura = folioFactura;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }
}
