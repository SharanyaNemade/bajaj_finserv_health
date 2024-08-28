package main;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PRNHashGenerator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar <jarfile> <PRN Number> <path to JSON file>");
            return;
        }

        String prnNumber = args[0];
        String jsonFilePath = args[1];

        try {
            // Read and parse JSON file
            FileInputStream fis = new FileInputStream(jsonFilePath);
            JSONTokener tokener = new JSONTokener();
            JSONObject jsonObject = new JSONObject();

            // Traverse JSON to find the key "destination"
            String destinationValue = findDestinationValue(jsonObject);

            if (destinationValue == null) {
                System.out.println("Key 'destination' not found in the JSON file.");
                return;
            }

            // Generate random 8-character alphanumeric string
            String randomString = generateRandomString(8);

            // Concatenate PRN number, destination value, and random string
            String concatenatedString = prnNumber + destinationValue + randomString;

            // Compute MD5 hash
            String md5Hash = computeMD5Hash(concatenatedString);

            // Output the result in the required format
            System.out.println(md5Hash + ";" + randomString);

        } catch (IOException e) {
            System.out.println("Error reading the JSON file: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not found: " + e.getMessage());
        }
    }

    private static String findDestinationValue(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (key.equals("destination")) {
                return value.toString();
            } else if (value instanceof JSONObject) {
                String found = findDestinationValue((JSONObject) value);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private static String computeMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
