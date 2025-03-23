import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YmlManager {
    private String fileName;
    private String dir;


    public YmlManager(String fileName){
        this.fileName = fileName;
    }
    public YmlManager(String fileName, String dir){
        this.fileName = fileName;
        this.dir = dir;
    }
    private void checkYML(){
        if (!fileName.endsWith(".yml")) {
            fileName = fileName + ".yml";
        }
    }
    private void checkDir(){
        if (dir != null){
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }
        }
    }

    public boolean createFile(){
        //checking if fileName is null

        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        //checking if fileName ends with .yml extension
        checkYML();
        if (dir != null){
            try {
                //create new directory
                File directory = new File(dir);
                if (!directory.exists()) {
                    directory.mkdirs();
                    System.out.println("Directory created: " + directory.getName());
                }
                checkDir();
                //create new file
                File file = new File(dir + fileName);
                if (file.exists()) {
                    System.out.println("The file '" +fileName + "' already exists");
                    return false;
                }
                    file.createNewFile();
                    System.out.println("File created: " + file.getName());
                    return true;
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }


        //create new jsonFile
        File file = new File(fileName);
        //checking if file already exists
        if (file.exists()) {
            System.out.println("The file '" +fileName + "' already exists");
            return false;
        }
            try {
                //create new file
                file.createNewFile();
                System.out.println("File created: " + file.getName());
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }

        return true;
    }

    public boolean deleteFile(){
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }

        //checking if fileName is ending with .yml extension
        checkYML();


        //checking if file exists
        if (dir != null){
            checkDir();
            File file = new File(dir, fileName);
            File directory = new File(dir);
            if (!file.exists()) {
                System.out.println("The file '" +fileName + "' does not exist");
                return false;
            }
            try{
                file.delete();
                directory.delete();
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
            return true;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file '" +fileName + "' does not exist");
            return false;
        }

        try{
            file.delete();
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return true;
    }
    public boolean writeLine(String key, String value) throws IOException {
        checkYML();
        checkDir();
        File file = new File(fileName);
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;

        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                yamlMap = yaml.load(inputStream);
                if (yamlMap == null) {
                    yamlMap = new HashMap<>();
                }
            }
        } else {
            yamlMap = new HashMap<>();
        }

        if (yamlMap.containsKey(key)) {
            Object existingValue = yamlMap.get(key);
            if (existingValue instanceof List) {
                ((List<Object>) existingValue).add(value);
            } else {
                List<Object> newList = new ArrayList<>();
                newList.add(existingValue);
                newList.add(value);
                yamlMap.put(key, newList);
            }
        } else {
            yamlMap.put(key, value);
        }

        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(yamlMap, writer);
        }
        return true;
    }
    public String readLine(String key) throws IOException {
        LoaderOptions options = new LoaderOptions();
        Yaml yaml = new Yaml(new Constructor(Map.class, options));
        try (InputStream inputStream = new FileInputStream(fileName)) {
            Map<String, Object> yamlMap = yaml.load(inputStream);
            Object value = yamlMap.get(key);
            if (value != null) {
                return value.toString();
            } else {
                return "Key does not exist";
            }
        }
    }
    public boolean updateValue(String key, String newValue) throws IOException {
        checkYML();
        checkDir();

        File file = new File(fileName);
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;

        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                yamlMap = yaml.load(inputStream);
                if (yamlMap == null) {
                    yamlMap = new HashMap<>();
                }
            }
        } else {
            throw new IOException("File does not exist");
        }

        if (yamlMap.containsKey(key)) {
            yamlMap.put(key, newValue);
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(yamlMap, writer);
        }

        return true;
    }
    public boolean deleteLine(String key) throws IOException {
        checkYML();
        checkDir();
        File file = new File(fileName);
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;

        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                yamlMap = yaml.load(inputStream);
                if (yamlMap == null) {
                    yamlMap = new HashMap<>();
                }
            }
        } else {
            throw new IOException("File does not exist");
        }

        if (yamlMap.containsKey(key)) {
            yamlMap.remove(key);
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(yamlMap, writer);
        }
        return true;
    }
    public boolean deleteValue(String key) throws IOException {
        checkYML();
        checkDir();
        File file = new File(fileName);
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;

        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                yamlMap = yaml.load(inputStream);
                if (yamlMap == null) {
                    yamlMap = new HashMap<>();
                }
            }
        } else {
            throw new IOException("File does not exist");
        }

        if (yamlMap.containsKey(key)) {
            yamlMap.put(key, null);
            try (FileWriter writer = new FileWriter(file)) {
                yaml.dump(yamlMap, writer);
            }
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        return true;
    }
}