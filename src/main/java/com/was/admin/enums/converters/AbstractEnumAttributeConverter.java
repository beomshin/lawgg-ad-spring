package com.was.admin.enums.converters;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractEnumAttributeConverter<E extends Enum<E> & LegacyEnum>
    implements AttributeConverter<E, Integer> {

  /** 대상 클래스 */
  private Class<E> enumClass;

  public AbstractEnumAttributeConverter(Class<E> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public Integer convertToDatabaseColumn(E attr) {
    return Optional.ofNullable(attr).map(EnumValueConvertUtils::toCode).orElse(null);
  }

  @Override
  public E convertToEntityAttribute(Integer column) {
    return Optional.ofNullable(column)
        .map(it -> EnumValueConvertUtils.ofCode(enumClass, column))
        .orElse(null);
  }
}
