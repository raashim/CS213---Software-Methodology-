public enum Specialty {
    Family(250),
    Pediatrician(300),
    Allergist(350);

    private final int charge;

    Specialty(int charge) {
        this.charge = charge;
    }

    public int getCharge(){
        return this.charge;
    }
}
