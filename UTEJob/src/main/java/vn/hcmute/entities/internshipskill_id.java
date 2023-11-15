package vn.hcmute.entities;

import java.io.Serializable;

import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class internshipskill_id implements Serializable{
	private int internship_id;
	private int skill_id;
}
