package com.wordz.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    private int position;
    public Score(String correct) {
        this.correct = correct;
    }
    public Letter letter(int position) {
        return resultado;
    }
    public void assess(String attempt) {
        if (isCorrectLetter(attempt)) {
            resultado = Letter.CORRECT;
        }
    }
    private boolean isCorrectLetter(String attempt){
        return correct.charAt(position) == attempt.
            charAt(position);
    }
}
