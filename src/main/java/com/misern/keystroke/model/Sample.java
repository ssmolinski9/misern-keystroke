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
	private Double lastTime;
	private Double measuredTime;
	private String userName;
}
