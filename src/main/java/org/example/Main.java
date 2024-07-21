package org.example;

import org.example.controller.handlers.implementation.ViewMethodsImpl;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new ViewMethodsImpl().startProgram();
    }
}