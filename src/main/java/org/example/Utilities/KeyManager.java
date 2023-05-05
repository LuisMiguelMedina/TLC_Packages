package org.example.Utilities;
import java.util.Scanner;

public class KeyManager {
    public static String key;
    public static void getKey() {
        if (key == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la clave: ");
            key = scanner.nextLine();
        }
    }
    public static void setTempKey(String tempKey) {
        key = tempKey;
    }
}
