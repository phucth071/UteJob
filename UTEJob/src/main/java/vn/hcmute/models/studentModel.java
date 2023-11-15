package vn.hcmute.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentModel {
	private int student_id;
	private String first_name;
	private String last_name;
	private String major;
	private int user_id;
	
}
