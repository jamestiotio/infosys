import java.util.Date;

public class Account {

  private int id = 0;
  private double balance = 0;
  private static double annualInterestRate = 0;
  private Date dateCreated;

  Account() {
    this.id = id;
    this.balance = balance;
    Account.annualInterestRate = annualInterestRate;
    this.dateCreated = new Date();
  }

  Account(int inputID, double initialBalance) {
    this.id = inputID;
    this.balance = initialBalance;
    Account.annualInterestRate = annualInterestRate;
    this.dateCreated = new Date();
  }

  public void setID(int newID) {
    this.id = newID;
  }

  public void setBalance(double newBalance) {
    this.balance = newBalance;
  }

  public static void setAnnualInterestRate(double newAnnualInterestRate) {
    Account.annualInterestRate = newAnnualInterestRate;
  }

  public int getID() {
    return this.id;
  }

  public double getBalance() {
    return this.balance;
  }

  public static double getAnnualInterestRate() {
    return Account.annualInterestRate;
  }

  public Date getDateCreated() {
    return this.dateCreated;
  }

  public static double getMonthlyInterestRate() {
    return Account.getAnnualInterestRate() / 12;
  }

  public double getMonthlyInterest() {
    return this.getBalance() * Account.getMonthlyInterestRate() / 100;
  }

  public void withdraw(double withdrawAmount) {
    this.setBalance(this.getBalance() - withdrawAmount);
  }

  public void deposit(double depositAmount) {
    this.setBalance(this.getBalance() + depositAmount);
  }

}