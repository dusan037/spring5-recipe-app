package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Nullable
    @Override
    @Synchronized
    public NotesCommand convert(Notes notes) {
        if (Objects.isNull(notes)) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setDescription(notes.getDescription());

        return notesCommand;
    }
}
