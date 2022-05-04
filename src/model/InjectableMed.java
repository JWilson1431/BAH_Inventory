package model;

public class InjectableMed extends Medication{

    private String concentration;
    private int sizeOfVial;

    public InjectableMed(String concentration, int sizeOfVial) {
        this.concentration = concentration;
        this.sizeOfVial = sizeOfVial;
    }

    public InjectableMed(int medicationId, String medName, int quantity, String concentration, int sizeOfVial) {
        super(medicationId,medName, quantity);
        this.concentration = concentration;
        this.sizeOfVial = sizeOfVial;
    }

    public InjectableMed(String medName, int quantity, String concentration, int sizeOfVial){
        super(medName,quantity);
        this.concentration=concentration;
        this.sizeOfVial=sizeOfVial;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public int getSizeOfVial() {
        return sizeOfVial;
    }

    public void setSizeOfVial(int sizeOfVial) {
        this.sizeOfVial = sizeOfVial;
    }
}
