

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonManager {
    private String fileName;
    private String dir;
    private String value = null;


    public JsonManager(String fileName){
        this.fileName = fileName;
    }
    public JsonManager(String fileName, String dir){
        this.fileName = fileName;
        this.dir = dir;
    }

    private void checkJson(){
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }
    }
    private void checkDir(){
        if (dir != null){
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }
        }
    }

    public JsonManager createFile(){
        //checking if fileName is null

        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        //checking if fileName ends with .json extension
        checkJson();
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
                    return null;
                }

                file.createNewFile();
                System.out.println("File created: " + file.getName());
                return this;

            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }


        //create new jsonFile
        File file = new File(fileName);
        //checking if file already exists
        if (file.exists()) {
            return null;
        }
            try {
                //create new file
                file.createNewFile();
                System.out.println("File created: " + file.getName());
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();

        }
        return this;
    }

    public JsonManager deleteFile(){
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }

        //checking if fileName is ending with .json extension
        checkJson();


        //checking if file exists
        if (!(dir == null)){
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }
            File file = new File(dir, fileName);
            File directory = new File(dir);
            if (!file.exists()) {
                System.out.println("The file '" +fileName + "' does not exist");
                return null;
            }
            try{
                file.delete();
                directory.delete();
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
            return null;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file '" +fileName + "' does not exist");
            return null;
        }

        try{
            file.delete();
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return this;
    }


    public String readLine(String key) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(fileName));
        JsonNode valueNode = jsonNode.get(key);
        if (valueNode != null) {
            return valueNode.asText();
        } else {
            return "Key does not exist";
        }
    }


    public JsonManager writeLine(String key, String value) throws IOException {
        checkJson();
        checkDir();

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root;

        if (file.exists()) {
            root = (ObjectNode) mapper.readTree(file);
        } else {
            root = mapper.createObjectNode();
        }

        root.put(key, value);
        mapper.writeValue(file, root);

        return this;
    }

    public JsonManager updateValue(String key, String newValue) throws IOException {
        checkJson();
        checkDir();

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root;

        if (file.exists()) {
            root = (ObjectNode) mapper.readTree(file);
        } else {
            throw new IOException("File does not exist");
        }

        if (root.has(key)) {
            root.put(key, newValue);
            mapper.writeValue(file, root);
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        return this;
    }
    public JsonManager deleteLine(String key) throws IOException {
        checkJson();
        checkDir();

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root;

        if (file.exists()) {
            root = (ObjectNode) mapper.readTree(file);
        } else {
            throw new IOException("File does not exist");
        }

        if (root.has(key)) {
            root.remove(key);
            mapper.writeValue(file, root);
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        return this;
    }
    public JsonManager deleteValue(String key) throws IOException {
        checkJson();
        checkDir();

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root;

        if (file.exists()) {
            root = (ObjectNode) mapper.readTree(file);
        } else {
            throw new IOException("File does not exist");
        }

        if (root.has(key)) {
            root.putNull(key);
            mapper.writeValue(file, root);
        } else {
            throw new IllegalArgumentException("Key does not exist");
        }

        return this;
    }
    public JsonManager writeLine(String key, String[] values) throws IOException {
        checkJson();
        checkDir();

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root;

        if (file.exists()) {
            root = (ObjectNode) mapper.readTree(file);
        } else {
            root = mapper.createObjectNode();
        }

        ArrayNode arrayNode;
        if (root.has(key)) {
            JsonNode existingNode = root.get(key);
            if (existingNode.isArray()) {
                arrayNode = (ArrayNode) existingNode;
            } else {
                arrayNode = mapper.createArrayNode();
                arrayNode.add(existingNode);
            }
        } else {
            arrayNode = mapper.createArrayNode();
        }

        for (String value : values) {
            arrayNode.add(value);
        }

        root.set(key, arrayNode);
        mapper.writeValue(file, root);

        return this;
    }
}
