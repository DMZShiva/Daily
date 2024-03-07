package com.example.watersystem.data.type;

public class DataCardType {

    public static final int DATA_EQUIPMENT = 1;
    public static final int DATA_LAST_CONNECTION_TIME = 2;
    public static final int DATA_TDS = 3;
    public static final int DATA_PH = 4;
    public static final int DATA_TEM = 5;
    public static final int DATA_TU = 5;


    private String equipment;

    private String lastConnection;

    private String dataTds;

    private String dataPh;

    private String dataTem;

    private String dataTu;

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public String getDataTds() {
        return dataTds;
    }

    public void setDataTds(String dataTds) {
        this.dataTds = dataTds;
    }

    public String getDataPh() {
        return dataPh;
    }

    public void setDataPh(String dataPh) {
        this.dataPh = dataPh;
    }

    public String getDataTem() {
        return dataTem;
    }

    public void setDataTem(String dataTem) {
        this.dataTem = dataTem;
    }

    public String getDataTu() {
        return dataTu;
    }

    public void setDataTu(String dataTu) {
        this.dataTu = dataTu;
    }
}
