package org.example.controller.handlers.implementation;

import org.example.model.HandlerInputText;
import org.example.model.NoteModelServiceImpl;
import org.example.controller.handlers.ViewMethods;
import org.example.model.parseNoteModel.ParseNoteModel;
import org.example.model.NoteModel;
import org.example.view.Messages;
import org.example.view.OutputView;
import org.example.view.implementation.OutputViewImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewMethodsImpl implements ViewMethods {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    OutputView outputView = new OutputViewImpl();

    @Override
    public void startProgram() throws IOException, InterruptedException {
        outputView.outputView(Messages.STARTMESSAGE.getValue().formatted(Messages.OUTPUTMESSAGEALLTASKS.getValue().formatted(new ViewMethodsImpl().outputAllTasks())));
        new HandlerInputText().handler();
    }
    @Override
    public Integer questionCreateNumberTask() throws IOException, InterruptedException {
        outputView.outputView(Messages.QUESTIONOFNUMBER.getValue());
        String number = reader.readLine();
        if (number.matches("^[0-9]+"))
        {
            return Integer.parseInt(number);
        } else {
            new OutputViewImpl().outputView("Введены неверные значения! Присвоено дефолтное значение - 0!");
            return 0;
        }
    }

    @Override
    public String questionCreateTitleTask() throws IOException {
        outputView.outputView(Messages.QUESTIONOFTITLE.getValue());
        return reader.readLine();
    }

    @Override
    public String questionCreateTextTask() throws IOException {
        outputView.outputView(Messages.QUESTIONOFTEXT.getValue());
        return reader.readLine();
    }

    @Override
    public String outputAllTasks() throws IOException {
        StringBuilder result = new StringBuilder();
        File fileFolder = new File(ParseNoteModel.pathFiles);
        File[] arrayNameFiles = fileFolder.listFiles();

        for (File arrayNameFile : arrayNameFiles) {
            NoteModel noteModel = new NoteModelServiceImpl().load(Integer.parseInt(arrayNameFile.getName().split("\\.")[0]));
            result.append("Порядковый номер записи - %d, Заголовок - %s".formatted(noteModel.getNumber(), noteModel.getTitle()));
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String outputTask(int index) throws IOException {
        NoteModel model = new NoteModelServiceImpl().load(index);
        return Messages.OUTPUTTASK.getValue().formatted(model.getNumber(), model.getDate(), model.getTitle(), model.getText());
    }

}
