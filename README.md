# ConfigManager

## Table of Contents
 - [MavenSetup](#Maven)
 - [JsonExamples](#JsonExamples)
 - [YmlExamples](#YmlExamples)

## Maven
       
    <repositories>
        <!-- Maven Central Repository -->
        <repository>
            <id>central</id>
            <url>https://repo.maven.apache.org/maven2</url>
        </repository>

    </repositories>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.16.1</version>
        </dependency>
        <dependency>
            <groupId>org.yaml</groupId>
            <artifactId>snakeyaml</artifactId>
            <version>2.0</version>
        </dependency>
    </dependencies>

## JsonExamples

### Example for creating Json File
        JsonManager jsonManager = new JsonManager("test");
        jsonManager.createFile();

### Example for creating Json File with directory
        jsonManager.createFile("file", "directory");
### Example for deleting Json Files
        jsonManager.deleteFile();
### Example for reading Json Files value
        jsonManager.readLine("key")
### Example for adding Json Files value
        jsonManager.writeLine("key", "value");
### Example for updating Json Files value
        jsonManager.updateValue("key", "value");
        
## YmlExamples
### Example for creating YML File
        YmlManager ymlManager = new YmlManager("test");
        ymlManager.createFile();

### Example for creating YML File with directory
        ymlManager.createFile("file", "directory");
### Example for deleting YML File
        ymlManager.deleteFile();
### Example for reading YML Files value
        ymlManager.readLine("key")
### Example for adding YML Files value
        ymlManager.writeLine("key", "value");
### Example for updating YML Files value
        ymlManager.updateValue("key", "value");
