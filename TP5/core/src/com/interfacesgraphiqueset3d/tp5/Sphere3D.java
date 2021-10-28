package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Vector3;

public class Sphere3D {
    private Vector3 center, color;
    private float radius, kPhong, kReflex, kRefract;

    public Sphere3D(Vector3 center, float radius, Vector3 color, float kPhong, float kReflex, float kRefract) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.kPhong = kPhong;
        this.kReflex = kReflex;
        this.kRefract = kRefract;
    }

    public Vector3 getColor() {
        return color;
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public Vector3 getCenter() {
        return center;
    }

    public void setCenter(Vector3 center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getkPhong() {
        return kPhong;
    }

    public void setkPhong(float kPhong) {
        this.kPhong = kPhong;
    }

    public float getkReflex() {
        return kReflex;
    }

    public void setkReflex(float kReflex) {
        this.kReflex = kReflex;
    }

    public float getkRefract() {
        return kRefract;
    }

    public void setkRefract(float kRefract) {
        this.kRefract = kRefract;
    }
    
}
