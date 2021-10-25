package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.math.collision.Sphere;

public class Sphere3D extends Object3D{

    private Sphere sphere;

    public Sphere3D(String nom, float kPhong, float kReflex, float kRefract, float kText, Vector3 pos, Vector3 color, float radius) {
        super(nom, kPhong, kReflex, kRefract, kText, color, pos);
        this.sphere = new Sphere(pos, radius);
    }

    @Override
    public boolean calculIntersection(Ray ray) {
        return Intersector.intersectRaySphere(ray, this.sphere.center, this.sphere.radius, null);
    }
    
}
