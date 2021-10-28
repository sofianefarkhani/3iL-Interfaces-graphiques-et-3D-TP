package com.interfacesgraphiqueset3d.tp5;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;

public class Light extends Object3D{

    private float intensity;

    /**
     * Constructor of an light
     * @param name
     * @param intensity
     * @param pos
     * @param color
     */
    public Light(String name, float intensity ,Vector3 pos) {
        super(name, pos);
        this.intensity = intensity;
    }

    /**
     * 
     * @return
     */
    public float getIntensity() {
        return this.intensity;
    }

    /**
     * 
     * @param pt
     * @param scene
     * @return
     */
    public boolean isVisibleFrom(Vector3 pt, ArrayList<Object3D> scene) {
        return false;
    }
    
}
