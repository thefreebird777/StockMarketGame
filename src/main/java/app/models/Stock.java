package app.models;

//@Entity
//@Table(name="STOCK")
public class Stock {
    
        public Stock() {}
    
        public Stock(String ticker, double price) {
            this.ticker = ticker;
            this.price = price;
            this.shares = 0;
        }
        
        public Stock(String ticker, double price, int shares) {
            this.ticker = ticker;
            this.price = price;
            this.shares = shares;
        }

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public int id;
	
//	@Column(name="TICKER")
	public String ticker;
	
//	@Column(name="PRICE")
	public double price;
        
        public int shares;
	
	public String getTicker() { return ticker; }
	public void setTicker(String ticker) { this.ticker = ticker; }
	public double getValue() { return price; }
	public void setValue(double value) { this.price = value; }
        public int getShares() { return shares; }
	public void setShares(int shares) { this.shares = shares; }
}