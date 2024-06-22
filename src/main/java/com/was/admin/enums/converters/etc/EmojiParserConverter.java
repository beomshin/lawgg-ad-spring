package com.was.admin.enums.converters.etc;

import com.vdurmont.emoji.EmojiParser;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmojiParserConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return EmojiParser.parseToAliases(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return EmojiParser.parseToUnicode(dbData);
    }
}
