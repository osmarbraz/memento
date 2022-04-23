import com.memento.editor.Editor;
import com.memento.shapes.Circle;
import com.memento.shapes.CompoundShape;
import com.memento.shapes.Dot;
import com.memento.shapes.Rectangle;

import java.awt.*;

public class Principal {

    public static void main(String[] args) {
        //Instância o Editor
        Editor editor = new Editor();
        //Carrega as formas
        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),
                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),
                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}
