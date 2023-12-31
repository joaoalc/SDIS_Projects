package com.company.dataStructures;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FileInfo {
    public String filePath;
    public String fileID;
    public String unencryptedFileID;
    public int numberOfChunks;
    public int desiredReplicationDegree;
    //Indexes are chunk numbers; Each string is a userID; Each Array is a list of users in the chunk
    public ArrayList<ArrayList<String>> usersBackingUp;

    public FileInfo(String filePath, String unencryptedFileID, String fileID, ArrayList<ArrayList<String>> usersBackingUp, int numberOfChunks, int desiredReplicationDegree){
        this.filePath = filePath;
        this.unencryptedFileID = unencryptedFileID;
        this.fileID = fileID;
        this.usersBackingUp = usersBackingUp;
        this.numberOfChunks = numberOfChunks;
        this.desiredReplicationDegree = desiredReplicationDegree;
    }

    public FileInfo(String filePath, String unencryptedFileID, int numberOfChunks, int desiredReplicationDegree) {
        try {
            this.filePath = filePath;
            this.unencryptedFileID = unencryptedFileID;
            this.fileID = toHexString(getSHA(unencryptedFileID));
            System.out.println();
            this.usersBackingUp = new ArrayList<>();
            this.numberOfChunks = numberOfChunks;
            this.desiredReplicationDegree = desiredReplicationDegree;
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("SHA256 no longer exists, appearantly.");
        }
    }


    //getSHA and toHexString functions taken from geeksforgeeks
    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    //In big endian form
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public void addUser(String userID, int chunk) {

        // Verify if user already exists
        boolean repeat = false;
        for(int i = 0; i < usersBackingUp.get(chunk).size(); i++){
            if((usersBackingUp.get(chunk)).get(i).compareTo(userID) == 0){
                repeat = true;
                break;
            }
        }
        if(!repeat) {
            usersBackingUp.get(chunk).add(userID);
        }
    }

    public void addChunkToArray(int chunkNo) {
        if(usersBackingUp.size() > chunkNo){
            return;
        }
        else{
            usersBackingUp.add(new ArrayList<>());
        }
    }
}
