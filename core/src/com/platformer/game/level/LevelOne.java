package com.platformer.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.platformer.game.base.LevelBase;
import com.platformer.game.characters.Player;
import com.platformer.game.config.GameConfig;
import com.platformer.game.debug.DebugCameraController;
import com.platformer.game.physics.CollisionListener;
import com.platformer.game.physics.WorldController;

public class LevelOne extends LevelBase {

    private float time;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private DebugCameraController debugCameraController;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private ScreenViewport viewport;

    public LevelOne() {
    }

    @Override
    public void show() {
        WorldController.createWorld();
        WorldController.getWorld().setContactListener(new CollisionListener());

        initCamera();
        initGraphics();
        initDebug();
        initCharacters();
    }

    @Override
    public void render(float delta) {
        this.time += Gdx.graphics.getDeltaTime();
        Player.handleInput();

        WorldController.getWorld().step(1 / 60f, 6, 2);

        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.x = Player.getPlayerPosition().x;
        camera.position.y = 5;

        camera.update();
        renderer.setView(camera);
        renderer.render();
        debugRenderer.render(WorldController.getWorld(), camera.combined);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();

        Player.updatePlayer(time);
        this.batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    private void initDebug() {
        this.debugRenderer = new Box2DDebugRenderer();
        this.debugCameraController = new DebugCameraController();
        this.debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    private void initCharacters() {
        Player.initPlayer(new Vector2(14, 3), this.batch);
    }

    private void initGraphics() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        TiledMap tiledMap = new TmxMapLoader().load("level1.tmx");
        this.batch = new SpriteBatch();
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap, GameConfig.SCALING_FACTOR);

        for (int i = 2; i < 4; i++) {
            for (MapObject object : tiledMap.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) * GameConfig.SCALING_FACTOR,
                        (rectangle.getY() + rectangle.getHeight() / 2) * GameConfig.SCALING_FACTOR);
                Body body = WorldController.getWorld().createBody(bodyDef);

                shape.setAsBox((rectangle.getWidth() / 2) * GameConfig.SCALING_FACTOR, (rectangle.getHeight() / 2) * GameConfig.SCALING_FACTOR);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
            }
        }
    }

    private void initCamera() {
        this.camera = new OrthographicCamera();
        this.viewport = new ScreenViewport(this.camera);
        this.viewport.setUnitsPerPixel(GameConfig.SCALING_FACTOR);
        this.camera.setToOrtho(false, Gdx.graphics.getWidth() * GameConfig.SCALING_FACTOR,
                Gdx.graphics.getHeight() * GameConfig.SCALING_FACTOR);

        this.camera.update();
    }


}
