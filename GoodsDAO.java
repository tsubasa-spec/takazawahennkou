package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Goods;

public class GoodsDAO {
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public ArrayList<Goods> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<Goods> goodsList = new ArrayList<>();
		try {
			String sql = "SELECT goods_id,goods,price,stock,shipping FROM goods_info ORDER BY goods_id";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goods_id"));
				goods.setGoodsName(rs.getString("goods"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setShipping(rs.getString("shipping"));
				goodsList.add(goods);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
		return goodsList;
	}

	public void insert(Goods goods) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "INSERT INTO goods_info "
					+ "VALUES(" + goods.getGoodsId() + ",'"
					+ goods.getGoodsName() + "',"
					+ goods.getPrice() + ","
					+ goods.getStock() + ","
					+ goods.getShipping() + ")";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
	}

	public Goods selectById(String goodsId) {
		Connection con = null;
		Statement smt = null;
		Goods goods = new Goods();
		try {
			String sql = "SELECT goods_id,goods,price,stock,shipping FROM goods_info WHERE goods_id=" + goodsId + "";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				goods.setGoodsId(rs.getInt("goods_id"));
				goods.setGoodsName(rs.getString("goods"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setShipping(rs.getString("shipping"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
		return goods;
	}

	public void delete(String goodsId) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "DELETE FROM goods_info WHERE goods_id = '" + goodsId + "'";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
	}

	public void update(Goods goods) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "UPDATE goods_info SET "
					+ "goods='" + goods.getGoodsName()
					+ "',price=" + goods.getPrice()
					+ "',stock=" + goods.getStock()
					+ "',shipping=" + goods.getShipping()
					+ " WHERE goods_id='" + goods.getGoodsId() + "'";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
	}

	public ArrayList<Goods> search(String goodsId, String goodsName, int price, int stock,String shipping) {
		Connection con = null;
		Statement smt = null;
		ArrayList<Goods> goodsList = new ArrayList<>();
		try {
			String sql = "SELECT goods_id,goods,price,stock,shipping FROM goods_info " +
					"WHERE goods_id LIKE '%" + goodsId + "%'"
					+ " AND goods LIKE '%" + goodsName + "%'"
					+ " AND price LIKE '%" + price + "%'"
					+ " AND stock LIKE '%" + stock + "%'"
					+ " AND shipping LIKE '%" + shipping + "%'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setShipping(rs.getString("shipping"));
				goodsList.add(goods);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ignore) {
			}
		}
		return goodsList;
	}
}