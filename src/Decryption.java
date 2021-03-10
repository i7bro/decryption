import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Decryption
{
    public static String decryptLetter (String letter, int shift) {
        int shiftIndex;
        String lettersString = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        shiftIndex = lettersString.indexOf(letter.toLowerCase()) + shift;
        if (!lettersString.contains(letter)) {
            return String.valueOf(lettersString.charAt(shiftIndex)).toUpperCase();
        }
        return String.valueOf(lettersString.charAt(shiftIndex));
    }

    public static String[] decryptString(String cypher) {
        String[] strArray = cypher.split("");
        String[] finalStrArray = new String[32];

        for (int j = 0; j < finalStrArray.length; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i].matches("[а-яА-ЯЁё]*")) {
                    strArray[i] = decryptLetter(strArray[i], 1);
                }
                sb.append(strArray[i]);
            }
            finalStrArray[j] = sb.toString();
        }
        return finalStrArray;
    }

    public static String decryptStringWithShift(String cypher, int shift) {
        String[] strArray = cypher.split("");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].matches("\\w*")) {
                strArray[i] = decryptLetter(strArray[i], shift);
            }
            sb.append(strArray[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Stream<String> stream = Files.lines(Paths.get("cypher.txt"), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        stream.forEach(s -> sb.append(s).append("\n"));

        String[] finalStrArray = decryptString(sb.toString());

        for (int i = 0; i < finalStrArray.length; i++) {
            System.out.println("Кодовое значение: " + (i+1));
            System.out.println(finalStrArray[i]);
        }
    }

}
