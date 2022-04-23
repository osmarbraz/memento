package com.memento.commands;

import com.memento.editor.Editor;
import com.memento.shapes.Shape;

import java.awt.*;

/**
 * Um objeto comando pode agir como cuidador.
 *
 * Neste caso, o comando obtém o memento antes que ele mude o estado do
 * originador. Quando o undo(desfazer) é solicitado, ele restaura o estado do
 * originador a partir de um memento.
 */
public class ColorCommand implements Command {

    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color) {
        this.editor = editor;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Colorize: " + color.toString();
    }

    @Override
    public void execute() {
        for (Shape child : editor.getShapes().getSelected()) {
            child.setColor(color);
        }
    }
}
