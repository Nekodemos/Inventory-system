package com.example.software_i.Model;

public class INOROUT {
    int partID;
    String InHouse_OutSource;
    boolean isInHouse;

    /**
     *
     * @param partID
     * @param inHouse_OutSource
     * @param isInHouse
     */
    public INOROUT(int partID, String inHouse_OutSource, boolean isInHouse) {
        this.partID = partID;
        InHouse_OutSource = inHouse_OutSource;
        this.isInHouse = isInHouse;
    }

    /**
     *
     * @return
     */
    public int getPartID() {
        return partID;
    }

    /**
     *
     * @param partID
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }

    /**
     *
     * @return
     */
    public String getInHouse_OutSource() {
        return InHouse_OutSource;
    }

    /**
     *
     * @param inHouse_OutSource
     */
    public void setInHouse_OutSource(String inHouse_OutSource) {
        InHouse_OutSource = inHouse_OutSource;
    }

    /**
     *
     * @return
     */
    public boolean isInHouse() {
        return isInHouse;
    }

    /**
     *
     * @param inHouse
     */
    public void setInHouse(boolean inHouse) {
        isInHouse = inHouse;
    }
}
