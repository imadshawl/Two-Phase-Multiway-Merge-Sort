import java.io.*;
import java.util.*;


public class FileGenerator {

    //Here we are generating random ints between min and max values
    static void create_ints(int num, int min_val, int max_val) {
        try {
            String path = "data.txt";
            Random rand = new Random();
            File myFile = new File(path);
            myFile.createNewFile();
            PrintWriter pw = new PrintWriter(path, "UTF-8");

            //generate random value within the range
            for (int i = 0; i < num; i++) {
                int rand_num = (int)(Math.random()*(max_val-min_val+1)+min_val);
                pw.println(rand_num);
            }
            pw.close();


        } catch (IOException e) {
            System.out.println("Error occured while generating random ints");
            e.printStackTrace();
        }
    }


    //Reading and displaying the generated n integers for option 2 (debugging)
    static void read_file() {
        try {
            File myFile = new File("data.txt");
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String d = sc.nextLine();
                System.out.println(d);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file");
            e.printStackTrace();
        }
    }

}
