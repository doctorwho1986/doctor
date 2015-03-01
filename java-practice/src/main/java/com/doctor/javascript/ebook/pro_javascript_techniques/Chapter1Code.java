package com.doctor.javascript.ebook.pro_javascript_techniques;

import java.io.FileNotFoundException;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.doctor.javascript.JavaForJsUtil;

/**
 * @author doctor
 *
 * @since 2015年3月1日 上午9:20:47
 */
public class Chapter1Code {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws ScriptException 
	 */
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		ScriptEngine nashornScriptEngine = JavaForJsUtil.getNashornScriptEngine();
		Reader reader = JavaForJsUtil.getDefaultJSFileInPackage(Chapter1Code.class);
		nashornScriptEngine.eval(reader);
		Invocable invocable = (Invocable) nashornScriptEngine;
	}

}
