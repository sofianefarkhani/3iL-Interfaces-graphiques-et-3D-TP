package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import java.util.ArrayList;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Application extends ApplicationAdapter {
    private FitViewport viewport;
    private PerspectiveCamera camera;
    private Pixmap pixels;
    private Texture textureWithPixels;
    private SpriteBatch spriteBatch;
    private Vector2 currentScreen;
    private Vector3 currentScene;
    private Vector3 tmpVector3;
    private ArrayList<Object3D> scene;
    Vector3 rayOrigin;

    @Override
    public void create() {
        // Get screen dimensions, in pixels :
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        scene = new ArrayList<Object3D>();
        Sphere3D sphere = new Sphere3D("sphere1", new Vector3(0f,0f,-5f), 1f, new Vector3(1f, 0f, 0f),1f, 1f, 1f);
        Sphere3D sphere2 = new Sphere3D("sphere2", new Vector3(0f,1f,-3f), 2f, new Vector3(0f, 1f, 0f),1f, 1f, 1f);
        Light light = new Light("lampe",2,new Vector3(0f, 2f, -5f));

        scene.add(sphere);
        scene.add(sphere2);
        scene.add(light);

        // Create a camera with perspective view :
        camera = new PerspectiveCamera(50.0f, screenWidth, screenHeight);
        camera.position.set(0f, 0f, -10f);
        camera.lookAt(0, 0, 0);
        camera.near = 10f;
        camera.far = 500f;
        camera.update();

        // Create a viewport to convert coords of screen space into coords of scene
        // space.
        viewport = new FitViewport(screenWidth, screenHeight, camera);

        // Create an array of pixels, initialized with grey color :
        pixels = Pixmap.createFromFrameBuffer(0, 0, screenWidth, screenHeight);
        for (int y = 0; y < screenHeight; y++) {
            for (int x = 0; x < screenWidth; x++) {
                pixels.setColor(0.1f, 0.1f, 0.1f, 1f);
                pixels.drawPixel(x, y);
            }
        }

        // Add pixels in a Texture in order to render them :
        spriteBatch = new SpriteBatch();
        textureWithPixels = new Texture(pixels);

        // Initialize coords of the first pixel, in screen space :
        currentScreen = new Vector2(0, 0);

        rayOrigin = camera.position;

        // Others initializations :
        currentScene = new Vector3();
        tmpVector3 = new Vector3();
    }

    @Override
    public void render() {
        // If "ctrl + s" is pressed :
        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.S)) {
            // Save the pixels into a png file :
            savePixelsInPngFile();
        }

        // If "escape" is pressed :
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            // Close th application :
            Gdx.app.exit();
        }
        // Reset the screen buffer colors :
        ScreenUtils.clear(0, 0, 0, 1);

        // Launch ray
        lancerRayon();

        // Process pixels color :
        //processPixel();

        // Refresh texture
        textureWithPixels.draw(pixels, 0, 0);

        // Render the texture with pixels :
        spriteBatch.begin();
        spriteBatch.draw(textureWithPixels, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        textureWithPixels.dispose();
        pixels.dispose();
    }

    /**
     * Compute the color of each screen pixels and store the results in the pixels
     * map.
     */
    private boolean processPixel() {
        boolean isOk = true;

        // Get color of current pixel :
        Vector3 color = getColor((int) currentScreen.x, (int) currentScreen.y, 1);

        // Save color into pixels map :
        pixels.setColor(color.x, color.y, color.z, 1f);
        pixels.drawPixel((int) currentScreen.x, (int) currentScreen.y);

        return isOk;
    }

    /**
     * Wrire pixels in the png file "core/assets/render.png". If the file already
     * exists it will be overrided.
     */
    private boolean savePixelsInPngFile() {
        boolean isOk = true;

        // Create file :
        FileHandle file = Gdx.files.local("render.png");

        // Write pixels in file :
        PixmapIO.writePNG(file, pixels);

        return isOk;
    }

    /**
     * Return the color processed with path tracing and Phong method for the given pixel.
     * @param xScreen
     * @param yScreen
     * @param rec
     */
    private Vector3 getColor(int xScreen, int yScreen, int rec)
    {
        
        Vector3 color = new Vector3(0.0f, 0.0f, 0.0f); 
        
        
        if (rec >= 1) {
            Object3D intersectMin = null;
            float distanceMin = 99999999f, currentDst, iAmb = 1f, kAmb = 1f, iDiff = 1f, kDiff = 1f;
            Vector3 colorAmbiant = new Vector3(0f,0f,0f), colorDiffuse = new Vector3(0f,0f,0f), 
                colorSpecular = new Vector3(0f,0f,0f), colorPhong = new Vector3(0f,0f,0f);

            // Get coords of current pixel, in scene space :
            tmpVector3.set(xScreen, yScreen, 0);
            currentScene = camera.unproject(tmpVector3);
            Vector3 posPix = new Vector3(currentScene.x, currentScene.y, 0f);
            
            Ray ray = new Ray(rayOrigin, posPix.sub(rayOrigin));
            

            for (int i = 0 ; i < scene.size() ; i++) {
                if(scene.get(i) instanceof Sphere3D) {
                    if(Intersector.intersectRaySphere(ray, scene.get(i).getPos(), scene.get(i).getRadius(), null)) {
                        currentDst = rayOrigin.dst(scene.get(i).getPos());
                        // Get object 
                        if(currentDst < distanceMin) {
                            intersectMin = scene.get(i);
                            distanceMin = currentDst;
                        }
                    }
                }
            }

            if (intersectMin != null) {
                // Get ambiant color
                colorAmbiant = intersectMin.getColor();
                iAmb = 1.0f;
                
                // Get diffuse color
                colorDiffuse = intersectMin.getColor();
                Vector3 norm = ray.origin.nor();
                Vector3 lightVec = new Vector3(0f,0f, -1f);
                for (int i = 0 ; i < scene.size() ; i++) {
                    if(scene.get(i) instanceof Light) {
                        lightVec = scene.get(i).getPos().sub(intersectMin.getPos());
                    }
                }
                lightVec = lightVec.nor();
                iDiff = Math.max(0.0f, Vector3.dot(lightVec.x, lightVec.y, lightVec.z, norm.x, norm.y, norm.z));
            }
            colorPhong = colorAmbiant.scl(iAmb*kAmb);//.add(colorDiffuse);//*kDiff));
            color = colorPhong;
        }
        
        return color;
    }

    /**
     * 
     */
    private boolean lancerRayon() {
        for (int i = 0 ; i < pixels.getWidth() ; i++) {
            for (int j = 0 ; j < pixels.getHeight() ; j++) {
                Vector3 colorFound = getColor(i,j, 2);
                pixels.setColor(colorFound.x,colorFound.y,colorFound.z,1f);
                pixels.drawPixel(i,j);
            }
        }
        return true;
    }
}