package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();
            if (operation.equals("encode")){
                System.out.println("Input string:");
                String string = scanner.nextLine();
                System.out.println("Encoded string:");
                System.out.println(encode(string));
                System.out.println();
            } else if (operation.equals("decode")) {
                System.out.println("Input encoded string:");
                String string = scanner.nextLine();
                try {
                    System.out.println("Decoded string\n" + decode(string));
                } catch (Exception e){
                    System.out.println("Encoded string is not valid");
                }

                System.out.println();
            } else if (operation.equals("exit")){
                System.out.println("Bye!");
                break;
            } else{
                System.out.println("There is no '" + operation + "' operation");
                System.out.println();
            }
        }
// TODO THERE IS PROBABLY ERROR THAT YOU CAN'T HANDLE SPACES
    }
    public static String charToBinary(String string){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++){
            String binary = Integer.toBinaryString(string.charAt(i));
            binary = String.format("%7s", binary).replace(' ', '0');
            result.append(binary);
        }
        return result.toString();
    }
    public static StringBuilder encode(String string){
        String binary = charToBinary(string);
        StringBuilder result = new StringBuilder();
        String previous = "s";

        for (int i = 0; i < binary.length(); i++){
            if (binary.charAt(i) == '1'){
                if (previous.equals("1")){
                    result.append("0");
                } else{
                    result.append(" 0 0");
                    previous = "1";
                }
            } else{
                if (previous.equals("0")){
                    result.append("0");
                } else{
                    result.append(" 00 0");
                    previous = "0";
                }
            }
        }
        result.deleteCharAt(0);
        return result;
    }
    public static String chuckToBinary(String string){
        StringBuilder result = new StringBuilder();

        String[] blocks = string.split(" ");

        for (int i = 0; i < blocks.length; i+= 2){
            if (blocks[i].equals("0") || blocks[i].equals("00")){
            } else{
                throw new IllegalArgumentException();
            }
        }
        String[] ready = new String[blocks.length / 2 + blocks.length % 2];

        for (int i = 0; i < ready.length - 1; i++){
            ready[i] = blocks[i * 2] + " " + blocks[i * 2 + 1];
        }

        int[] binary = new int[ready.length];
        for (int i = 0; i < blocks.length; i+=2){
            int count = 0;
            if (blocks[i].contains("00")){
                for (int j = 0; j < blocks[i + 1].length(); j++){
                    if (blocks[i + 1].charAt(j) == '0'){
                        count++;
                    }
                }
                for (int k = 0; k < count; k++){
                    result.append("0");
                }
            } else{
                for (int j = 0; j < blocks[i + 1].length(); j++){
                    if (blocks[i + 1].charAt(j) == '0'){
                        count++;
                    }
                }
                for (int k = 0; k < count; k++){
                    result.append("1");
                }
            }
        }

        if (blocks.length % 2 == 1){
           throw new IllegalArgumentException();

        }
            for (int i = 7; i < result.length() - 1; i+=8){
            result.insert(i, " ");
        }

        return result.toString();
    }
    public static String decode(String binary){
        StringBuilder result = new StringBuilder();

        String[] binaries = chuckToBinary(binary).split(" ");
        for (int i = 0; i < binaries.length; i++){
            if (binaries[i].length() % 7 != 0){
                throw new IllegalArgumentException("gg");
            }
        }
        for (int i = 0; i < binaries.length; i++) {
            int intCode = Integer.parseInt(binaries[i], 2);
            char charCode = (char) intCode;
            result.append(charCode);
        }
        return String.valueOf(result);

    }
}