public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean fallen;
    private double confidence;

    //Constructor
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = Math.min(Math.max(horseConfidence, 0), 1);
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void fall() {
        this.fallen = true;
    }

    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public boolean hasFallen() {
        return fallen;
    }

    public void moveForward() {
        if (!hasFallen()) {
            distanceTravelled++;
        }
    }

    public void setConfidence(double newConfidence) {
        this.confidence = Math.min(Math.max(newConfidence, 0), 1);
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }
}
