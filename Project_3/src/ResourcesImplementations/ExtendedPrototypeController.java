/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcesImplementations;

import ADT.CharacterPrototypeFactory;
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
        ArrayList<ACharacter> characters = (ArrayList<ACharacter>) loader.loadExtendedCharacters();
        ArrayList<AWeapon> weapons = (ArrayList<AWeapon>) loader.loadExtendedWeapons();
        for (ACharacter c:characters){
            CharacterPrototypeFactory.addPrototype(c.getName(), c);
        }
        for (AWeapon w:weapons){
            WeaponPrototypeFactory.addPrototype(w.getName(), w);
        }        
    }
}
