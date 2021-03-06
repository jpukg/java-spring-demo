package joe.spring.springapp.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * The account entity class.
 * 
 * @author jsicree
 *
 * NOTE: To run on Oracle Express, use the @GeneratedValue annotation 
 * with the strategy = GenerationType.SEQUENCE and the @SequenceGenerator
 * annotation. For mySQL, comment those annotations out and use the 
 * @GeneratedValue annotation with strategy = GenerationType.IDENTITY.
 *
 */
@Entity(name="ACCOUNT")
public class Account {

	public enum AccountType {
		FREE, PREMIUM;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="springSeq")
//    @SequenceGenerator( name = "springSeq", sequenceName = "SPRING_SEQ")
	private Long id;

	@Column(name="ACCOUNT_TYPE")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;
	
	@ManyToOne
    @JoinColumn(name="CUSTOMER_ID")	
	private Customer customer;

	@SuppressWarnings("unused")
	private Account() {
		
	}

	public Account(final AccountType accountType, final String accountNumber, Customer customer) {
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", accountNumber=" + accountNumber 
				+ ", customer=" + customer + "]";
	}

}