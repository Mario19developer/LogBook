package com.example.logbook.NominasModelo;

import java.util.Objects;

public class Nominas {

    //creacion del objeto JSON - se deben generar lo getter and setter
    private String A_Id; // *
    private String B_Categoria; // *
    private String C_Usuario; //
    private String D_Fecha_Registro; // *
    private String E_Zona_Dispositivo; //
    private String F_Tipo_Lista; //
    private String G_Zona_Registro; //
    private String H_Rancho_Registro; //
    private String I_QR_Empleado; //
    private String J_Latitud; //
    private String K_Longitud; //

    public void nominas(String a_Id, String b_Categoria, String c_Usuario, String d_Fecha_Registro, String e_Zona_Dispositivo,
                        String f_Tipo_Lista, String g_Zona_Registro, String h_Rancho_Registro, String i_QR_Empleado, String j_Latitud, String k_Longitud){
        this.A_Id = a_Id;
        this.B_Categoria = b_Categoria;
        this.C_Usuario = c_Usuario;
        this.D_Fecha_Registro = d_Fecha_Registro;
        this.E_Zona_Dispositivo = e_Zona_Dispositivo;
        this.F_Tipo_Lista = f_Tipo_Lista;
        this.G_Zona_Registro = g_Zona_Registro;
        this.H_Rancho_Registro = h_Rancho_Registro;
        this.I_QR_Empleado = i_QR_Empleado;
        this.J_Latitud = j_Latitud;
        this.K_Longitud = k_Longitud;
    }

    public String getA_Id() {
        return A_Id;
    }

    public void setA_Id(String a_Id) {
        A_Id = a_Id;
    }

    public String getB_Categoria() {
        return B_Categoria;
    }

    public void setB_Categoria(String b_Categoria) {
        B_Categoria = b_Categoria;
    }

    public String getC_Usuario() {
        return C_Usuario;
    }

    public void setC_Usuario(String c_Usuario) {
        C_Usuario = c_Usuario;
    }

    public String getD_Fecha_Registro() {
        return D_Fecha_Registro;
    }

    public void setD_Fecha_Registro(String d_Fecha_Registro) {
        D_Fecha_Registro = d_Fecha_Registro;
    }

    public String getE_Zona_Dispositivo() {
        return E_Zona_Dispositivo;
    }

    public void setE_Zona_Dispositivo(String e_Zona_Dispositivo) {
        E_Zona_Dispositivo = e_Zona_Dispositivo;
    }

    public String getF_Tipo_Lista() {
        return F_Tipo_Lista;
    }

    public void setF_Tipo_Lista(String f_Tipo_Lista) {
        F_Tipo_Lista = f_Tipo_Lista;
    }

    public String getG_Zona_Registro() {
        return G_Zona_Registro;
    }

    public void setG_Zona_Registro(String g_Zona_Registro) {
        G_Zona_Registro = g_Zona_Registro;
    }

    public String getH_Rancho_Registro() {
        return H_Rancho_Registro;
    }

    public void setH_Rancho_Registro(String h_Rancho_Registro) {
        H_Rancho_Registro = h_Rancho_Registro;
    }

    public String getI_QR_Empleado() {
        return I_QR_Empleado;
    }

    public void setI_QR_Empleado(String i_QR_Empleado) {
        I_QR_Empleado = i_QR_Empleado;
    }

    public String getJ_Latitud() {
        return J_Latitud;
    }

    public void setJ_Latitud(String j_Latitud) {
        J_Latitud = j_Latitud;
    }

    public String getK_Longitud() {
        return K_Longitud;
    }

    public void setK_Longitud(String k_Longitud) {
        K_Longitud = k_Longitud;
    }

    @Override
    public boolean equals(Object o) {
        return A_Id.equals(((Nominas)o).A_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A_Id, B_Categoria, C_Usuario, D_Fecha_Registro,
                E_Zona_Dispositivo, F_Tipo_Lista, G_Zona_Registro, H_Rancho_Registro, I_QR_Empleado,
                J_Latitud, K_Longitud);
    }
}
