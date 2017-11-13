package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setDescription(DESCRIPTION);

        NotesCommand notesCommand = converter.convert(notes);
        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(DESCRIPTION, notesCommand.getDescription());
    }
}
