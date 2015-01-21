package com.doctor.typehandler;

import org.apache.ibatis.type.MappedTypes;

import com.doctor.enums.FavouriteSection;

@MappedTypes(FavouriteSection.class)
public class FavouriteSectionHandler extends AbstractIEnumDescriptionHandler {

}
