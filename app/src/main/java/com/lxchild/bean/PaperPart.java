package com.lxchild.bean;

/**
 * Created by LXChild on 20/11/2016.
 */

public class PaperPart {
    int paperID;
    String partA;
    String partB;
    String partC;
    String partD;
    String partE;
    int typeA;
    int typeB;
    int typeC;
    int typeD;
    int typeE;

    public PaperPart(int paperID, String partA, int typeA) {
        this.paperID = paperID;
        this.partA = partA;
        this.partB = null;
        this.partC = null;
        this.partD = null;
        this.partE = null;

        this.typeA = typeA;
        this.typeB = -1;
        this.typeC = -1;
        this.typeD = -1;
        this.typeE = -1;
    }

    public int getPaperID() {
        return paperID;
    }
    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

    public String getPartA() {
        return partA;
    }
    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }
    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }
    public void setPartC(String partC) {
        this.partC = partC;
    }

    public String getPartD() {
        return partD;
    }
    public void setPartD(String partD) {
        this.partD = partD;
    }

    public String getPartE() {
        return partE;
    }
    public void setPartE(String partE) {
        this.partE = partE;
    }


    public int getTypeA() {
        return typeA;
    }
    public void setTypeA(int typeA) {
        this.typeA = typeA;
    }

    public int getTypeB() {
        return typeB;
    }
    public void setTypeB(int typeB) {
        this.typeB = typeB;
    }

    public int getTypeC() {
        return typeC;
    }
    public void setTypeC(int typeC) {
        this.typeC = typeC;
    }

    public int getTypeD() {
        return typeD;
    }
    public void setTypeD(int typeD) {
        this.typeD = typeD;
    }

    public int getTypeE() {
        return typeE;
    }
    public void setTypeE(int typeE) {
        this.typeE = typeE;
    }
}
