package karikuncheva.dominosapp.model.products;


public class Drink extends Product {

	private  String description;

	public Drink(String name, double price, int imageId, String description) {
		super(ProductType.DRINK, name, price, imageId );
		this.description = description;
	}
	public Drink(String name, double price, String description ) {
		super(ProductType.DRINK, name, price);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() +  ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}

	
}

