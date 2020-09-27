public class CheckingAccount extends Account {

  CheckingAccount() {
    super();
  }

  CheckingAccount(int inputID, double inputBalance) {
    super(inputID, inputBalance);
  }

  public void withdraw(double withdrawAmount) {
    if (withdrawAmount - getBalance() > 5000) {
      System.out.println("over limit");
      return;
    }

    this.setBalance(this.getBalance() - withdrawAmount);
  }

}
