package vn.hcmute.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class reviewModel {
	private int review_id;
	private String comment;
	private double rating;
	private int application_id;
}
