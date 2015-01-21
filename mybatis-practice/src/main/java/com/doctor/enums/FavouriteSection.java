package com.doctor.enums;

public enum FavouriteSection implements IEnumDescription {
	NEWS(0, "news"),
	VIDEOS(1, "videos"),
	IMAGES(2, "images"),
	PODCASTS(3, "podcasts");
	private int index;
	private String name;

	FavouriteSection(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getDescription() {
		return name;
	}
}
