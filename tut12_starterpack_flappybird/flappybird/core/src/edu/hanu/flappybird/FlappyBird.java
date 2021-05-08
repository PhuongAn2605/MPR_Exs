package edu.hanu.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] birds;
	// animating
	int flapState = 0;

	// moving up & down
	float birdY = 0;
	float velocity = 0;
	float gravity = 2;
	int gameState = 0;

	// pipes
	Texture topTube, bottomTube;
	float gap = 400;
	float maxTubeOffset;
	Random randomGenerator;
	float tubeVelocity = 4;
	int numberOfTubes = 4;
	float[] tubeX = new float[numberOfTubes];
	float[] tubeOffset = new float[numberOfTubes];
	float distanceBetweenTubes;

	// scoring
	int score = 0;
	int scoringTube = 0;

	// shapes
	ShapeRenderer shapeRenderer;
	Circle birdCircle;
	Rectangle topTubeRectangle, bottomTubeRectangle;

	Texture gameOver;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		// animating
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");

		// pipes
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		maxTubeOffset = Gdx.graphics.getHeight()/2 - gap /2 - 100;
		randomGenerator = new Random();

		// setups
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
		birdY = Gdx.graphics.getHeight()/2-birds[flapState].getHeight()/2;
		for (int i = 0; i < numberOfTubes; i++) {
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * maxTubeOffset;
			tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + i * distanceBetweenTubes + Gdx.graphics.getWidth();
		}

		// scoring

		// shapes
		shapeRenderer = new ShapeRenderer();
		birdCircle = new Circle();
		topTubeRectangle = new Rectangle();
		bottomTubeRectangle = new Rectangle();
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// the game states
		if (gameState == 1) { // game playing
			// moving up & down
			if (Gdx.input.justTouched()) {
				velocity = -30;
			}

			// pipes
			for (int i = 0; i < numberOfTubes; i++) {
				// replace tube
				if (tubeX[i] < - topTube.getWidth()) {
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
				} else {
					tubeX[i] -= tubeVelocity;
				}

				batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight()/2 + gap/2 + tubeOffset[i]);
				batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight()/2 - gap/2 - bottomTube.getHeight() + tubeOffset[i]);
			}

			if (birdY > 0 || velocity < 0) {
				velocity += gravity;
				birdY -= velocity;
			}
			if(birdY < 0){
				gameState = 2;
			}

			// shapes
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(Color.RED);

			// bird circle
			birdCircle.set(Gdx.graphics.getWidth()/2, birdY + birds[flapState].getHeight()/2, birds[flapState].getWidth()/2);
//			shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

			// scoring tube rectangle
			topTubeRectangle.set(tubeX[scoringTube], Gdx.graphics.getHeight()/2+gap/2 + tubeOffset[scoringTube], topTube.getWidth(), topTube.getHeight());
			bottomTubeRectangle.set(tubeX[scoringTube], Gdx.graphics.getHeight()/2 - gap/2 - bottomTube.getHeight() + tubeOffset[scoringTube], bottomTube.getWidth(), bottomTube.getHeight());
//			shapeRenderer.rect(topTubeRectangle.x, topTubeRectangle.y, topTubeRectangle.width, topTubeRectangle.height);
//			shapeRenderer.rect(bottomTubeRectangle.x, bottomTubeRectangle.y, bottomTubeRectangle.width, bottomTubeRectangle.height);

			shapeRenderer.end();

			// TODO: collision detection
//			Intersector.overlaps(birdCircle, topTubeRectangle);
//			Intersector.overlaps(birdCircle, bottomTubeRectangle);

			if(Intersector.overlaps(birdCircle, topTubeRectangle) || Intersector.overlaps(birdCircle, bottomTubeRectangle)){
				gameState = 2;
			}


		} else if(gameState == 0) { // game start
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		}else{
			gameOver = new Texture("gameover.png");
			batch.draw(gameOver, Gdx.graphics.getWidth()/2 - gap/2, Gdx.graphics.getHeight()/2);
//			gameState = 1;
			if(Gdx.input.justTouched()){
				gameState = 1;
				restart();
//				score = 0;
//				create();
			}
		}

		// animating
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}
		batch.draw(birds[flapState], Gdx.graphics.getWidth()/2-birds[flapState].getWidth()/2, birdY);

		// TODO: scoring
//		int scoringTube = 0;
//		int score = 0;
		if(tubeX[scoringTube] < Gdx.graphics.getWidth()/2){
			score ++;
			scoringTube ++;
			if(scoringTube > 3){
				scoringTube = 0;
			}
		}


		BitmapFont font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);
		font.draw(batch, String.valueOf(score), 100, 200);


		batch.end();
	}

	public void restart(){
		score = 0;
		scoringTube = 0;
		velocity = 0;

		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
		birdY = Gdx.graphics.getHeight()/2-birds[flapState].getHeight()/2;
		for (int i = 0; i < numberOfTubes; i++) {
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * maxTubeOffset;
			tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + i * distanceBetweenTubes + Gdx.graphics.getWidth();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
