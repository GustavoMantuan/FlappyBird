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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dorga on 28/09/2015.
 */
public class TelaJogo extends TelaBase {

    private OrthographicCamera camera; //camera do jogo
    private World mundo; //representa o mundo do Box2D
    private Body chao;
    private Box2DDebugRenderer debug; //representa o mundo na tela para ajudar
    private Passaro passaro;

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

        initChao();
        initPassaro();

        new Obstaculo(mundo, camera, null);
    }

    private void initChao() {
        chao = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, 0, 0);
    }

    private void initPassaro() {
//        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.DynamicBody;
       // float y = (Gdx.graphics.getWidth() / 2 / ESCALA) / PIXEL_METRO + 2;
        //float x = (Gdx.graphics.getHeight() / 2 / ESCALA) / PIXEL_METRO + 2;
        //Body corpo = mundo.createBody(def);



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


        capturaTeclas();
        atualiziar(delta);
        renderizar(delta);
        debug.render(mundo, camera.combined.cpy().scl(Util.PIXEL_METRO));


    }

    private boolean pulando = false;
    private void capturaTeclas() {
        pulando = false;
        if (Gdx.input.justTouched()){
            pulando = true;
        }

    }

    private void renderizar(float delta) {

    }

    private void atualiziar(float delta) {
        passaro.atualizar(delta);
        mundo.step(1f / 60f, 6, 2);
        atualizarCamera();
        atualizarChao();
        if (pulando){
            passaro.pular();
        }
    }

    private void atualizarCamera() {
camera.position.x = ((passaro.getCorpo().getPosition().x * Util.PIXEL_METRO) + passaro.getLargura());
        camera.update();
    }

    private void atualizarChao() {
        Vector2 posicaoP = passaro.getCorpo().getPosition();

        chao.setTransform(posicaoP.x, 0, 0);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width/ Util.ESCALA, height / Util.ESCALA);
        camera.update();
        redimentsionaChao();
    }

    private void redimentsionaChao() {
        chao.getFixtureList().clear();
        float largura = camera.viewportWidth / Util.PIXEL_METRO;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, Util.ALTURA_CHAO / 2);
        Fixture forma = Util.criaForma(chao, shape, "CHAO");
        shape.dispose();
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
        debug.dispose();
        mundo.dispose();
    }
}
