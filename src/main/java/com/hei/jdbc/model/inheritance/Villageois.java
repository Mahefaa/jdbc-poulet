package com.hei.jdbc.model.inheritance;

public class Villageois extends EtreVivant {
  @Override
  public void vivre() {
    System.out.println("manger, s'amuser, dodo");
  }
}
