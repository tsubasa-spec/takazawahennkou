package bean;

public class Cart {

	//注文（カート）番号を格納する変数
	private String cartid;
	//ユーザーを格納する変数
	private String userid;
	//商品を格納する変数
	private int goodsid;
	//数量を格納する変数
	private int quantity;
	//発注日付を格納する変数
	private String orderdate;
	//入金日付を格納する変数
	private String paymentdate;
	//商品名
	private String goodsName;

	public Cart() {

		this.cartid = null;
		this.userid = null;
		this.goodsid = 0;
		this.quantity = 0;
		this.orderdate = null;
		this.paymentdate = null;
		this.goodsName = null;
	}

	public Cart(String cartid, String userid, int goodsid, int quantity, String orderdate, String paymentdate, String goodsName) {

		this.cartid = cartid;
		this.userid = userid;
		this.goodsid = goodsid;
		this.quantity = quantity;
		this.orderdate = orderdate;
		this.paymentdate = paymentdate;
		this.goodsName = goodsName;
	}


	//各フィールド変数のゲッターメソッド定義、セッターメソッド定義

	//注文（カート）番号
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	//ユーザー
	public String getUserid() {
		return userid;
	}
	public void setUserid(String string) {
		this.userid = string;
	}

	//商品
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	//数量
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//発注日
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	//入金日
	public String getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}
