package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Plane3D extends Object3D {

    private Plane plane;

    public Plane3D(String nom, float kPhong, float kReflex, float kRefract, float kText, float posNorm, Vector3 normal ,Vector3 color) {
        super(nom, kPhong, kReflex, kRefract, kText, color, normal);
        this.plane = new Plane(normal, posNorm);

    }

    public Plane getPlane() {
        return this.plane;
    }

    @Override
    public boolean calculIntersection(Ray ray) {
        return Intersector.intersectRayPlane(ray, this.plane, null);
    }
    
}
