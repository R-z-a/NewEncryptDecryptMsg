package newencryptdecryptmsg;

import java.util.Scanner;

public class NewEncryptDecryptMsg {
    static String stringToBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            String str=Integer.toBinaryString(b);
            StringBuilder padding= new StringBuilder();
            for (int i = 0; i < (8-str.length());i++)
                padding.append("0");
            binary.append(padding).append(str).append(" ");
        }
        System.out.println(binary);
        return binary.toString();
    }

    static String reverse(String str) {
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder=strBuilder.reverse();
        return strBuilder.toString();
    }

    static String XOR_operation(String inputString, String key) {
        String outputString = "";

        int len = key.length();

        for (int i = 0; i < len; i++) {
            outputString = outputString +
                    ((inputString.charAt(i) ^ key.charAt(i)));
        }

        return outputString;
    }

    static void formatBinary(String binary) {
        String output = "";
        int numberLength = binary.length() / 8;
        String[] array;
        array = new String[numberLength];
        int[] intArray;
        intArray = new int[numberLength];
        int j = 0;
        for (int i = 0; i < numberLength; i++) {
            array[i] = "";
            for (; j < binary.length(); j++) {
                array[i] += binary.charAt(j);
                if (array[i].length() == 8) {
                    intArray[i] = Integer.parseInt(array[i], 2);
                    array[i] = Character.toString((char) intArray[i]);
                    output += array[i];
                    j++;
                    break;
                }
            }

        }
        System.out.println(output);
    }
public static String encryptPer(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return new String(chars);
    }

    public static String decryptPer(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("please, enter the plaintext letters");
        String plaintext = input.nextLine();
        System.out.println("please, enter key with the same number of letters.");
        String key = input.nextLine();
        System.out.print("Plaintext:          ");
        plaintext = stringToBinary(plaintext);
        String reverse = reverse(plaintext);
        System.out.println("\nreversedPlaintext: "+reverse);
        reverse= reverse.replaceAll("[^0-1]", "");

        System.out.print("key:                ");
        key = stringToBinary(key);
        key = key.replaceAll("[^0-1]", "");
        //System.out.println("****************************");
        String encrypByXor = XOR_operation(reverse, key);
        System.out.println("Ciphertext:         "+encrypByXor);
        String permu=encryptPer(encrypByXor);
        System.out.print("String Ciphertext:  ");
        formatBinary(permu);


        System.out.println("****************** decryption step ******************");
        System.out.println("please, enter the Ciphertext letters");
        String ciphertext = input.nextLine();
        System.out.println("please, enter key with the same number of letters.");
        key = input.nextLine();
        System.out.println(permu);
        ciphertext = ciphertext.replaceAll("[^0-1]", "");
        key = stringToBinary(key);
        key = key.replaceAll("[^0-1]", "");
        System.out.println("Decrypted String");
        String permution=decryptPer(permu);
        String decXOR = XOR_operation(permution, key);
        //الان بعد ماعملت مره اخرى عملية XOR راح ترجع لوضعها في inverse mode
        System.out.println("The reverse Plaintext:"+decXOR);
        plaintext = reverse(decXOR);
        System.out.println("Plaintext:        "+plaintext);
        System.out.println("Plaintext as string:        ");
        formatBinary(plaintext);
   }
}