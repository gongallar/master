package org.ebrick.system.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.ebrick.system.dao.OrganisationDao;
import org.ebrick.system.model.Organisation;
import org.springframework.stereotype.Repository;

@Repository
public class OrganisationDaoImpl implements OrganisationDao  {
		
		  private static final Map<Integer,Organisation> orgMap =new HashMap<Integer,Organisation>();
		  
		    static {
		    	initOrganisation();
		    }   
		    
		    private static void  initOrganisation() {
		    	Organisation o = new Organisation();
				o.setId(1000);
				o.setName("Apple");
				o.setAddress("2/24");
				o.setCity("Hyderabad");
				o.setState("Telangana");
				o.setPincode("500486");
				o.setCountry("India");

				orgMap.put(o.getId(), o);
				
		    }
		    
		    public Organisation getOrganisation(int oid) {
				return orgMap.get(oid);
			}


}
