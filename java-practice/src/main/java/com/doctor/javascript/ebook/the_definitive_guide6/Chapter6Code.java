package com.doctor.javascript.ebook.the_definitive_guide6;

import java.io.Reader;

import javax.script.ScriptEngine;

import com.doctor.javascript.JavaForJsUtil;

/**
 * 
 * @author doctor
 *
 * @since 2015年2月13日 上午12:00:04
 */
public class Chapter6Code {

	public static void main(String[] args)throws Throwable {
		ScriptEngine engine = JavaForJsUtil.getNashornScriptEngine();
		Reader reader = JavaForJsUtil.getDefaultJSFileInPackage(Chapter6Code.class);
		engine.eval(reader);

	}

}
