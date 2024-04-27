import java.io.File;
import java.util.Scanner;

public class Driver7 {
    public static Scanner input;
    public static File f;
    public static String fName;
    public static int n;
    public static hash h;

    public static void main(String[] args) {
        System.out.print("Please enter a file name/path : ");
        input = new Scanner(System.in);
        fName = input.nextLine();
        f = new File(fName);
        try {
            input = new Scanner(f);
        } catch (Exception E) {
            System.out.println("File not found");
        }

        n = input.nextInt();
        h = new hash(59);

        for(int i = 0; i < n; i++){
            String word = input.next();
            System.out.println("String == " + word);

            h.insertHash1(word);
            h.insertHash2(word);
            System.out.println(word + " " + h.hash1(word));
            //System.out.println("Hash function #1: " + h.getSum1() + "  Table position: " + h.hash1(word));
            //System.out.println("Hash function #2: " + h.getSum2() + "  Table position: " + h.hash2(word));
            //System.out.printf("\n");
        }
        System.out.println("Total Collisions Hash #1 : " + h.getColl1());
        System.out.print("\n");
        System.out.println("Total Collisions Hash #2 : " + h.getColl2());
    }
}
