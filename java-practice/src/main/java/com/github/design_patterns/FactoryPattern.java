package com.github.design_patterns;

import org.junit.Assert;

import com.github.design_patterns.HumanFactory.Human;
import com.github.design_patterns.HumanFactory.HumanItem;

/**
 * 
 * @author @link http://www.programcreek.com/2013/02/java-design-pattern-factory/
 *         Factory design pattern is used for creating an object based on different parameters.
 *         The example below is about creating human in a factory. If we ask the factory for a boy,
 *         the factory will produce a boy; if we ask
 *         for a girl, the factory will produce a girl.
 *         Based on different parameters, the factory produce different stuff.
 */

public class FactoryPattern {

	public static void main(String[] args) {
		Human human = HumanFactory.getInstance("boy");
		human.walk();
		human.talk();
		human = HumanFactory.getInstance("girl");
		human.walk();
		human.talk();
		
		human = HumanFactory.getInstance("ss");
		Assert.assertNull(human);
		
		
		human = HumanFactory.getInstance(HumanItem.BOY);
		human.walk();
		human.talk();
		human = HumanFactory.getInstance(HumanItem.Girl);
		human.walk();
		human.talk();
		
	}

}

final class HumanFactory {

	public static Human getInstance(String name) {
		Human human = null;
		if (name.equalsIgnoreCase(Boy.class.getSimpleName())) {
			human = new Boy();
		} else if (name.equalsIgnoreCase(Girl.class.getSimpleName())) {
			human = new Girl();
		}

		return human;
	}

	public static Human getInstance(HumanItem humanItem) {
		Human human = null;
		if (humanItem.name().equalsIgnoreCase(Boy.class.getSimpleName())) {
			human = new Boy();
		} else if (humanItem.name().equalsIgnoreCase(Girl.class.getSimpleName())) {
			human = new Girl();
		}

		return human;
	}
	public interface Human {
		void talk();

		void walk();
	}

	public static enum HumanItem{
		BOY,
		Girl;
	}
	
	public static class Boy implements Human {

		@Override
		public void talk() {
			System.out.println("Boy talk");
		}

		@Override
		public void walk() {
			System.out.println("Boy walk");
		}
	}

	public static class Girl implements Human {

		@Override
		public void talk() {
			System.out.println("Girl talk");
		}

		@Override
		public void walk() {
			System.out.println("Girl walk");
		}

	}
}
