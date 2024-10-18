package StepDefinitions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateUser {
	
	@Getter @Setter 
	private int User_Id;	
	private String user_first_name;
	private int AlluserCount;
}
