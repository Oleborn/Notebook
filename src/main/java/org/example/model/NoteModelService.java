package org.example.model;

import java.io.IOException;

public interface NoteModelService {
    NoteModel create() throws IOException, InterruptedException;
    void save(NoteModel noteModel) throws IOException;
    NoteModel load(int number) throws IOException;
    NoteModel update(int number) throws IOException, InterruptedException;
    void delete(int number) throws IOException, InterruptedException;

}
