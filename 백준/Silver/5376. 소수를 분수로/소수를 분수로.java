import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public String[] convertToFractions(String[] decimalNumbers) {
        String[] fractions = new String[decimalNumbers.length];
        for (int i = 0; i < decimalNumbers.length; i++) {
            fractions[i] = decimalNumbers[i].contains("(")
                    ? convertRepeatingDecimal(decimalNumbers[i])
                    : convertNonRepeatingDecimal(decimalNumbers[i]);
        }
        return fractions;
    }

    private String convertNonRepeatingDecimal(String decimal) {
        int denominator = (int) Math.pow(10, decimal.length() - 2);
        int numerator = Integer.parseInt(decimal.substring(2));
        long gcdValue = gcd(denominator, numerator);
        return (numerator / gcdValue) + "/" + (denominator / gcdValue);
    }

    private String convertRepeatingDecimal(String decimal) {
        int startIndex = decimal.indexOf("(");
        int length = decimal.length() - 2;
        long denominator = (long) Math.pow(10, length - 2) - (long) Math.pow(10, startIndex - 2);

        String repeatingPart = decimal.substring(2, decimal.length() - 1).replace("(", "").replace(")", "");
        long numerator = Long.parseLong(repeatingPart);

        if (startIndex > 2) {
            numerator -= Long.parseLong(decimal.substring(2, startIndex));
        }

        long gcdValue = gcd(denominator, numerator);
        return (numerator / gcdValue) + "/" + (denominator / gcdValue);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCount = Integer.parseInt(br.readLine());
        String[] decimalNumbers = new String[testCount];

        for (int i = 0; i < testCount; i++) {
            decimalNumbers[i] = br.readLine();
        }

        br.close();

        Main main = new Main();
        String[] fractions = main.convertToFractions(decimalNumbers);

        for (String fraction : fractions) {
            bw.write(fraction + "\n");
        }

        bw.flush();
        bw.close();
    }
}
