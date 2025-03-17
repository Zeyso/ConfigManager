# ConfigManager

## Table of Contents

 - [JsonExamples](#JsonExamples)
 - [YmlExamples](#YmlExamples)


## JsonExamples

### Example for creating Json File
        JsonManager jsonManager = new JsonManager("test");
        jsonManager.createFile();

### Example for creating Json File with directory
        JsonManager jsonManager = new JsonManager("test", "JsonFiles" );
        jsonManager.createFile();
### Example for deleting Json Files
        JsonManager jsonManager = new JsonManager("test", "JsonFiles" );
        jsonManager.deleteFile();
        
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
