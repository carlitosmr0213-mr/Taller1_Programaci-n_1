package co.edu.uptc.taller1.run;
 
import java.util.Scanner;
import co.edu.uptc.taller1.logic.Management;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Management.execute(sc);
        sc.close();
    }
}