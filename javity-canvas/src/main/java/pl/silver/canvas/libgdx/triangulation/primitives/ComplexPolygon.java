package pl.silver.canvas.libgdx.triangulation.primitives;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import pl.silver.canvas.Position;
import pl.silver.canvas.ShapeHole;
import pl.silver.canvas.libgdx.triangulation.PolygonIntersect;

public class ComplexPolygon {

	private List<Position> shapePositions;
	private List<ShapeHole> shapeHoles;
	
	public ComplexPolygon(List<Position> shapePositions, List<ShapeHole> shapeHoles) {
		this.shapePositions = shapePositions;
		this.shapeHoles = shapeHoles;
	}
	
	public boolean intersect(ComplexPolygon other){
		PolygonIntersect polygonIntersect = new PolygonIntersect();
		Position[] a = shapePositions.toArray(new Position[shapePositions.size()]);
		Position[] b = other.shapePositions.toArray(new Position[other.shapePositions.size()]);
		
		double intersection = PolygonIntersect.intersectionArea(a, b);
		boolean generalIntersection = intersection == 0 ? false : true;
		if(!generalIntersection) return false;
		
		//Sprawdzenie czy obiekt jest w dziurze
		for(ShapeHole myHole : shapeHoles){
				Position[] a1 = myHole.getVertexs().toArray(new Position[myHole.getVertexs().size()]);
				double holeIntersectionValue = PolygonIntersect.intersectionArea(a1, b);
				boolean holeIntersection = holeIntersectionValue == 0 ? false : true;
				System.out.println("hole intersectionValue: " + intersection);
				
				if(holeIntersection) return false;
//			for(ShapeHole otherHole : other.shapeHoles){
//				Position[] a1 = myHole.getVertexs().toArray(new Position[myHole.getVertexs().size()]);
//				Position[] b1 = otherHole.getVertexs().toArray(new Position[otherHole.getVertexs().size()]);
//				double holeIntersectionValue = PolygonIntersect.intersectionArea(a1, b1);
//				boolean holeIntersection = holeIntersectionValue == 0 ? false : true;
//				System.out.println("hole intersectionValue: " + intersection);
//				
//				if(holeIntersection) return false;
//			}
		}
		
		System.out.println("intersectionValue: " + intersection + " collision: " + (intersection == 0 ? false : true));
		return true;
	}

	public void move(Vector2 position) {
		List<Position> shapePositionsNew = new ArrayList<Position>();
		for(Position pos : shapePositions){
			Position posNew = new Position(pos.X + position.x, pos.Y - position.y);
			shapePositionsNew.add(posNew);
		}
		shapePositions = shapePositionsNew;
		
		
		List<ShapeHole> shapeHolesNew = new ArrayList<ShapeHole>();
		for(ShapeHole hole : shapeHoles){
			ShapeHole holeNew = new ShapeHole();
			for(Position pos : hole.getVertexs()){
				Position posNew = new Position(pos.X + position.x, pos.Y - position.y);
				holeNew.addVertexPosition(posNew);
			}
			shapeHolesNew.add(holeNew);
		}
		
		shapeHoles = shapeHolesNew;
	}

	public ComplexPolygon copyComplexPolygon() {
		ComplexPolygon cp = new ComplexPolygon(shapePositions, shapeHoles);
		return cp;
	}

}
