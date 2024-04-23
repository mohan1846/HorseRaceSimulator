import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Race {
    public static final double FALL_PROBABILITY_BASE = 0.05;
    public int numberOfLanes =3;
    public int raceLength;
    public Horse[] horses; // Array to manage horses

    public Race(int distance) {
        this.raceLength = distance;
        this.horses = new Horse[3];
    }

    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber >= 1 && laneNumber <= horses.length) {
            horses[laneNumber - 1] = theHorse;
        } else {
            throw new IllegalArgumentException("Lane number " + laneNumber + " is invalid.");
        }
    }

    public void startRace() {
        for (Horse horse : horses) {
            if (horse != null) horse.goBackToStart();
        }

        boolean raceFinished = false;
        while (!raceFinished) {
            int horsesStillInRace = 0;
            for (Horse horse : horses) {
                if (horse != null) {
                    if (!horse.hasFallen()) {
                        moveHorse(horse);
                        horsesStillInRace++;
                    }
                    if (horse.getDistanceTravelled() >= raceLength) {
                        System.out.println("\nAnd the winner is " + horse.getName());
                        raceFinished = true;
                        break; // Breaks loop if a horse finishes the race
                    }
                }
            }

            if (horsesStillInRace == 0) {
                // If all horses have fallen, end the race.
                raceFinished = true;
                System.out.println("\nAll horses have fallen. Race over!");
            }

            printRace();

            // Slow down the loop
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Race was interrupted.");
                return;
            }
        }
    }


    public void moveHorse(Horse theHorse) {
        if (!theHorse.hasFallen()) {
            // Print statements to check the probabilities
            double moveProbability = theHorse.getConfidence();
            double fallProbability = FALL_PROBABILITY_BASE * (1 - moveProbability);

            System.out.println("Move Probability for " + theHorse.getName() + ": " + moveProbability);
            System.out.println("Fall Probability for " + theHorse.getName() + ": " + fallProbability);

            if (Math.random() < moveProbability) {
                theHorse.moveForward();
                System.out.println(theHorse.getName() + " moved forward to " + theHorse.getDistanceTravelled() + "!");
            }
            if (Math.random() < fallProbability) {
                theHorse.fall();
                System.out.println(theHorse.getName() + " has fallen!");
            }
        }
    }


    public boolean raceWonBy(Horse theHorse) {
        return theHorse.getDistanceTravelled() >= raceLength;
    }

    public void printRace() {
        clearConsole();
        multiplePrint('=', raceLength + numberOfLanes);
        System.out.println();

        for (Horse horse : horses) {
            if (horse != null) {
                printLane(horse);
                System.out.println();
            }
        }

        multiplePrint('=', raceLength + numberOfLanes);
        System.out.println();
    }

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    public void printLane(Horse theHorse) {
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        System.out.print('|');

        multiplePrint(' ', spacesBefore);

        // Prints 'X' if horse is fallen
        if (theHorse.hasFallen()) {
            System.out.print('X');
        } else {
            System.out.print(theHorse.getSymbol());
        }

        multiplePrint(' ', spacesAfter);

        System.out.print('|');
    }


    public void multiplePrint(char aChar, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(aChar);
        }
    }
}

