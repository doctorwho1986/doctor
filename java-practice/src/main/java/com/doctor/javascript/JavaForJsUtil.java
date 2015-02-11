package com.doctor.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java-js工具类
 * 
 * @author doctor
 *
 */
public interface JavaForJsUtil {
	static final Logger log = LoggerFactory.getLogger(JavaForJsUtil.class);

	/**
	 * 得到nashorn引擎
	 * 
	 * @return
	 */
	static ScriptEngine getNashornScriptEngine() {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		return nashorn;
	}

	/**
	 * classT类相同包下的js文件
	 * 得到
	 * 
	 * @param classT
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	static Reader getJSFileInPackage(Class<?> classT, String fileName) throws FileNotFoundException {
		String file = classT.getResource(fileName).getFile();
		return new FileReader(file);
	}

}
