package com.doctor.javascript;

import java.io.FileNotFoundException;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * @see http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
 * @author doctor
 *
 * @since 2015年2月11日 下午11:23:36
 */
public class OracleNashornANextGenerationJavaScriptEngineForTheJVM {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		ScriptEngine engine = JavaForJsUtil.getNashornScriptEngine();
		Reader reader = JavaForJsUtil.getJSFileInPackage(OracleNashornANextGenerationJavaScriptEngineForTheJVM.class, "oracleNashorn.js");
		engine.eval(reader);
		System.out.println(engine.eval("add(1,5)"));
		Invocable invocable = (Invocable) engine;
		Object invokeFunction = invocable.invokeFunction("add", 11,55);
		System.out.println(invokeFunction);
	}

}
