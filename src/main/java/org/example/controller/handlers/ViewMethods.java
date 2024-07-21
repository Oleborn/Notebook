package org.example.controller.handlers;

import java.io.IOException;

public interface ViewMethods {
    void startProgram() throws IOException, InterruptedException;

    Integer questionCreateNumberTask() throws IOException, InterruptedException;
    String questionCreateTitleTask() throws IOException;
    String questionCreateTextTask() throws IOException;
    String outputAllTasks() throws IOException;
    String outputTask(int index) throws IOException;
}
