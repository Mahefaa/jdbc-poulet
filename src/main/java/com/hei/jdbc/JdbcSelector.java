package com.hei.jdbc;

import com.hei.jdbc.model.Account;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// java program runs
// JdbcSelector {main, getConnection}
public class JdbcSelector {
  public static void main(String[] args) {
    // appel de vérification
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    Driver driver = new org.postgresql.Driver();
    try {
      DriverManager.registerDriver(driver);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    Connection connection;
    try {
      connection =
          DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/bank", "postgres", "postgres");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    Statement statement;
    try {
      statement = getStatement(connection);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    insertTwoValues(statement);
    updateTwoValues(statement);
    ResultSet rs;
    try {
      rs =
          statement.executeQuery(
              """
						select * from "account";
						""");
      /*
        id  | solde
      ------+-------
       id_2 |     0
       id_1 |   100


       [rs.cursorValue(): null, 0:{id_2, 0}, 1:{id_1, 100}]
       rs.next();
       [rs.cursorValue(): 0, 0:{id_2, 0}, 1:{id_1, 100}]
       */
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    List<Account> accounts = fetchAllAccountsFromDatabase(rs);
    System.out.println(accounts);
  }

  private static List<Account> fetchAllAccountsFromDatabase(ResultSet rs) {
    List<Account> accounts = new ArrayList<>();
    try {
      while (rs.next()) {
        String id = rs.getString("id");
        double solde = rs.getDouble("solde");
        Account account = new Account();
        account.setId(id);
        account.setSolde((int) solde);
        accounts.add(account);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return accounts;
  }

  private static void insertTwoValues(Statement statement) {
    try {
      int result =
          statement.executeUpdate(
              """
				insert into "account" ("id", "solde") values ('id_1', 0.0), ('id_2', 0.0);
				""");
      System.out.println("nanao insert za");
      assert (result == 2) : ("tsy nandeha ilay insert leka");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static void updateTwoValues(Statement statement) {
    try {
      int result =
          statement.executeUpdate(
              """
						update "account" set solde = 100.0 where id = 'id_1';
						""");
      System.out.println("update mandeha ewa");
      assert (result == 2) : ("tsy nandeha ilay update leka");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Statement getStatement(Connection connection) throws SQLException {
    return connection.createStatement();
  }

  // Account { id , solde};
}
