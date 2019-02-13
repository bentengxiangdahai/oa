package org.beifeng.oa.utils;

import java.util.UUID;

public class KeyProvider {

	public static String getPrimaryKey(){
		return UUID.randomUUID().toString();
	}
}
