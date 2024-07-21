package org.example.view.implementation;

import org.example.view.OutputView;

public class OutputViewImpl implements OutputView {

    @Override
    public void outputView(String text) {
        System.out.println(text);
    }
}
