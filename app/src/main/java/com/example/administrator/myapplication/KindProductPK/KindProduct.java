package com.example.administrator.myapplication.KindProductPK;

public class KindProduct {
    private int Stt;
    private String CodeKind;
    private String NameKind;

    public KindProduct(int stt, String codeKind, String nameKind) {
        this.Stt = stt;
        this.CodeKind = codeKind;
        this.NameKind = nameKind;
    }

    public KindProduct() {
    }

    public String getCodeKind() {
        return this.CodeKind;
    }

    public void setCodeKind(String codeKind) {
        this.CodeKind = codeKind;
    }

    public String getNameKind() {
        return this.NameKind;
    }

    public void setNameKind(String nameKind) {
        this.NameKind = nameKind;
    }

    public int getStt() {
        return this.Stt;
    }

    public void setStt(int stt) {
        this.Stt = stt;
    }
}
