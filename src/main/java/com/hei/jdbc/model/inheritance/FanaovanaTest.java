package com.hei.jdbc.model.inheritance;

public class FanaovanaTest {
  public static void main(String[] args) {
    Poulet poulet = new Poulet();

    EtreVivant poulet1 = new Poulet();
    EtreVivant poulet2 = (EtreVivant) new Poulet();
    EtreVivant loupGaroup = (EtreVivant) new LoupGaroup();

    // Poulet, LoupGaroup, Villageois sont castable en tant que la classe mère;
    EtreVivant poulet3 = (EtreVivant) poulet;
    // tous les poulets sont des êtres vivants
    // mais tous les êtres vivants ne sont pas des poulets;
  }
}
