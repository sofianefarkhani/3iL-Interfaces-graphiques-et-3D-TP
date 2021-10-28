package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Vector3;

public abstract class Object3D {

    protected String nom;
    protected float kPhong, kReflex, kRefract,radius;
    protected Vector3 color, pos;

    // CONSTRUCTORS

    public Object3D(String nom, float kPhong, float kReflex, float kRefract, Vector3 color, Vector3 pos, float radius) {
        this.nom = nom;
        this.kPhong = kPhong;
        this.kReflex = kReflex;
        this.kRefract = kRefract;
        this.color = color;
        this.pos = pos;
        this.radius = radius;
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

    public String getNom() {
        return this.nom;
    }

    public float getRadius() {
        return this.radius;
    }
    
}
