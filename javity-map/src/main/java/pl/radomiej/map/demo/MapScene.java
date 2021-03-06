package pl.radomiej.map.demo;

import java.util.ArrayList;
import java.util.List;

import org.javity.components.RectangleCollider;
import org.javity.components.Rigidbody;
import org.javity.components.SpriteRenderer;
import org.javity.engine.JGameObject;
import org.javity.engine.JScene;
import org.javity.engine.SceneBuilder;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import pl.radomiej.map.GeoPoint;
import pl.radomiej.map.MapCache;
import pl.radomiej.map.MapComponent;
import pl.radomiej.map.Marker;

public class MapScene implements SceneBuilder {

	@Override
	public void buildScene(JScene scene) {
		long size = (long) Math.pow(2, 28);
		JGameObject map = scene.instantiateGameObject(new Vector2(100, 100));
		map.addComponent(new SpriteRenderer("javity.png"));
		map.addComponent(new MapComponent());
		map.addComponent(new MapCache());
		map.addComponent(new RectangleCollider(size, size));
		map.addComponent(new Rigidbody(true));
		map.addComponent(new AddMarkerComponent());
		map.getTransform().setScale(new Vector2(1, 1));	
		
		List<GeoPoint> path = new ArrayList<GeoPoint>();
		path.add(new GeoPoint(52.7576313, 15.2621716));
		path.add(new GeoPoint(52.757214, 15.2618817));
		path.add(new GeoPoint(52.7570431, 15.2624595));
		MapComponent mapComponent = map.getComponent(MapComponent.class);
		mapComponent.addPath("line-viaToll", path, Color.GREEN, 20);
		mapComponent.addMarker("viaToll", new Marker( 52.7576313, 15.2621716, "javity.png", new Vector2(0.1f, 0.1f)));
	}

}
