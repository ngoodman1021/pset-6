import java.text.NumberFormat;

public class BankAccount {
    
	private static long prevAccountNo = 100000000L;
	
    private int pin;
    private long accountNo;
    private double balance;
    private User accountHolder;
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    // Refer to the Simple ATM tutorial to fill in the details of this class. //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    /*
     * Formats the account balance in preparation to be written to the data file.
     * 
     * @return a fixed-width string in line with the data file specifications.
     */
    
    private String formatBalance() {
        return String.format("%1$15s", balance);
    }
    
    /*
     * Converts this BankAccount object to a string of text in preparation to
     * be written to the data file.
     * 
     * @return a string of text formatted for the data file
     */
    
    @Override
    public String toString() {
        return String.valueOf(accountNo) +
            String.valueOf(pin) +
            accountHolder.serialize() +
            formatBalance();
    }
    
    public BankAccount(int pin, User accountHolder) {
    	this.pin = pin;
    	this.accountHolder = accountHolder;
    }
    
    public BankAccount(int pin, long accountNo, User accountHolder) {
    	this.pin = pin;
    	this.accountNo = ++BankAccount.prevAccountNo;
    	this.accountHolder = accountHolder;
    }
    
    public BankAccount(int pin, long accountNo, double balance, User accountHolder) {
    	this.pin = pin;
    	this.accountNo = ++BankAccount.prevAccountNo;
    	this.balance = balance;
    	this.accountHolder = accountHolder;
    }
    

    
    public int getPin() {
    	return pin;
    }
    
    public long getAccountNo() {
    	return accountNo;
    }
    
    
    public User getAccountHolder() {
    	return accountHolder;
    }
    
    public String getBalance() {
    	NumberFormat currency = NumberFormat.getCurrencyInstance();
    	
    	return currency.format(balance);
    }
    
    public int deposit(double amount) {
    	if(amount <= 0) {
    		return ATM.INVALID;
    	} else if (balance + amount >= ATM.INVALIDTOP) {
    		return ATM.INVALIDMAX;
    	} else {
    		balance = balance + amount;
    	}
    	return ATM.SUCCESS;
    }
    
    public int transfer(double amount, String wOD) {
    	if(amount <= 0) {
    		return ATM.INVALID;
    	} else if (balance + amount >= ATM.INVALIDTOP) {
    		return ATM.INVALIDMAX;
    	} else if (amount > balance) {
    		return ATM.INSUFFICIENT;
    	} else if (amount - balance < 0) {
    		return ATM.INSUFFICIENT;
    	} else if (wOD.equals("deposit")){
    		balance = balance + amount;
    	} else if (wOD.equals("withdraw")) {
    		balance = balance - amount;
    	}
    	return ATM.SUCCESS;
    }
   
    public int withdraw(double amount) {
    	if(amount <= 0) {
    		return ATM.INVALID;
    	} else if (amount > balance){
    		return ATM.INSUFFICIENT;
    	} else {
    		balance = balance - amount;
    	}
    	return ATM.SUCCESS;
    }
}
