package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID_2);
        categories.add(category1);
        categories.add(category2);

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);

        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(recipeCommand.getId(), RECIPE_ID);
        assertEquals(recipeCommand.getCookTime(), COOK_TIME);
        assertEquals(recipeCommand.getDescription(), DESCRIPTION);
        assertEquals(recipeCommand.getDifficulty(), DIFFICULTY);
        assertEquals(recipeCommand.getPrepTime(), PREP_TIME);
        assertEquals(recipeCommand.getDirections(), DIRECTIONS);
        assertEquals(recipeCommand.getServings(), SERVINGS);
        assertEquals(recipeCommand.getSource(), SOURCE);
        assertEquals(recipeCommand.getUrl(), URL);
        assertEquals(recipeCommand.getNotes().getId(), NOTES_ID);
        assertEquals(recipeCommand.getCategories().size(), 2);
        assertEquals(recipeCommand.getIngredients().size(), 2);
    }
}
