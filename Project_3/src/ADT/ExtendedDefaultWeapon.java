/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;


import abstraction.AAppearance;
import abstraction.ACharacter;
import java.util.TreeMap;
import utils.Elements;

/**
 *
 * @author Marco Gamboa
 */
public class ExtendedDefaultWeapon extends DefaultWeapon{
    private Elements elements;
    private boolean available;

    public ExtendedDefaultWeapon(String name, int range, int damage, int level, int areaOfEffect, int hitPerUnit, TreeMap<Integer, AAppearance> appearances, int unlockLevel) {
        super(name, range, damage, level, areaOfEffect, hitPerUnit, appearances, unlockLevel);
        elements = new Elements(); 
        available= true;
    }



    public Elements getElements() {
        return elements;
    }

    public void setElements(Elements elements) {
        this.elements = elements;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
    @Override
    public void use(ACharacter character){
      ExtendedDefaultCharacter eCharacter = (ExtendedDefaultCharacter) character;
      int pos = eCharacter.getType().ordinal();
      character.takeDamage(elements.getPercentages()[pos]);
    } 
    
    public static class ExtendedDefaultWeaponBuilder extends DefaultWeaponBuilder{
        @Override
        public ExtendedDefaultWeapon build(){
              DefaultWeapon temp = (DefaultWeapon) super.build();
              return new ExtendedDefaultWeapon(temp.getName(),temp.getRange(),temp.getDamage(),temp.getLevel(),temp.getAreaOfEffect(),temp.getHitPerUnit(),temp.getAppearances(),temp.getUnlockLevel());
        }
    }    
    
}
