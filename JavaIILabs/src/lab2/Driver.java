package lab2;

public class Driver {
	public static void main(String[] args) {
		ProductUtil productUtil = new ProductUtil();
		productUtil.insertProduct(0,"Fork", "Untensil used when eating",800,100);
		productUtil.insertProduct(1,"Spoon", "Untensil used when eating",800,100);
		productUtil.showProduct();
		productUtil.deleteProduct(0);
		productUtil.showProduct();
	}
}
