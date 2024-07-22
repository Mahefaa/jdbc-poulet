package com.hei.jdbc.model.inheritance;

public class LoupGaroup extends EtreVivant {
  @Override
  public void vivre() {
    System.out.println("manger villageois");
  }
}
