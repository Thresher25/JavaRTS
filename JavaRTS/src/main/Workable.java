/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

public interface Workable {

    void doWork(double workDone);

    boolean isHarvestable();

    void setHarvestable(boolean set);

    String resourceType();

}
