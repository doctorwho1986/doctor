package com.doctor.javascript.ebook.the_definitive_guide6;

import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;

import com.doctor.javascript.JavaForJsUtil;

/**
 * @author doctor
 *
 * @since 2015年2月12日 下午10:05:11
 */
public class Chapter5Code {

	public static void main(String[] args) throws Throwable {
		ScriptEngine engine = JavaForJsUtil.getNashornScriptEngine();
		Reader reader = JavaForJsUtil.getDefaultJSFileInPackage(Chapter5Code.class);
		engine.eval(reader);
		
		Invocable invocable = (Invocable) engine;
		System.out.println(invocable.invokeFunction("fact", 10));
	}

}
