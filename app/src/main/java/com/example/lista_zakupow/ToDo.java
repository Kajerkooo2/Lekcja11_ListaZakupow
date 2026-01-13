package com.example.lista_zakupow;

public class ToDo {
    private String nazwa;
    private boolean czyWykonane;
    private byte priorytet;

    public ToDo(byte priorytet, String nazwa) {
        this.priorytet = priorytet;
        this.czyWykonane = false;
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public boolean isCzyWykonane() {
        return czyWykonane;
    }

    public void setCzyWykonane(boolean czyWykonane) {
        this.czyWykonane = czyWykonane;
    }

    public byte getPriorytet() {
        return priorytet;
    }

    public void setPriorytet(byte priorytet) {
        this.priorytet = priorytet;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "nazwa='" + nazwa + '\'' +
                ", priorytet=" + priorytet +
                '}';
    }
}
