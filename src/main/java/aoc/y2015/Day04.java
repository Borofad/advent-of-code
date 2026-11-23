package aoc.y2015;

import aoc.util.Input;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Day04 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 4);

        System.out.println(firstNumForMD5WithNLeadingZeros(input.get(0), 6));
    }

    private static int firstNumForMD5WithNLeadingZeros(String input, int numberOfZeros) {
        if (numberOfZeros < 0 || numberOfZeros > 32) {
            throw new IllegalArgumentException("MD5 has 32 hex digits");
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }

        byte[] prefix = input.getBytes(StandardCharsets.UTF_8);
        byte[] digits = new byte[11];
        byte[] hash = new byte[16];

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int pos = digits.length;
            int n = i;
            while (n > 0) {
                digits[--pos] = (byte) ('0' + (n % 10));
                n /= 10;
            }

            md.update(prefix);
            md.update(digits, pos, digits.length - pos);
            try {
                md.digest(hash, 0, hash.length);
            } catch (DigestException e) {
                throw new RuntimeException(e);
            }

            if (hasLeadingHexZeros(hash, numberOfZeros)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean hasLeadingHexZeros(byte[] hash, int numberOfZeros) {
        int fullBytes = numberOfZeros / 2;
        for (int j = 0; j < fullBytes; j++) {
            if (hash[j] != 0) {
                return false;
            }
        }

        return (numberOfZeros % 2 == 0) || ((hash[fullBytes] & 0xF0) == 0);
    }
}
