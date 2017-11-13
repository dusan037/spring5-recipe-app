package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = 5;
    public static final Integer PREP_TIME = 7;
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = 3;
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        recipeCommand.setNotes(notes);

        Set<CategoryCommand> categories = new HashSet<>();
        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID_2);
        categories.add(category1);
        categories.add(category2);

        Set<IngredientCommand> ingredients = new HashSet<>();
        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGRED_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        recipeCommand.setCategories(categories);
        recipeCommand.setIngredients(ingredients);

        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(recipe.getId(), RECIPE_ID);
        assertEquals(recipe.getCookTime(), COOK_TIME);
        assertEquals(recipe.getDescription(), DESCRIPTION);
        assertEquals(recipe.getDifficulty(), DIFFICULTY);
        assertEquals(recipe.getPrepTime(), PREP_TIME);
        assertEquals(recipe.getDirections(), DIRECTIONS);
        assertEquals(recipe.getServings(), SERVINGS);
        assertEquals(recipe.getSource(), SOURCE);
        assertEquals(recipe.getUrl(), URL);
        assertEquals(recipe.getNotes().getId(), NOTES_ID);
        assertEquals(recipe.getCategories().size(), 2);
        assertEquals(recipe.getIngredients().size(), 2);
    }
}
