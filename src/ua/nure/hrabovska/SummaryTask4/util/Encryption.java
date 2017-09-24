package ua.nure.hrabovska.SummaryTask4.util;

/**
 * Class for password cipher
 *
 * @author Y. Hrabovska
 */
public class Encryption {

    /**
     * Encryption of password
     *
     * @param password - password to encryption
     */
    public static String doCipher(String password) {
        char[] chars = password.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] += i + 1;
        }

        return new String(chars);
    }
    /**
     * Decryption of cipher
     *
     * @param cipher - cipher to decryption
     */
    public static String doDecipher(String cipher) {
        char[] chars = cipher.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] -= i + 1;
        }

        return new String(chars);
    }
}
