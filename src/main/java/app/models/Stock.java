package app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STOCK")
public class Stock implements DefaultInterface {
    
        public Stock() {}
    
        public Stock(int id, String ticker, double price) {
            this.id = id;
            this.ticker = ticker;
            this.price = price;
        }
        
        public Stock(String ticker, double price) {
            this.ticker = ticker;
            this.price = price;
        }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="ID")
	public int id;
	
	@Column(name="TICKER")
	public String ticker;
	
	@Column(name="PRICE")
	public double price;
	
	public String getTicker() { return ticker; }
	public void setTicker(String ticker) { this.ticker = ticker; }
	public double getValue() { return price; }
	public void setValue(double value) { this.price = value; }
}