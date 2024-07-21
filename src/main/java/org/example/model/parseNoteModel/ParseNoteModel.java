package org.example.model.parseNoteModel;

import org.example.model.NoteModel;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class ParseNoteModel {

    public static String pathFiles = "src/main/resources/";

    public void parseNoteModel(NoteModel noteModel) throws IOException {
        if (checkFile(noteModel.getNumber())){
            Files.createFile(Path.of(pathFiles+noteModel.getNumber() + ".txt"));
        }
        Files.writeString(Path.of(pathFiles +noteModel.getNumber() + ".txt"),
                """
                Порядковый номер:%d
                Дата создания:%s
                Заголовок:%s
                Текст записи:%s
                """.formatted(noteModel.getNumber(), noteModel.getDate(), noteModel.getTitle(), noteModel.getText()));
    }
    public NoteModel unparseNoteModel(int number) throws IOException {
        if (!checkFile(number)) {
            List<String> list = Files.readAllLines(Path.of(pathFiles + number + ".txt"));
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                String[] test = list.get(i).split(":");
                map.put(test[0], test[1]);
            }
            NoteModel noteModel = NoteModel.builder()
                    .number(Integer.parseInt(map.get("Порядковый номер")))
                    .date(map.get("Дата создания"))
                    .title(map.get("Заголовок"))
                    .text(map.get("Текст записи"))
                    .build();
            return noteModel;
        }else
            {
                return null;
            }

    }
    public Boolean checkFile(int number){
       return !Files.exists(Path.of(pathFiles + number + ".txt"));
    }
}
