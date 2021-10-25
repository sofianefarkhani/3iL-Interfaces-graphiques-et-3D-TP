package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Light extends Object3D{

    private float intensity;
    private Vector3 pos;

    public Light(String nom, float intensity ,Vector3 pos, Vector3 color) {
        super(nom, color, pos);
        this.intensity = intensity;
    }

    public float getIntensity() {
        return this.intensity;
    }

    public Vector3 getPos() {
        return this.pos;
    }

    public boolean isVisibleFrom(Vector3 pt, Scene scene) {
        return false;
    }

    @Override
    public boolean calculIntersection(Ray ray) {
        return false;
    }
    
}
