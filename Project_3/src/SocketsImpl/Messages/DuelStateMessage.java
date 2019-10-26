/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import abstraction.AWeapon;
import commsapi.Message.AMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Diego Murillo
 */
public class DuelStateMessage extends AMessage{
    
    private boolean start = false;
    private List<WarriorCoreInfo> warriors;
    
    public DuelStateMessage(String oponent){
        super(oponent);
        warriors = new ArrayList();
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public List<WarriorCoreInfo> getWarriors() {
        return warriors;
    }

    public void setWarriors(List<ExtendedDefaultCharacter> warriors) {
        for(ExtendedDefaultCharacter c: warriors){
            WarriorCoreInfo w = new WarriorCoreInfo();
            w.setName(c.getName());
            TreeMap<Integer,DefaultCharacterAppearance> appearances = new TreeMap();
            for(Integer i : c.getAppearances().keySet()){
                appearances.put(i, (DefaultCharacterAppearance) c.getAppearance(i));
            }
            w.setAppearances(appearances);
            ArrayList<ExtendedDefaultWeapon> weapons = new ArrayList<>();
            for(AWeapon weapon : c.getWeapons()){
                weapons.add((ExtendedDefaultWeapon) weapon);
            }
            w.setWeapons(weapons);
            w.setCurrentHealthPoints(c.getCurrentHealthPoints());
            this.warriors.add(w);
        }
    }
    
    public class WarriorCoreInfo{
        public String name;
        public TreeMap<Integer,DefaultCharacterAppearance> appearances;
        public int currentHealthPoints = 0;
        public ArrayList<ExtendedDefaultWeapon> weapons;
        public WarriorCoreInfo(){
            
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TreeMap<Integer, DefaultCharacterAppearance> getAppearances() {
            return appearances;
        }

        public void setAppearances(TreeMap<Integer, DefaultCharacterAppearance> appearances) {
            this.appearances = appearances;
        }

        public int getCurrentHealthPoints() {
            return currentHealthPoints;
        }

        public void setCurrentHealthPoints(int currentHealthPoints) {
            this.currentHealthPoints = currentHealthPoints;
        }

        public ArrayList<ExtendedDefaultWeapon> getWeapons() {
            return weapons;
        }

        public void setWeapons(ArrayList<ExtendedDefaultWeapon> weapons) {
            this.weapons = weapons;
        }
        
        
    }
    
}
