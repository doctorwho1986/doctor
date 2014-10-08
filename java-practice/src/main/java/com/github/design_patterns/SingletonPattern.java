package com.github.design_patterns;

public class SingletonPattern {

	public static void main(String[] args) {
		AmericaPresident instance = AmericaPresident.getInstance();
		instance.otherMethods();
		
		SingletonOfEnum.instance.otherMethods();
		System.out.println(SingletonOfEnum.instance.toString());
		System.out.println(SingletonOfEnum.instance.ordinal());

	}

}

/**
 * 
 * @author see http://www.programcreek.com/2011/07/java-design-pattern-singleton/
 * 
 * @link Runtime
 *
 */
class AmericaPresident{
	private static final AmericaPresident instance = new AmericaPresident();
	private AmericaPresident(){};
	public static AmericaPresident getInstance() {
		return instance;
	}
	
	public void otherMethods() {
		System.out.println(this.getClass() + " otherMethods");
	}
}


/**
 * 
 * @author 
 * As private constructor doesn't protect from instantiation via reflection, 
 * Joshua Bloch (Effective Java) proposes a better implementation of Singleton. 
 *  with Enum
 *
 */
enum SingletonOfEnum{
	instance;
	public void otherMethods() {
		System.out.println(this.getClass() + " otherMethods");
	}
}