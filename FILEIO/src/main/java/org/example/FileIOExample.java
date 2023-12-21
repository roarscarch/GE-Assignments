package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIOExample {

    public static void writeToFile(String filePath, String data) throws IOException {
         BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));
            writer.write(data);
            writer.newLine();
            System.out.println("Data added to file:");
//        }catch (IOException exception){
//            exception.printStackTrace();
//        }
    }

    public static String readFromFile(String filePath){
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                content.append(line).append("\n");
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return content.toString();
    }
    public static void createFile(String filePath){
        try {
            Path path = Path.of(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File Created: " +filePath);
            }else{
                System.out.println("File Already exists: "+filePath);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        String filePath = "data.txt";

        createFile(filePath);

        writeToFile(filePath,"Hello, World");

        System.out.println("Content from the file: " +readFromFile(filePath));

    }
}
