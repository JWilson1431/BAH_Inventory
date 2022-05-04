package model;

public class OralMed extends Medication{

    private int numTabletsInBottle;
    private int tabletStrength;

    public OralMed(int numTabletsInBottle, int tabletStrength) {
        this.numTabletsInBottle = numTabletsInBottle;
        this.tabletStrength = tabletStrength;
    }

    public OralMed(int medicationId, String medName, int quantity, int numTabletsInBottle, int tabletStrength) {
        super(medicationId,medName, quantity);
        this.numTabletsInBottle = numTabletsInBottle;
        this.tabletStrength = tabletStrength;
    }

    public int getNumTabletsInBottle() {
        return numTabletsInBottle;
    }

    public void setNumTabletsInBottle(int numTabletsInBottle) {
        this.numTabletsInBottle = numTabletsInBottle;
    }

    public int getTabletStrength() {
        return tabletStrength;
    }

    public void setTabletStrength(int tabletStrength) {
        this.tabletStrength = tabletStrength;
    }
}
