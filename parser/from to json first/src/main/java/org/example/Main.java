package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);

        String json = listToJson(list);

        writeString(json, "data.json");

        String xmlFileName = "data.xml";
        List<Employee> xmlList = parseXML(xmlFileName);
        String xmlJson = listToJson(xmlList);
        writeString(xmlJson, "data2.json");

    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            return csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    private static void writeString(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(fileName);
            NodeList employeeNodes = doc.getElementsByTagName("employee");

            for (int i = 0; i < employeeNodes.getLength(); i++) {
                org.w3c.dom.Element employee = (org.w3c.dom.Element) employeeNodes.item(i);

                employees.add(new Employee(
                        Long.parseLong(employee.getElementsByTagName("id").item(0).getTextContent()),
                        employee.getElementsByTagName("firstName").item(0).getTextContent(),
                        employee.getElementsByTagName("lastName").item(0).getTextContent(),
                        employee.getElementsByTagName("country").item(0).getTextContent(),
                        Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent())
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}