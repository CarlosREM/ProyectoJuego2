/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Comparator;

/**
 *
 * @author Marco Gamboa
 */
public class SortByAverage implements Comparator<Ranking> 
{ 

    @Override
    public int compare(Ranking o1, Ranking o2) {
        return Float.compare(o2.calculate(), o1.calculate());
    }
} 
