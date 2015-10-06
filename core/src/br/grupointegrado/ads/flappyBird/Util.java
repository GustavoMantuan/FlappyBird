package br.grupointegrado.ads.flappyBird;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dorga on 05/10/2015.
 */
public class Util {



    public static final float ESCALA = 2;
    public static final float PIXEL_METRO = 32;
    public static final float ALTURA_BASICA = 80 / PIXEL_METRO;
    public static final float ALTURA_CHAO = 2.5f;


    /**
     * Cria um corpo dentro do mundo
     *
     * @param mundo
     * @param tipo
     * @param x
     * @param y
     * @return
     */
    public static Body criarCorpo(World mundo, BodyDef.BodyType tipo, float x, float y){
        BodyDef definicao = new BodyDef();
        definicao.type = tipo;
        definicao.position.set(x, y);
        definicao.fixedRotation = true;
        Body corpo = mundo.createBody(definicao);

        return corpo;

    }

    /**
     * Cria uma forma para o corpo
     * @param corpo
     * @param shape
     * @param nome
     * @return
     */
    public static Fixture criaForma(Body corpo, Shape shape, String nome){
        FixtureDef definicao = new FixtureDef();
        definicao.density = 1; //densidade
        definicao.friction = 0.06f; //atrito entre um corpo e outro
        definicao.restitution = 0.3f; //elasticidade
        definicao.shape = shape;
        Fixture forma = corpo.createFixture(definicao);

        forma.setUserData(nome);

        return forma;

    }
}
