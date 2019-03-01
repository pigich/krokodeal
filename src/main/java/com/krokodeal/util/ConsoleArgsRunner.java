package com.krokodeal.util;

import static com.krokodeal.pojos.EmailData.setSubject;
import static com.krokodeal.pojos.ParserFields.*;

public class ConsoleArgsRunner {
    private void goNorth(String parserField) {
        System.out.println("parserField = " + parserField);
    }

    public interface MoveAction {
        void move(String parserField);
    }


    private ConsoleArgsRunner.MoveAction[] moveActions = new ConsoleArgsRunner.MoveAction[]{
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setSubject(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setSearch_text(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setAuc_num(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setOkrb(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setCompany_title(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setPeriod(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setFrom_date(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setCreated_to(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setRequest_end_from(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setRequest_end_to(parserField);
                }
            },
            new ConsoleArgsRunner.MoveAction() {
                public void move(String parserField) {
                    setEntriesOnPage(parserField);
                }
            }
    };

    public void move(int index, String parserField) {
        moveActions[index].move(parserField);
    }
}
