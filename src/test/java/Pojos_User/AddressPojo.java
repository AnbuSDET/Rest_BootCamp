package Pojos_User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressPojo {	
	
	private String PlotNumber ;
	private String street;
	private String state;
	private String country;
	private String zipCode;
	
	
}
