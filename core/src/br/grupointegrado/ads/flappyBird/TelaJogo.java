package br.grupointegrado.ads.flappyBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dorga on 28/09/2015.
 */
public class TelaJogo extends TelaBase {

    private static final float ESCALA = 2;
    private static final float PIXEL_METRO = 32;

    private OrthographicCamera camera; //camera do jogo
    private World mundo; //representa o mundo do Box2D
    private Box2DDebugRenderer debug; //representa o mundo na tela para ajudar

    public TelaJogo(MainGame game) {
        super(game);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        debug = new Box2DDebugRenderer();
        mundo = new World(new Vector2(0,-9.8f), false);
        initPassaro();
    }

    private void initPassaro() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        float y = (Gdx.graphics.getWidth() / 2 / ESCALA) / PIXEL_METRO + 2;

        float x = (Gdx.graphics.getHeight() / 2 / ESCALA) / PIXEL_METRO + 2;
        def.position.set(x, y);
        def.fixedRotation = true;
        Body corpo = mundo.createBody(def);
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / PIXEL_METRO);
        Fixture fixacao = corpo.createFixture(shape, 1);
        shape.dispose();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f,0.25f,0.25f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();

        mundo.step(delta, 6, 2);
        debug.render(mundo, camera.combined.scl(PIXEL_METRO));
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
