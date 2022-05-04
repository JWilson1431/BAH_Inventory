package model;

public class Medication {

    private int medicationId;
    private String medName;
    private int quantity;

    public Medication(){};
    public Medication(int medicationId, String medName, int quantity) {
        this.medicationId=medicationId;
        this.medName = medName;
        this.quantity = quantity;
    }
    public Medication(String medName, int quantity){
        this.medName=medName;
        this.quantity=quantity;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }
}
