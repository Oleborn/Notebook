package org.example.model;

import org.example.controller.handlers.ViewMethods;
import org.example.controller.handlers.implementation.ViewMethodsImpl;
import org.example.model.parseNoteModel.ParseNoteModel;
import org.example.view.OutputView;
import org.example.view.implementation.OutputViewImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class NoteModelServiceImpl implements NoteModelService {

    ViewMethods viewMethods = new ViewMethodsImpl();
    ParseNoteModel parseNoteModel = new ParseNoteModel();
    OutputView outputView = new OutputViewImpl();

    @Override
    public NoteModel create() throws IOException, InterruptedException {
        return NoteModel.builder()
                .number(viewMethods.questionCreateNumberTask())
                .date(String.valueOf(LocalDate.now()))
                .title(viewMethods.questionCreateTitleTask())
                .text(viewMethods.questionCreateTextTask())
                .build();
    }

    @Override
    public void save(NoteModel noteModel) throws IOException {
        parseNoteModel.parseNoteModel(noteModel);
    }

    @Override
    public NoteModel load(int number) throws IOException {
        return parseNoteModel.unparseNoteModel(number);
    }

    @Override
    public NoteModel update(int number) throws IOException, InterruptedException {
        NoteModel noteModel = load(number);
        return requestForUpdate(noteModel);
    }

    @Override
    public void delete(int number) throws IOException, InterruptedException {
        Files.delete(Path.of(ParseNoteModel.pathFiles + number + ".txt"));
        new OutputViewImpl().outputView(("Запись с порядковым номером %d успешно удалена!\n").formatted(number));
        Thread.sleep(2000);
    }

    private NoteModel requestForUpdate(NoteModel noteModel) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        NoteModel noteModelNew = noteModel;
        System.out.println("""
                Что вы желаете изменить?
                1. Заголовок
                2. Текст записи

                Введите номер желаемого изменения!
                """);
        int number = Integer.parseInt(reader.readLine());
        if (number == 1) {
            outputView.outputView("Введите измененный заголовок!");
            noteModelNew.setTitle(reader.readLine());
            outputView.outputView("Заголовок успешно изменен!\n");
        } else if (number == 2) {
            new OutputViewImpl().outputView("Введите измененный текст записи!");
            noteModelNew.setText(reader.readLine());
            outputView.outputView("Текст записи успешно изменен!\n");
        }
        Thread.sleep(2000);
        new ViewMethodsImpl().startProgram();
        return noteModelNew;
    }
}
