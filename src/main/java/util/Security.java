package util;

import org.apache.commons.codec.binary.Hex;

public class Security {

    // Encode password and salt
    public static String encodePassword(char[] password, byte[] salt) {
        byte[] hashedPassword = Crypto.hashPassword(password, salt, Crypto.ITERATIONS, Crypto.KEY_LENGTH);
        // ^ apply salt to password
        String encodedPassword = Hex.encodeHexString(hashedPassword); // salted and hashed password is encoded
        return encodedPassword;
    }

    public static String encodeSalt(byte[] salt) {
        String encodedSalt = Hex.encodeHexString(salt);
        return encodedSalt;
    }
    
}