import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static final String messageWelcome = "Welcome to our simulation on the spread of disease. Please start the program by entering values to the system";
    static final String messagePopulationNumber = "Enter size of population";
    static final String messageMinDays = "Enter the minimum amount of days for the infection to last in an infected individual";
    static final String messageMaxDays = "Enter the maximum amount of days for the infection to last in an infected individual";
    static final String messageProbofInfection = "Enter the probability of an infection";
    static final String messageProbofDeath = "Enter the probability of death";
    static final String messageNumStartInfected = "Enter the number of people infected at the start";
    static Population population;

    public static void main(String[] args) {
        getUserInputs();
        population.printMatrix();
        population.runSimulation();
    }

    private static void getUserInputs() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("");
        System.out.println(messageWelcome);

        print(messagePopulationNumber);
        int populationNumber = scanner.nextInt();

        population = new Population(populationNumber);

        print(messageMinDays);
        population.daysMinIll = scanner.nextInt();

        print(messageMaxDays);
        population.daysMaxIll = scanner.nextInt();

        print(messageProbofInfection);
        population.probOfInfection = scanner.nextDouble();

        print(messageProbofDeath);
        population.probOfDead = scanner.nextDouble();

        print(messageNumStartInfected);
        int illNumber = scanner.nextInt();
        population.numberOfInitialIll = illNumber;

        population.printMatrix();

        IntStream.range(0, illNumber).forEach(i -> {
            System.out.println("------------------------------");
            int limit = (int)Math.sqrt(populationNumber)-1;
            System.out.println("Please enter X & Y values between 0 and " + limit);
            System.out.println("------------------------------");
            System.out.print("Give X position of infected member number " + (i + 1) + ":");
            int x = scanner.nextInt();
            System.out.print("Give Y position of infected member number " + (i + 1) + ":");
            int y = scanner.nextInt();
            population.setMemberStatus(x, y, StatusType.INFECTED);
        });
    }

    private static void print(String s) {
        System.out.println("------------------------------");
        System.out.print(s + " :");
    }
}
