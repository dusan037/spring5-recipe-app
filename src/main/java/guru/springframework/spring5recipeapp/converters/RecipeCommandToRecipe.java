package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Override
    @Synchronized
    public Recipe convert(RecipeCommand recipeCommand) {
        if (Objects.isNull(recipeCommand)) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setServings(recipeCommand.getServings());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        if (!CollectionUtils.isEmpty(recipeCommand.getCategories())) {
            recipeCommand.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (!CollectionUtils.isEmpty(recipeCommand.getIngredients())) {
            recipeCommand.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
