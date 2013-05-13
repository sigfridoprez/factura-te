/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sig.derbyclient.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sigfrido.perez
 */
public class ReceptoresDto {

    private int receptorId;
    private String nombre;
    private String rfcIni;
    private String rfcNac;
    private String rfcHomo;
    private String direccion;
    private List<FacturaDto> facturas;

    public ReceptoresDto(String nombre, String rfcIni, String rfcNac, String rfcHomo) {
        this.nombre = nombre;
        this.rfcIni = rfcIni;
        this.rfcNac = rfcNac;
        this.rfcHomo = rfcHomo;
        this.facturas = new ArrayList<FacturaDto>();
    }

    public List<FacturaDto> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaDto> facturas) {
        this.facturas = facturas;
    }

    public int getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfcIni() {
        return rfcIni;
    }

    public void setRfcIni(String rfcIni) {
        this.rfcIni = rfcIni;
    }

    public String getRfcNac() {
        return rfcNac;
    }

    public void setRfcNac(String rfcNac) {
        this.rfcNac = rfcNac;
    }

    public String getRfcHomo() {
        return rfcHomo;
    }

    public void setRfcHomo(String rfcHomo) {
        this.rfcHomo = rfcHomo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
