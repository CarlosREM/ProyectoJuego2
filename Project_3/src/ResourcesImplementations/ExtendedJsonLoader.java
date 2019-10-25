/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcesImplementations;

import Json.JSONCharacterHolder;
import Json.JSONWeaponHolder;
import Json.JsonLoader;
import abstraction.ACharacter;
import abstraction.AWeapon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Murillo
 */
public class ExtendedJsonLoader {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public List<ACharacter> loadExtendedCharacters(){    
        InputStream defaultFile = getClass().getResourceAsStream("/resources/ExtendedCharacters.json");
        String json = null;

        try(Scanner scanner = new Scanner(defaultFile, StandardCharsets.UTF_8.name())){
            json = scanner.useDelimiter("\\A").next();
            defaultFile.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JSONCharacterHolder newHolder = mapper.readValue(json, new TypeReference<JSONCharacterHolder>(){});
            return newHolder.getCharacters();
            
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<AWeapon> loadExtendedWeapons(){
        InputStream defaultFile = getClass().getResourceAsStream("/resources/ExtendedWeapons.json");
        String json = null;

        try(Scanner scanner = new Scanner(defaultFile, StandardCharsets.UTF_8.name())){
            json = scanner.useDelimiter("\\A").next();
        }
        try {
            JSONWeaponHolder newHolder = mapper.readValue(json, new TypeReference<JSONWeaponHolder>(){});
            return newHolder.getWeapons();
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }            
    }
}
