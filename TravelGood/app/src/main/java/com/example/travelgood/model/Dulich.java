package com.example.travelgood.model;

public class Dulich {
        public int ID;
        public String Tendiadiem;
        public String Diachi;
        public String Hinhanhdiadiemdulich;
        public String Motadiadiem;
        public int IDDulich;

    public Dulich(int ID, String tendiadiem, String diachi, String hinhanhdiadiemdulich, String motadiadiem, int IDDulich) {
        this.ID = ID;
        Tendiadiem = tendiadiem;
        Diachi = diachi;
        Hinhanhdiadiemdulich = hinhanhdiadiemdulich;
        Motadiadiem = motadiadiem;
        this.IDDulich = IDDulich;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTendiadiem() {
        return Tendiadiem;
    }

    public void setTendiadiem(String tendiadiem) {
        Tendiadiem = tendiadiem;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getHinhanhdiadiemdulich() {
        return Hinhanhdiadiemdulich;
    }

    public void setHinhanhdiadiemdulich(String hinhanhdiadiemdulich) {
        Hinhanhdiadiemdulich = hinhanhdiadiemdulich;
    }

    public String getMotadiadiem() {
        return Motadiadiem;
    }

    public void setMotadiadiem(String motadiadiem) {
        Motadiadiem = motadiadiem;
    }

    public int getIDDulich() {
        return IDDulich;
    }

    public void setIDDulich(int IDDulich) {
        this.IDDulich = IDDulich;
    }
}
