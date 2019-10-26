/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcesImplementations;

import ADT.CharacterPrototypeFactory;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import ADT.WeaponPrototypeFactory;
import Controllers.DefaultPrototypeController;
import Json.JsonLoader;
import abstraction.ACharacter;
import abstraction.AWeapon;
import java.util.ArrayList;

/**
 *
 * @author Diego Murillo
 */
public class ExtendedPrototypeController {
    
    public static void loadDefaultPrototypes(){
        ExtendedJsonLoader loader = new ExtendedJsonLoader();
        ArrayList<ExtendedDefaultCharacter> characters = (ArrayList<ExtendedDefaultCharacter>) loader.loadExtendedCharacters();
        ArrayList<ExtendedDefaultWeapon> weapons = (ArrayList<ExtendedDefaultWeapon>) loader.loadExtendedWeapons();
        for (ExtendedDefaultCharacter c:characters){
           
            CharacterPrototypeFactory.addPrototype(c.getName(), c);
        }
        for (ExtendedDefaultWeapon w:weapons){
            WeaponPrototypeFactory.addPrototype(w.getName(), w);
        }        
    }
}
