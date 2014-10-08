package com.github.design_patterns;

/**
 * 
 * @author see http://www.tutorialspoint.com/design_pattern/template_pattern.htm
 *
 *         In Template pattern, an abstract class exposes defined way(s)/template(s) to
 *         execute its methods. Its subclasses can overrides the method implementations
 *         as per need basis but the invocation is to be in the same way as defined by
 *         an abstract class. This pattern comes under behavior
 *         pattern category.
 */

public class TemplatePattern {
	public static void main(String[] args) {
		// Use the Game's template method play() to demonstrate a defined way of playing game.
		Game game = new Football();
		game.play();

		game = new Cricket();
		game.play();
	}
}

abstract class Game {
	abstract void initialize();

	abstract void startPlay();

	abstract void endPlay();

	// template method
	//create a Game abstract class defining operations with a template 
	//method set to be final so that it cannot be overridden. 
	public final void play() {
		initialize();
		startPlay();
		endPlay();
	}
}

class Cricket extends Game {

	@Override
	void initialize() {
		System.out.println(this.getClass().getName() + " initialize ");
	}

	@Override
	void startPlay() {
		System.out.println(this.getClass().getName() + " startPlay ");
	}

	@Override
	void endPlay() {
		System.out.println(this.getClass().getName() + " endPlay ");
	}
}

class Football extends Game {

	@Override
	void initialize() {
		System.out.println(this.getClass().getName() + " initialize ");
	}

	@Override
	void startPlay() {
		System.out.println(this.getClass().getName() + " startPlay ");
	}

	@Override
	void endPlay() {
		System.out.println(this.getClass().getName() + " endPlay ");
	}

}