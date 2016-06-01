package com.lifemm.game;

import java.util.List;
import java.util.LinkedList;


public class CrateList {
    private List<Crate> crates;

    public CrateList() {
        crates = new LinkedList<Crate>();
    }

    public void add(Crate crate) {
        crates.add(crate);
    }

    public void deleteDeadCrates() {
        for (int i = 0; i < crates.size(); i++) {
            if (crates.get(i).getHealth() <= 0) {
                crates.remove(crates.get(i));
            }
        }
    }

    public void remove(Crate crate) {
        crates.remove(crate);
    }

    public Crate get(int index) {
        return crates.get(index);
    }

    public List<Crate> getIterable() {
        return crates;
    }

    public int size() {
        return crates.size();
    }

}