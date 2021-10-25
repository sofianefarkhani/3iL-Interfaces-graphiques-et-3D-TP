package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.utils.Array;

public class Scene {
    private Array<Object3D> listObject;

    public Scene() {
        this.listObject = new Array<Object3D>();
    }

    public void addObject(Object3D object) {
        this.listObject.add(object);
    }

    public Array<Object3D> getAllObject() {
        return this.listObject;
    }
}
