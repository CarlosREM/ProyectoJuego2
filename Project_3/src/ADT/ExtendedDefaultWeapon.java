/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import abstraction.AAppearance;
import abstraction.ACharacter;
import abstraction.IPrototype;
import com.google.gson.internal.bind.TypeAdapters;
import java.util.TreeMap;
import utils.Elements;

/**
 *
 * @author Marco Gamboa
 */
public class ExtendedDefaultWeapon extends DefaultWeapon {

    private Elements elements;
    private boolean available;
    private int actualAttack;
    private boolean isKilled;

    public ExtendedDefaultWeapon() {
        super();
    }

    public ExtendedDefaultWeapon(String name, int range, int damage, int level, int areaOfEffect, int hitPerUnit, TreeMap<Integer, AAppearance> appearances, int unlockLevel) {
        super(name, range, damage, level, areaOfEffect, hitPerUnit, appearances, unlockLevel);
        elements = new Elements();
        available = true;
        actualAttack = 0;
        isKilled = false;

    }

    public int getActualAttack() {
        return this.actualAttack;
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

    public boolean isIsKilled() {
        return isKilled;
    }

    public void setIsKilled(boolean isKilled) {
        this.isKilled = isKilled;
    }

    @Override
    public void use(ACharacter character) {
        ExtendedDefaultCharacter eCharacter = (ExtendedDefaultCharacter) character;
        int pos = eCharacter.getType().ordinal();
        int pre = character.getCurrentHealthPoints();
        character.takeDamage(elements.getPercentages()[pos]);
        actualAttack = elements.getPercentages()[pos];
        if (character.getCurrentHealthPoints() < 0) {
            character.setCurrentHealthPoints(0);
        }

        isKilled = ((pre != 0) && (character.getCurrentHealthPoints() == 0));

    }

    @Override
    public IPrototype deepClone() {
        TreeMap<Integer, AAppearance> newAppearances = new TreeMap<>();

        for (Integer i : getAppearances().keySet()) {
            newAppearances.put(i, (AAppearance) getAppearances().get(i).deepClone());
        }
        return new ExtendedDefaultWeapon(getName(), getRange(), getDamage(), getLevel(), getAreaOfEffect(), getHitPerUnit(), newAppearances, getUnlockLevel());
    }

    public static class ExtendedDefaultWeaponBuilder extends DefaultWeaponBuilder {

        @Override
        public ExtendedDefaultWeapon build() {
            DefaultWeapon temp = (DefaultWeapon) super.build();
            return new ExtendedDefaultWeapon(temp.getName(), temp.getRange(), temp.getDamage(), temp.getLevel(), temp.getAreaOfEffect(), temp.getHitPerUnit(), temp.getAppearances(), temp.getUnlockLevel());
        }
    }

}
