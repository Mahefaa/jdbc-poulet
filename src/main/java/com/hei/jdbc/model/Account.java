package com.hei.jdbc.model;

public final class Account {
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Account{");
    sb.append("id='").append(id).append('\'');
    sb.append(", solde=").append(solde);
    sb.append('}');
    return sb.toString();
  }

  private String id;
  private int solde;

  public int getSolde() {

    return solde;
  }

  public void setSolde(int solde) {
    this.solde = solde;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
