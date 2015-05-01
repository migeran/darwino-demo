/*!COPYRIGHT HEADER! - CONFIDENTIAL 
 *
 * Darwino Inc Confidential.
 *
 * (c) Copyright Darwino Inc 2014-2015.
 *
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.     
 */

package com.darwino.demo.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.darwino.commons.json.JsonException;
import com.darwino.commons.json.JsonJavaFactory;
import com.darwino.commons.json.JsonParser;
import com.darwino.commons.security.acl.User;
import com.darwino.commons.security.acl.UserException;
import com.darwino.commons.security.acl.UserService;
import com.darwino.commons.security.acl.impl.StaticUserService;
import com.darwino.commons.security.acl.impl.UserImpl;
import com.darwino.commons.security.acl.impl.StaticUserService.StaticUser;
import com.darwino.demo.config.DemoConfiguration;


/**
 * User directory service for the Darwino demo organizaion when used from a TOMCAT
 * environment without a true LDAP connection.
 * 
 * The tomcat-users.xml file should be filled accordingly:
	  <user password="floflo" roles="peuser" username="atinov"/>
	  <user password="floflo" roles="peuser" username="amass"/>
	  <user password="floflo" roles="peuser" username="aboucher"/>
	  <user password="floflo" roles="peuser" username="acalder"/>
	  <user password="floflo" roles="peuser" username="agardner"/>
	  <user password="floflo" roles="peuser" username="bchapot"/>
	  <user password="floflo" roles="peuser" username="blemercier"/>
	  <user password="floflo" roles="peuser" username="bchris"/>
	  <user password="floflo" roles="peuser" username="bbright"/>
	  <user password="floflo" roles="peuser" username="larmatti"/>
	  <user password="floflo" roles="peuser" username="lbros"/>
	  <user password="floflo" roles="peuser" username="mdavis"/>
	  <user password="floflo" roles="peuser" username="pcollins"/>
	  <user password="floflo" roles="peuser" username="rjordan"/>
 */
public class StaticTomcatUserService extends StaticUserService {
	
	public StaticTomcatUserService() {
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected List<StaticUser> createUsers() {
		List<StaticUser> users = new ArrayList<StaticUser>();
		try {
			InputStream is = DemoConfiguration.loadResource("/darwino_users.json", "/darwino_users_default.json");
			Map<String, Object> namesObj = (Map<String, Object>)JsonParser.fromJson(JsonJavaFactory.instance, is);
			is.close();
			for(Map<String, String> entry : (List<Map<String, String>>)namesObj.get("users")) {
				String dn = entry.get("dn");
				String cn = entry.get("cn");
				String uid = entry.get("uid");
				String email = entry.get("mail");
				
				users.add(new StaticUser(dn, cn, uid, email));
			}
		} catch(JsonException le) {
			throw new RuntimeException("Error loading users JSON file", le);
		} catch(IOException ioe) {
			throw new RuntimeException("Error loading users JSON file", ioe);
		}
		return users;
	}
}