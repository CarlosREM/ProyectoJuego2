/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcesImplementations;

import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
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
import resourcesImp.JSONExtendedCharacterHolder;
import resourcesImp.JSONExtendedWeaponHolder;

/**
 *
 * @author Diego Murillo
 */
public class ExtendedJsonLoader {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public List<ExtendedDefaultCharacter> loadExtendedCharacters(){    
        InputStream defaultFile = getClass().getResourceAsStream("/resourcesImp/ExtendedCharacters.json");
        String json = null;

        try(Scanner scanner = new Scanner(defaultFile, StandardCharsets.UTF_8.name())){
            json = scanner.useDelimiter("\\A").next();
            defaultFile.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JSONExtendedCharacterHolder newHolder = mapper.readValue(json, new TypeReference<JSONExtendedCharacterHolder>(){});
            return newHolder.getCharacters();
            
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<ExtendedDefaultWeapon> loadExtendedWeapons(){
        InputStream defaultFile = getClass().getResourceAsStream("/resourcesImp/ExtendedWeapons.json");
        String json = null;

        try(Scanner scanner = new Scanner(defaultFile, StandardCharsets.UTF_8.name())){
            json = scanner.useDelimiter("\\A").next();
        }
        try {
            JSONExtendedWeaponHolder newHolder = mapper.readValue(json, new TypeReference<JSONExtendedWeaponHolder>(){});
            return newHolder.getWeapons();
        } catch (IOException ex) {
            Logger.getLogger(JsonLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }            
    }
}
