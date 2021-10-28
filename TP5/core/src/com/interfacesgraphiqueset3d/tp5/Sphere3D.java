package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.math.Vector3;

public class Sphere3D extends Object3D{

    public Sphere3D(String nom, Vector3 center, float radius, Vector3 color, float kPhong, float kReflex, float kRefract) {
        super(nom, kPhong, kReflex, kRefract, color, center, radius);
    }
    
}
