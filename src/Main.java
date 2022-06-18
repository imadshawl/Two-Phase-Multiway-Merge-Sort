import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            System.out.println("Welcome to 2PMMS Algorithm!");
            System.out.println(
                    "1. Create a random list of integers within a range\n" +
                            "2. Display the generated random list (for debugging only!)\n" +
                            "3. Run 2PMMS Algorithm\n" +
                            "4. EXIT"
            );
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Please enter the number of integers 'n' ");
                    int n = scanner.nextInt();
                    System.out.println("Please enter the maximum possible value 'max' ");
                    int max_val = scanner.nextInt();
                    System.out.println("Please enter minimum possible value 'min' ");
                    int min_val = scanner.nextInt();
                    FileGenerator.create_ints(n, min_val, max_val);
                    System.out.println("Successfully created the file - data.txt !");
                    break;

                case 2:
                    FileGenerator.read_file();
                    break;

                case 3:
                    System.out.println("Please enter your RAM size");
                    int r = scanner.nextInt();
                    TPMMS.create_sortedsublists(r);
                    break;

                default:
                    end = true;
            }
        }
    }
}
