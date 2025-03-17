import java.io.File;

public class YmlManager {
    public YmlManager createFile(String fileName){
        //checking if fileName is null
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        //checking if fileName ends with .yml extension
        if (!fileName.endsWith(".yml")) {
            //if fileName does not end with .yml, add .yml extension
            fileName = fileName + ".yml";
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
    public YmlManager createFile(String fileName, String dir){
        //checking if fileName is null
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        //checking if dir is null
        if (dir == null) {
            throw new IllegalArgumentException("dir cannot be null");
        }
        //checking if dir starts with "/"
        if (!dir.startsWith("/")) {
            dir = "/" + dir;
        }
        //checking if fileName ends with .yml extension
        if (!fileName.endsWith(".yml")) {
            //if fileName does not end with .yml, add .yml extension
            fileName = fileName + ".yml";
        }
        //initializing new file and directory
        File directory = new File(dir);
        File file = new File(dir, fileName);
        //checking if directory already exists
        if (directory.exists()){
            System.out.println("Directory '" + dir + "' already exists");
            return this;
        }

        //checking if file already exists
        if (file.exists()) {
            System.out.println("The file '" +fileName + "' already exists");
            return this;
        }
        try {
            //create new directory
            directory.mkdir();
            System.out.println("Directory created: " + directory.getName());
        } catch (Exception e) {
            System.out.println("An error occurred while creating directory '" + directory.getName() + "'");
            e.printStackTrace();
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
}
