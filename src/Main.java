import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz sciezke do pliku treningowego");
        String trainPath = scanner.next();
        System.out.println("Wprowadz stala uczenia");
        double learningConst = scanner.nextDouble();
        System.out.println("Wprowadz liczbe epok");
        int numberOfCycles = scanner.nextInt();
        System.out.println("Wprowadz text do sprawdzania");
        scanner.nextLine();
        String text = scanner.nextLine();
        DataRepository dataRepository = new DataRepository(trainPath);
        final TestService testService = new TestService(dataRepository, learningConst, numberOfCycles);
        System.out.println(testService.languageOfText(text));


    }
}