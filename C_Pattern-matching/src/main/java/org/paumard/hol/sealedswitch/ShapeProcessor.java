package org.paumard.hol.sealedswitch;

import org.paumard.hol.sealedswitch.model.Circle;
import org.paumard.hol.sealedswitch.model.Rectangle;
import org.paumard.hol.sealedswitch.model.Shape;
import org.paumard.hol.sealedswitch.model.Square;

public class ShapeProcessor {

    public double computeSurface(Shape shape) {
        return switch (shape) {
            case Rectangle(double length, double width) -> length * width;
            case Square(double edge) -> edge * edge;
            case Circle(_, double radius) -> Math.PI * radius * radius;
        };
    }
}
