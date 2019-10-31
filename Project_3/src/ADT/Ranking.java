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
public class Ranking {

    private String topic;
    private int wins;
    private int losses;
    private float average;

    public Ranking(String topic) {
        this.topic = topic;
        this.wins=0;
        this.losses=0;
        this.average = 0;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float calculate() {
        if (losses == 0) {
            average = wins;
        } else {
            average = (wins / losses);
        }
        return average;
    }

}
