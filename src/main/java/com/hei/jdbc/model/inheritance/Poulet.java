package com.hei.jdbc.model.inheritance;

public class Poulet extends EtreVivant {
  @Override
  public void vivre() {
    System.out.println("pondre un oeuf");
  }
}
