package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonExample {
    public static void writeJsonFile(String filePath,Person person){
        Gson gson =new  GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(filePath)){
            gson.toJson(person,writer);
            System.out.println("Data Added");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Person readFromFile(String filePath){
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)){
            return gson.fromJson(reader, Person.class);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Person person = new Person("Sample",25);

        String filePath = "person.json";

        writeJsonFile(filePath,person);
        System.out.println("Data from Json file:\n" +readFromFile(filePath).toString());

    }
}
