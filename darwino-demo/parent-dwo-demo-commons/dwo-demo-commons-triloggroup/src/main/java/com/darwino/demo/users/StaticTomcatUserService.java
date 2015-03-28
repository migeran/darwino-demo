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

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.ldif.LdifReader;

import com.darwino.commons.security.acl.User;
import com.darwino.commons.security.acl.UserException;
import com.darwino.commons.security.acl.UserService;
import com.darwino.commons.security.acl.impl.UserImpl;
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
public class StaticTomcatUserService implements UserService {
	
	private static final class StaticUser extends UserImpl {
		private String userId;
		private String email;
		StaticUser(String dn, String cn, String userId, String email) {
			super(dn, cn, null, null);
			this.userId = userId;
			this.email = email;
		}
		@Override
		public String getAttribute(String attrName) {
			if(ATTR_USERID.equals(attrName)) {
				return userId;
			}
			if(ATTR_EMAIL.equals(attrName)) {
				return email;
			}
			return super.getAttribute(attrName);
		}
	}
	
	private List<StaticUser> demoUsers_;

	@Override
	public User findUser(String id) throws UserException {
		if(id.startsWith("cn=")) {
			return findUserByDn(id);
		}
		if(id.indexOf('@')>=0) {
			return findUserByEmail(id);
		}
		return findUserById(id);
	}
	
	protected User findUserById(String id) throws UserException {
		int count = getUsers().size();
		for(int i=0; i<count; i++) {
			StaticUser u = getUsers().get(i);
			if(id.equalsIgnoreCase(u.userId)) {
				return u;
			}
		}
		return null;
	}
	
	protected User findUserByDn(String dn) throws UserException {
		int count = getUsers().size();
		for(int i=0; i<count; i++) {
			StaticUser u = getUsers().get(i);
			if(dn.equalsIgnoreCase(u.getDistinguishedName())) {
				return u;
			}
		}
		return null;
	}
	
	protected User findUserByEmail(String email) throws UserException {
		int count = getUsers().size();
		for(int i=0; i<count; i++) {
			StaticUser u = getUsers().get(i);
			if(email.equalsIgnoreCase(u.email)) {
				return u;
			}
		}
		return null;
	}
	
	private List<StaticUser> getUsers() {
		if(demoUsers_ == null) {
			demoUsers_ = new ArrayList<StaticUser>();
			try {
				InputStream is = DemoConfiguration.loadResource("/darwino_users.ldif", "/darwino_users_default.ldif");
				LdifReader namesReader = new LdifReader(is);
				for(LdifEntry entry : namesReader) {
					if(entry != null && entry.isLdifContent()) {
						String dn = entry.get("dn").toString();
						String cn = entry.get("cn").getString();
						String uid = entry.get("uid").getString();
						String email = entry.get("mail").getString();
						
						demoUsers_.add(new StaticUser(dn, cn, uid, email));
					}
				}
				namesReader.close();
				is.close();
			} catch(LdapException le) {
				throw new RuntimeException("Error loading users LDIF file", le);
			} catch(IOException ioe) {
				throw new RuntimeException("Error loading users LDIF file", ioe);
			}
		}
		return demoUsers_;
	}
}