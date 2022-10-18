package com.example.logbook.CFModelo;

import java.util.Objects;

public class Cosecha {

    //Creacion del JSON - se deben generaro los getter and setters

    private String A_Id; // *
    private String B_Categoria; //
    private String C_Usuario; // *
    private String D_Fecha_Registro; //
    private String E_Fecha_Galera; // *
    private String F_Galera;
    private String G_Variedad_seleccion; //
    private String H_Codigo; //
    private String I_Tipo_de_plata; //
    private String J_Cantidad_de_planta; //
    private String K_Lote_logistica; //
    private String L_Num_empleado; //
    private String M_ID_Caja; //
    private String N; //
    private String O; //
    private String P; //
    private String Q; //
    private String R; //
    private String S; //
    private String T; //
    private String U; //


    public void cosecha(String a_Id, String b_Categoria, String c_Usuario, String d_Fecha_Registro, String e_Fecha_Galera, String f_Galera, String g_Variedad_seleccion,
                             String h_Codigo, String i_Tipo_de_plata, String j_Lote_logistica, String k_Num_empleado, String l_Num_empleado, String m_ID_Caja, String n,
                             String o, String p, String q, String r, String s, String t) {

        this.A_Id = a_Id;
        this.B_Categoria = b_Categoria;
        this.C_Usuario = c_Usuario;
        this.D_Fecha_Registro = d_Fecha_Registro;
        this.E_Fecha_Galera = e_Fecha_Galera;
        this.F_Galera = f_Galera;
        this.G_Variedad_seleccion = g_Variedad_seleccion;
        this.H_Codigo = h_Codigo;
        this.I_Tipo_de_plata = i_Tipo_de_plata;
        this.J_Cantidad_de_planta = j_Lote_logistica;
        this.K_Lote_logistica = k_Num_empleado;
        this.L_Num_empleado = l_Num_empleado;
        this.M_ID_Caja = m_ID_Caja;
        this.N = n;
        this.O = n;
        this.P = o;
        this.Q = p;
        this.R = q;
        this.S = r;
        this.T = s;

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

    public String getE_Fecha_Galera() {
        return E_Fecha_Galera;
    }

    public void setE_Fecha_Galera(String e_Fecha_Galera) {
        E_Fecha_Galera = e_Fecha_Galera;
    }

    public String getF_Galera() {
        return F_Galera;
    }

    public void setF_Galera(String f_Galera) {
        F_Galera = f_Galera;
    }

    public String getG_Variedad_seleccion() {
        return G_Variedad_seleccion;
    }

    public void setG_Variedad_seleccion(String g_Variedad_seleccion) {
        G_Variedad_seleccion = g_Variedad_seleccion;
    }

    public String getH_Codigo() {
        return H_Codigo;
    }

    public void setH_Codigo(String h_Codigo) {
        H_Codigo = h_Codigo;
    }

    public String getI_Tipo_de_plata() {
        return I_Tipo_de_plata;
    }

    public void setI_Tipo_de_plata(String i_Tipo_de_plata) {
        I_Tipo_de_plata = i_Tipo_de_plata;
    }

    public String getJ_Cantidad_de_planta() {
        return J_Cantidad_de_planta;
    }

    public void setJ_Cantidad_de_planta(String j_Cantidad_de_planta) {
        J_Cantidad_de_planta = j_Cantidad_de_planta;
    }

    public String getK_Lote_logistica() {
        return K_Lote_logistica;
    }

    public void setK_Lote_logistica(String k_Lote_logistica) {
        K_Lote_logistica = k_Lote_logistica;
    }

    public String getL_Num_empleado() {
        return L_Num_empleado;
    }

    public void setL_Num_empleado(String l_Num_empleado) {
        L_Num_empleado = l_Num_empleado;
    }

    public String getM_ID_Caja() {
        return M_ID_Caja;
    }

    public void setM_ID_Caja(String m_ID_Caja) {
        M_ID_Caja = m_ID_Caja;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O = o;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public String getU() {
        return U;
    }

    public void setU(String u) {
        U = u;
    }

    @Override
    public boolean equals(Object obj) {
        return A_Id.equals(((Cosecha)obj).A_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A_Id, B_Categoria, C_Usuario, D_Fecha_Registro, E_Fecha_Galera, F_Galera, G_Variedad_seleccion, H_Codigo,
                I_Tipo_de_plata, J_Cantidad_de_planta, K_Lote_logistica, L_Num_empleado, M_ID_Caja, N, O, P, Q, R, S, T, U);
    }
}
