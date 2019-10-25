/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import ADT.CharacterPrototypeFactory;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import Controllers.DefaultPrototypeController;
import Json.JSONCharacterHolder;
import Json.JSONWeaponHolder;
import ResourcesImplementations.ExtendedPrototypeController;
import abstraction.ACharacter;
import abstraction.AWeapon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Elements;

/**
 *
 * @author Diego Murillo
 */
public class prueba {
    public static void main(String[] args){
        
        ExtendedPrototypeController.loadDefaultPrototypes();
        List<ACharacter> cs = CharacterPrototypeFactory.getAllCharacters();
        
        
                
        for(ACharacter c : cs){
            System.out.println(c.getName());
        }
                                   
    } 
}
