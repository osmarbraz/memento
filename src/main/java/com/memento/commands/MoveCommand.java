package com.memento.commands;

import com.memento.editor.Editor;
import com.memento.shapes.Shape;

/**
 * Um objeto comando pode agir como cuidador.
 *
 * Neste caso, o comando obtém o memento antes que ele mude o estado do
 * originador. Quando o undo(desfazer) é solicitado, ele restaura o estado do
 * originador a partir de um memento.
 */
public class MoveCommand implements Command {

    private Editor editor;
    private int startX, startY;
    private int endX, endY;

    public MoveCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "Move por X:" + (endX - startX) + " Y:" + (endY - startY);
    }

    public void start(int x, int y) {
        startX = x;
        startY = y;
        for (Shape child : editor.getShapes().getSelected()) {
            child.drag();
        }
    }

    public void move(int x, int y) {
        for (Shape child : editor.getShapes().getSelected()) {
            child.moveTo(x - startX, y - startY);
        }
    }

    public void stop(int x, int y) {
        endX = x;
        endY = y;
        for (Shape child : editor.getShapes().getSelected()) {
            child.drop();
        }
    }

    @Override
    public void execute() {
        for (Shape child : editor.getShapes().getSelected()) {
            child.moveBy(endX - startX, endY - startY);
        }
    }
}
