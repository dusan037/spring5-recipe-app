package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{
    @Nullable
    @Override
    @Synchronized
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(Objects.isNull(unitOfMeasureCommand)) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(unitOfMeasureCommand.getId());
        uom.setDescription(unitOfMeasureCommand.getDescription());
        return uom;
    }
}
