package com.neosoft.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

	private int project_id;
	
	@NotEmpty(message = "Last name is required")
	private String projectName;
	
	
	@Min(message = "Duration should be required", value = 0)
	private Long duration; //= Duration.from(ChronoUnit.HOURS.getDuration());
}
