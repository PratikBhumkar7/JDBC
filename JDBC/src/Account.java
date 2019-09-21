
public class Account {
	private int accNo;
	private String custName;
	private double accBal;
	public Account(int accNo, String custName, double accBal) {
		super();
		this.accNo = accNo;
		this.custName = custName;
		this.accBal = accBal;
	}
	public int getAccNo() {
		return accNo;
	}
	public String getCustName() {
		return custName;
	}
	public double getAccBal() {
		return accBal;
	}
}
