package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CSVExample {

    public static void writeToFile(String csvFilePath,String[][] data){
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))){
            writer.writeNext(data[0]);
            for(int i=1; i< data.length;i++){
                writer.writeNext(data[i]);
            }
            System.out.println("Data Added");
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void readFromFile(String filePath){
        try(CSVReader reader = new CSVReader(new FileReader(filePath))){
            String[] header = reader.readNext();
            System.out.println(Arrays.toString(header));
            String[]  line;
            while((line = reader.readNext())!=null){
                for(String value: line){
                    System.out.print(value+ " ");
                }
                System.out.println(" ");
            }
        }catch (IOException exception){
            exception.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String csvFilePath = "employee.csv";

        String[][] data = {
                {"Name","Age","City"},
                {"Test1","25","Bangalore"},
                {"Test2","30","Mumbai"},
        };

        writeToFile(csvFilePath,data);

        readFromFile(csvFilePath);
    }
}
