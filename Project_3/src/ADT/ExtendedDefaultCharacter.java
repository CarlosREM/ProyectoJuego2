/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import abstraction.AAppearance;
import abstraction.AWeapon;
import java.util.ArrayList;
import java.util.TreeMap;
import utils.Elements;

/**
 *
 * @author Marco Gamboa
 */
public class ExtendedDefaultCharacter extends DefaultCharacter {
    private Elements.Types type;

    public ExtendedDefaultCharacter(){
        
    }
    public ExtendedDefaultCharacter(Elements.Types type, String name, TreeMap<Integer, AAppearance> appearance, int currentHealthPoints, int maxHealthPoints, int hitsPerUnit, int level, int tiles, int unlockLevel, int cost, ArrayList<AWeapon> weapons, int coordinateX, int coordinateY) {
        super(name, appearance, currentHealthPoints, maxHealthPoints, hitsPerUnit, level, tiles, unlockLevel, cost, weapons, coordinateX, coordinateY);
        this.type = type;
    }

    public Elements.Types getType() {
        return type;
    }

    public void setType(Elements.Types type) {
        this.type = type;
    }
    
    
    public static class ExtendedDefaultCharacterBuilder extends DefaultCharacterBuilder{
        private Elements.Types builderType;
        public ExtendedDefaultCharacterBuilder setType(Elements.Types type){
            builderType = type;
            return this;
        }
        
        @Override 
        public ExtendedDefaultCharacter build(){
           DefaultCharacter temp = (DefaultCharacter) super.build();
           return new ExtendedDefaultCharacter(builderType, temp.getName(), temp.getAppearances(), temp.getCurrentHealthPoints(), temp.getMaxHealthPoints(), temp.getHitsPerUnit(), temp.getLevel(), temp.getTiles(),temp.getUnlockLevel(), temp.getCost(), temp.getWeapons(), temp.getCoordinateX(), temp.getCoordinateY());
        }
        
    }
}
