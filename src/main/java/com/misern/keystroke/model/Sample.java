package com.misern.keystroke.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sample {
	private Long id;
	private String userName;
	private String times;

	public Long [] getSampleTimes() {
		if (times == null || times.isEmpty()) {
			return null;
		}

		String [] rawTimes = times.split("\\|");
		Long [] result = new Long[rawTimes.length];

		for (int i = 0; i < rawTimes.length; i++) {
			result[i] = Long.parseLong(rawTimes[i]);
		}

		return result;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(userName);
		stringBuilder.append(": ");
		for (long l : getSampleTimes()) {
			stringBuilder.append(l).append(" ");
		}

		return stringBuilder.toString();
	}
}
