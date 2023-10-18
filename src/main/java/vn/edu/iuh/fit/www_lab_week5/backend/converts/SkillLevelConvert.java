package vn.edu.iuh.fit.www_lab_week5.backend.converts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.www_lab_week5.backend.enums.SkillLevel;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SkillLevelConvert implements AttributeConverter<SkillLevel, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SkillLevel attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SkillLevel convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(SkillLevel.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}

