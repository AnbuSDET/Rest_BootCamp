package Pojos_User;

import StepDefinitions.CreateUser;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User_Pojos {

	private String  user_first_name;
	private String  user_last_name;
	private String  user_contact_number;  
	private String user_email_id;
	private  AddressPojo userAddress;
	
}
