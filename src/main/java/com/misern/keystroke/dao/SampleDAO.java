package com.misern.keystroke.dao;

import com.misern.keystroke.model.Sample;

import java.util.List;

public interface SampleDAO {
	void save(Sample sample);
	List<Sample> findByUserName(String userName);
}
