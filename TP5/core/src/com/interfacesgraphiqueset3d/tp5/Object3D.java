package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public abstract class Object3D {

    protected String nom;
    protected float kPhong, kReflex, kRefract, kText;
    protected Vector3 color, pos;

    // CONSTRUCTORS

    public Object3D(String nom, float kPhong, float kReflex, float kRefract, float kText, Vector3 color, Vector3 pos) {
        this.nom = nom;
        this.kPhong = kPhong;
        this.kReflex = kReflex;
        this.kRefract = kRefract;
        this.kText = kText;
        this.color = color;
        this.pos = pos;
    }

    public Object3D(String nom, Vector3 color, Vector3 pos) {
        this.nom = nom;
        this.color = color;
        this.pos = pos;
    }

    // GETTERS

    public Vector3 getColor() {
        return this.color;
    }

    public Vector3 getPos() {
        return this.pos;
    }

    public float getKPhong() {
        return this.kPhong;
    }

    public float kReflex() {
        return this.kReflex;
    }

    public float getKRefract() {
        return this.kRefract;
    }

    public float getKText() {
        return this.kText;
    }

    // Other methods

    public abstract boolean calculIntersection(Ray ray);
    
}
