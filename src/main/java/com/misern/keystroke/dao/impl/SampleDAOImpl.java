package com.misern.keystroke.dao.impl;

import com.misern.keystroke.dao.SampleDAO;
import com.misern.keystroke.model.Sample;
import com.misern.keystroke.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SampleDAOImpl implements SampleDAO {
	@Override
	public void save(Sample sample) throws ClassNotFoundException, SQLException {
		final String SQL = "INSERT INTO SAMPLES (times, username) values (?,?)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

		statement.getGeneratedKeys();
		statement.setString(1, sample.getTimes());
		statement.setString(2, sample.getUserName());
		statement.executeUpdate();
	}

	@Override
	public List<Sample> findByUserName(String userName) throws SQLException, ClassNotFoundException {
		final String SQL = "SELECT * FROM SAMPLES data WHERE data.username = ?";

		List<Sample> result = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(SQL);
		statement.setString(1, userName);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			result.add(Sample.builder()
					.id(rs.getLong(1))
					.times(rs.getString(2))
					.userName(rs.getString(3)).build());
		}

		return result;
	}

	@Override
	public java.util.List<Sample> findAll() throws SQLException, ClassNotFoundException {
		final String SQL = "SELECT * FROM SAMPLES data";

		List<Sample> result = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(SQL);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			result.add(Sample.builder()
					.id(rs.getLong(1))
					.times(rs.getString(2))
					.userName(rs.getString(3)).build());
		}

		return result;
	}
}
