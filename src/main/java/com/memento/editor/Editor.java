package com.memento.editor;

import com.memento.shapes.CompoundShape;
import com.memento.history.History;
import com.memento.history.Memento;
import com.memento.shapes.Shape;
import com.memento.commands.Command;
import javax.swing.*;
import java.io.*;
import java.util.Base64;

/**
 * Código do editor.
 *
 * O originador tem alguns dados importantes que podem mudar com o tempo. Ele
 * também define um método para salvar seu estado dentro de um memento e outro
 * método para restaurar o estado dele.
 */
public class Editor extends JComponent {

    private Canvas canvas;
    private CompoundShape allShapes = new CompoundShape();
    private History history;

    public Editor() {
        canvas = new Canvas(this);
        history = new History();
    }

    public void loadShapes(Shape... shapes) {
        allShapes.clear();
        allShapes.add(shapes);
        canvas.refresh();
    }

    public CompoundShape getShapes() {
        return allShapes;
    }

    public void execute(Command c) {
        history.push(c, new Memento(this));
        c.execute();
    }

    public void undo() {
        if (history.undo()) {
            canvas.repaint();
        }
    }

    public void redo() {
        if (history.redo()) {
            canvas.repaint();
        }
    }

    public String backup() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.allShapes);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            return "";
        }
    }

    public void restore(String state) {
        try {
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            this.allShapes = (CompoundShape) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException ocorreu.");
        } catch (IOException e) {
            System.out.print("IOException ocorreu.");
        }
    }
}
