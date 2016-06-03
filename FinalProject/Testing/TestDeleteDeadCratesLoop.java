package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.CrateList;
import junit.framework.TestCase;

/**
 * Tests the Enemy objects.
 * @author Hristo Stoytchev
 */

public class TestDeleteDeadCratesLoop extends TestCase {

    public void testDeleteEmpty() {
        CrateList crateList = new CrateList();
        crateList.deleteDeadCrates();
        assertEquals(0, crateList.size());
    }

    public void testDeleteOne() {
        CrateList crateList = new CrateList();
        Crate crate = new Crate(0);
        crate.setHealth(0);
        crateList.add(crate);
        crateList.deleteDeadCrates();
        assertEquals(0, crateList.size());
    }

    public void testDeleteTwo() {
        CrateList crateList = new CrateList();
        Crate crate = new Crate(0);
        crate.setHealth(0);
        Crate crate1 = new Crate(0);
        crateList.add(crate);
        crateList.add(crate1);
        crateList.deleteDeadCrates();
        assertEquals(1, crateList.size());
    }

    public void testDeleteTen() {
        CrateList crateList = new CrateList();
        Crate crate0 = new Crate(0);
        Crate crate1 = new Crate(0);
        Crate crate2 = new Crate(0);
        Crate crate3 = new Crate(0);
        Crate crate4 = new Crate(0);
        Crate crate5 = new Crate(0);
        Crate crate6 = new Crate(0);
        Crate crate7 = new Crate(0);
        Crate crate8 = new Crate(0);
        Crate crate9 = new Crate(0);
        crateList.add(crate0);
        crateList.add(crate1);
        crateList.add(crate2);
        crateList.add(crate3);
        crateList.add(crate4);
        crateList.add(crate5);
        crateList.add(crate6);
        crateList.add(crate7);
        crateList.add(crate8);
        crateList.add(crate9);
        crateList.deleteDeadCrates();
        assertEquals(10, crateList.size());
    }
}
