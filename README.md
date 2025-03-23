# ConfigManager

## Table of Contents

 - [JsonExamples](#JsonExamples)
 - [YmlExamples](#YmlExamples)


## JsonExamples

### Example for creating Json File
        JsonManager jsonManager = new JsonManager("test");
        jsonManager.createFile();

### Example for creating Json File with directory
        jsonManager.createFile();
### Example for deleting Json Files
        jsonManager.deleteFile();
### Example for reading Json Files value
        jsonManager.readLine("key")
### Example for adding Json Files value
        jsonManager.writeLine("key", "value");
### Example for adding Json Files value
        jsonManager.updateValue("key", "value");
        
## YmlExamples
### Example for creating YML File
        YmlManager ymlManager = new YmlManager("test");
        ymlManager.createFile();

### Example for creating YML File with directory
        YmlManager ymlManager = new YmlManager("test", "YmlFiles" );
        ymlManager.createFile();
### Example for deleting YML File
        YmlManager ymlManager = new YmlManager("test", "YmlFiles" );
        ymlManager.deleteFile();
