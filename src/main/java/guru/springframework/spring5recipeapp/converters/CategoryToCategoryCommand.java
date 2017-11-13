package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Nullable
    @Override
    @Synchronized
    public CategoryCommand convert(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
