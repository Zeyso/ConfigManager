import java.io.File;

public class JsonManager {
    String fileName;
    String dir;


    public JsonManager(String fileName){
        this.fileName = fileName;
    }
    public JsonManager(String fileName, String dir){
        this.fileName = fileName;
        this.dir = dir;
    }

    public JsonManager createFile(){
        //checking if fileName is null

        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        //checking if fileName ends with .json extension
        if (!fileName.endsWith(".json")) {
            //if fileName does not end with .json, add .json extension
            fileName = fileName + ".json";
        }
        if (dir != null){
            try {
                //create new directory
                File directory = new File(dir);
                if (!directory.exists()) {
                    directory.mkdirs();
                    System.out.println("Directory created: " + directory.getName());
                }
                if (!dir.endsWith("/")) {
                    dir = dir + "/";
                }
                //create new file
                File file = new File(dir + fileName);
                if (file.exists()) {
                    System.out.println("The file '" +fileName + "' already exists");
                } else {
                    file.createNewFile();
                    System.out.println("File created: " + file.getName());
                    return this;
                }
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
        } else {
            try {
                //create new file
                file.createNewFile();
                System.out.println("File created: " + file.getName());
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
        return this;
    }

    public JsonManager deleteFile(){
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }

        //checking if fileName is ending with .json extension
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }


        //checking if file exists
        if (!(dir == null)){
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }
            File file = new File(dir, fileName);
            File directory = new File(dir);
            if (!file.exists()) {
                System.out.println("The file '" +fileName + "' does not exist");
                return this;
            }
            try{
                file.delete();
                directory.delete();
            } catch (Exception e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
            return this;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file '" +fileName + "' does not exist");
            return this;
        }

        try{
            file.delete();
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    return this;
    }
}
