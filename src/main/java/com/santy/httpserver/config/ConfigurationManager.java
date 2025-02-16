package com.santy.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.santy.httpserver.utils.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    /**
     * To make the manager static
     */
    private static ConfigurationManager serverConfigurationManager;
    /**
     * Return type for the loaded configuration is of configuration
     */
    private static Configuration serverCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (serverConfigurationManager == null) {
            serverConfigurationManager = new ConfigurationManager();
        }
        return serverConfigurationManager;
    }

    /*
    * Used for loading conf file from provided directory
    * */
    public void loadConfigurationFile(String configFilePath) throws IOException {
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(configFilePath);
        }catch(FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try{
            while(( i = fileReader.read()) != -1){
                buffer.append((char)i);
            }
        }catch(IOException e){
            throw new HttpConfigurationException(e);
        }

        JsonNode node = null;
        try {
            node = Json.parse(buffer.toString());
        }catch(IOException e){
            throw new HttpConfigurationException("Error parsing configuration file", e);
        }
        try {
            serverCurrentConfiguration = Json.fromJson(node,Configuration.class);
        }catch (JsonProcessingException e){
            throw new HttpConfigurationException("Error parsing configuration file,internal", e);
        }
    }

    /**
     * Returns the current configuration which is loaded
     */
    public Configuration getCurrentConfiguration() {
    if(serverCurrentConfiguration == null) {
        throw new HttpConfigurationException("No current configuration set for server");
    }
    return serverCurrentConfiguration;
    }
}
