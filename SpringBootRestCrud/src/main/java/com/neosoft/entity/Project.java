package com.neosoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="project_dtls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(initialValue = 1, name = "project_seq", sequenceName = "project_sequence")
    @Column(name = "project_id")
	private int project_id;
	
	@Column(name = "project_name")
	@NotEmpty(message = "Last name is required")
	private String projectName;
	
	@Column(name = "duration")
	@Min(message = "Duration should be required", value = 0)
	private Long duration; //= Duration.from(ChronoUnit.HOURS.getDuration());
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(foreignKey = @ForeignKey(name = "student_id"), name =
	 * "student_id") //@JsonBackReference private Student student;
	 */

}
