package org.example.model;

import org.example.controller.handlers.implementation.ViewMethodsImpl;
import org.example.view.Messages;
import org.example.view.implementation.OutputViewImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlerInputText {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    NoteModelService noteModelService = new NoteModelServiceImpl();

    public void handler() throws IOException, InterruptedException {
        String command = reader.readLine().toUpperCase();
        switch (command) {
            case "C" -> noteModelService.save(noteModelService.create());
            case "U" -> {
                new OutputViewImpl().outputView(Messages.OUTPUTMESSAGEUPDATETASKS.getValue().formatted(new ViewMethodsImpl().outputAllTasks()));
                noteModelService.save(noteModelService.update(Integer.parseInt(reader.readLine())));
            }
            case "V" -> {
                new OutputViewImpl().outputView(Messages.QUESTIONOFNUMBER.getValue());
                new OutputViewImpl().outputView(new ViewMethodsImpl().outputTask(Integer.parseInt(reader.readLine())));
                reader.readLine();
            }
            case "D" -> {
                new OutputViewImpl().outputView(Messages.OUTPUTMESSAGEDELETETASKS.getValue().formatted(new ViewMethodsImpl().outputAllTasks()));
                noteModelService.delete(Integer.parseInt(reader.readLine()));
            }
            default -> {
                new OutputViewImpl().outputView("Введено не верное значение!\n");
                Thread.sleep(2000);
            }
        }
        new ViewMethodsImpl().startProgram();
    }
}
