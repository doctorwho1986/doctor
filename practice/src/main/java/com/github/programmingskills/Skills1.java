package com.github.programmingskills;
/**
 * 
 * @author cui
 * @date 2014年 08月 22日 星期五 09:04:35 CST
 */
public class Skills1 {

	public static void main(String[] args) {
		//作为工具类，一般只有static方法，或者单例模式中用到
		System.out.println(Skill.getNamet()); 
		
		//但有时候，我们不得不用该类的对象调用静态方法，
		//例如：jsp 中 jsp:useBean 标签，我们可以这样实现
		//jsp 中，如果 <jsp:useBean id = "skill" class = "">
		//这样可以调用 <jsp:getProperty name="skill" property="name" />
		//<c:out value="${skill.name}"/>
		//其实，jsp 会专用下面java代码调用
		Skill skill = new Skill();
		skill.getName();

	}

}

 class Skill{
	public static String getNamet() {
		return Skill.class.getName();
	}
	
	public String getName() {
		return getNamet();
	}
}