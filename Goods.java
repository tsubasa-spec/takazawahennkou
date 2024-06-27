package bean;

public class Goods {

	private int goodsId;	//商品ID
	private String goodsName;	//商品名
	private int price;			//値段
	private int stock;			//在庫数
	private String shipping;	//発送日

	public Goods() {
		this.goodsId = 0;
		this.goodsName = null;
		this.price = 0;
		this.stock = 0;
		this.shipping = null;
	}
	
	public Goods(int goodsId, String goodsName, int price, int stock, String shipping) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.price = price;
		this.stock = stock;
		this.shipping = shipping;
	}
	
	public int getGoodsId() {
		return this.goodsId;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public String getShipping() {
		return this.shipping;
	}
	
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
}