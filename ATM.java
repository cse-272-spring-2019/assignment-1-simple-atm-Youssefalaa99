public class ATM {
    private int balance=10000;
    private final String CreditNumber= "12345";

    public int getBalance(){
        return balance;
    }

    public void Withdraw(int number){
        balance-=number;
    }

    public void Deposit(int number){
        balance+=number;
    }

    public String getCreditNumber(){
        return CreditNumber;
    }


}
