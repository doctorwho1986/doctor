package com.doctor.javascript.ebook.the_definitive_guide6;

import com.doctor.javascript.JavaForJsUtil;
import javax.script.ScriptEngine;
import java.io.Reader;
/**

 * Created by doctor on 15-2-14.
 */
public class Chapter8Code {
    public static  void main(String[] args) throws  Throwable{
        ScriptEngine engine = JavaForJsUtil.getNashornScriptEngine();
        Reader reader = JavaForJsUtil.getDefaultJSFileInPackage(Chapter8Code.class);
        engine.eval(reader);
    }
}
