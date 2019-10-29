/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Marco Gamboa
 */
public class Statistics {
    private int wins;
    private int defeats;
    private int attacks;
    private int success;
    private int failed;
    private int giveup;
    private int draws;

    public Statistics() {
        this.wins=0;
        this.defeats=0;
        this.attacks=0;
        this.success=0;
        this.failed=0;
        this.giveup=0;
        this.draws=0;
    }

    public int getDraws() {
        return draws;
    }

    public void addDraw() {
        this.draws++;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailed() {
        return failed;
    }

    public int getGiveup() {
        return giveup;
    }

    public void addWin() {
        this.wins = wins;
    }

    public void addDefeat() {
        this.defeats++;
    }

    public void addAttack() {
        this.attacks++;
    }

    public void addSuccess() {
        this.success++;
    }

    public void addFailed() {
        this.failed++;
    }

    public void addGiveup() {
        this.giveup++;
    }

    @Override
    public String toString() {
        return "Wins:\t" + wins + 
                "\nLosses:\t" + defeats + 
                "\nAttacks:\t" + attacks + 
                "\nSuccess:\t" + success + 
                "\nFailed:\t" + failed + 
                "\nGiveup:\t" + giveup +
                "\nDraws:\t" + draws +"\n";
    }
    
    
}
