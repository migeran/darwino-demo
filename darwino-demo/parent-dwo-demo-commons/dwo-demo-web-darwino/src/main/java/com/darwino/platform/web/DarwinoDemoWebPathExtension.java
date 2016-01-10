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

package com.darwino.platform.web;

import java.util.List;

import com.darwino.commons.util.StringUtil;
import com.darwino.platform.resources.DarwinoWebLibrary;
import com.darwino.platform.resources.DarwinoWebLibrary.Module;
import com.darwino.platform.resources.DarwinoWebLibraryExtension;



/**
 * Extension for contributing aliases to a path rewriter .
 * 
 * @author priand
 */
public class DarwinoDemoWebPathExtension implements DarwinoWebLibraryExtension {
	
	public static final String SOURCE_BUNDLE = "com.darwino.demo.web.darwino";
	
	@Override
	public void contributeLibraries(List<DarwinoWebLibrary> libraries, String context) {
		if(StringUtil.equals(context, CTX_WEBLIBRARIES)) {
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Darwino", 
					"/darwino-ui/", 
					new Module("1.0.0", "/libs/darwino-ui/"))
			);
			
			/* 
			 * General purposes libraries
			 */
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "JQuery", 
					"/jquery/", 
					new Module("2.1.4", "/libs/jquery/2.1.4/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Code Mirror", 
					"/codemirror/", 
					new Module("5.6.0", "/libs/codemirror/5.6.0/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Moment JS", 
					"/moment/", 
					new Module("2.10.6", "/libs/moment/2.10.6/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Quill Rich Text Editor",
					"/quill/",
					new Module("0.20.0", "/libs/quill/0.20.0/"))
			);

			
			/* 
			 * Boostrap
			 */
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Boostrap 3", 
					"/bootstrap3/", 
					new Module("3.3.5", "/libs/bootstrap/3.3.5/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Jasny Boostrap", 
					"/jasny-bootstrap/", 
					new Module("3.1.3", "/libs/jasny-bootstrap/3.1.3/"))
			);
			
			/* 
			 * Angular JS
			 */
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "AngularJS", 
					"/angular/", 
					new Module("1.4.5", "/libs/angular/1.4.5/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "AngularJS Bootstrap", 
					"/angular-ui/bootstrap/", 
					new Module("0.13.4", "/libs/angular-ui/bootstrap/0.13.4/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "AngularJS ui-grid", 
					"/angular-ui/ui-grid/", 
					new Module("3.0.6", "/libs/angular-ui/ui-grid/3.0.6/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "AngularJS ui-codemirror", 
					"/angular-ui/ui-codemirror/", 
					new Module("0.3.0", "/libs/angular-ui/ui-codemirror/0.3.0/"))
			);
			// angular-quill and ngquill serve the same purpose of loading Quill in an Angular environment,
			// but have proved to have different bug characteristics in different layouts, so it's worth keeping
			// both around, at least for now. ngquill development appears more alive than angular-quill, so it
			// should be preferred as a first choice.
			
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "angular-quill",
					"/angular-quill/",
					new Module("0.9.2", "/libs/angular-quill/0.9.2/"))
			);
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "ngquill",
					"/ngquill/",
					new Module("1.0.12", "/libs/ngquill/1.0.12/"))
			);
			
			
			/* 
			 * Ionic
			 */
			libraries.add(new DarwinoWebLibrary(SOURCE_BUNDLE, "Ionic", 
					"/ionic/", 
					new Module("1.1.1", "/libs/ionic/1.1.1/",true),
					new Module("1.2.1", "/libs/ionic/1.2.1/"))
			);
		}
	}
}