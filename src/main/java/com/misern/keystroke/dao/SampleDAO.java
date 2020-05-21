package com.misern.keystroke.dao;

import com.misern.keystroke.model.Sample;

import java.sql.SQLException;
import java.util.List;

public interface SampleDAO {
	List<Sample> findByUserName(String userName) throws SQLException, ClassNotFoundException;
	List<Sample> findAll() throws java.sql.SQLException, ClassNotFoundException;
	void save(Sample sample) throws ClassNotFoundException, SQLException;
}
