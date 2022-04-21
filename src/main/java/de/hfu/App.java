package de.hfu;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter stuff: ");

        String stuff = myScanner.nextLine();
        System.out.println("Converted to uppercase: " + stuff.toUpperCase());
        myScanner.close();
    }
}
