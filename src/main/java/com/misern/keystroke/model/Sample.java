package com.misern.keystroke.model;


import lombok.*;

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
