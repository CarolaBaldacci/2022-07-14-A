package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polito.tdp.nyc.model.Adiacenza;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getAllBorghi(){
		String sql = "SELECT DISTINCT Borough FROM nyc_wifi_hotspot_locations";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("Borough"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}

	public List<String> getVertici(String b) {
		String sql = "SELECT DISTINCT NTACode FROM nyc_wifi_hotspot_locations"
				+ " WHERE Borough=?";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("NTACode"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Adiacenza> getArchi(String b){
		String sql="SELECT n1.NTACode, n2.NTACode, COUNT(DISTINCT n1.SSID) AS pesoN1,COUNT(DISTINCT n2.SSID) AS pesoN2"
				+ " FROM nyc_wifi_hotspot_locations n1,nyc_wifi_hotspot_locations n2"
				+ " WHERE n1.Borough=? AND n1.Borough=n2.Borough"
				+ " AND n1.NTACode!=n2.NTACode"
				+ " GROUP BY n1.NTACode,n2.NTACode";
		List<Adiacenza> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				double peso = res.getInt("pesoN1")+res.getInt("pesoN2");
				result.add(new Adiacenza(res.getString("n1.NTACode"),
				res.getString("n2.NTACode"),peso));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
}
