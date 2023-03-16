package com.laohac.swp391spring2023.utils;

import com.laohac.swp391spring2023.model.SpecialDay;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class Utils {
	public static String getBaseURL(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}

	public static boolean isSpecialDay(LocalDate date){

		for (SpecialDay specialDay : SpecialDay.values()){
			if (specialDay.getDate().equals(date)) return true;
		}
		return false;
	}
}
