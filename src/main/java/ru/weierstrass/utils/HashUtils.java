package ru.weierstrass.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    /**
     * Convert string to md5 hash as hex string
     *
     * @return md5 hash
     */
    public static String md5( String string ) {
        try {
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            return DatatypeConverter.printHexBinary( md5.digest( string.getBytes() ) ).toLowerCase();
        }
        catch( NoSuchAlgorithmException e ) {
            return "";
        }
    }

}
