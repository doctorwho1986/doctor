package com.doctor.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java - javascript
 * 
 * @author doctor
 *
 */
public class FirstJavaJs {
	private static final Logger log = LoggerFactory.getLogger(FirstJavaJs.class);

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		String path = FirstJavaJs.class.getResource("first.js").getFile();
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			log.error("{file:{}}", path, e);
		}

		nashorn.eval(fileReader);

		// 利用工具类
		ScriptEngine nashornScriptEngine = JavaForJsUtil.getNashornScriptEngine();
		try (Reader reader = JavaForJsUtil.getJSFileInPackage(FirstJavaJs.class, "first.js")) {
			nashornScriptEngine.eval(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
