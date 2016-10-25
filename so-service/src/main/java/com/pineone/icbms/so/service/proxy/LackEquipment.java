package com.pineone.icbms.so.service.proxy;

public class LackEquipment {
    //
    private String inv;
    private String qnt;

    public LackEquipment() {
    }

    public LackEquipment(String inv, String qnt) {
        this.inv = inv;
        this.qnt = qnt;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    @Override
    public String toString() {
        return "LackEquipment{" +
                "inv='" + inv + '\'' +
                ", qnt='" + qnt + '\'' +
                '}';
    }
}
