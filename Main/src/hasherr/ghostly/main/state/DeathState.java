package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import hasherr.ghostly.main.core.Game;
import hasherr.ghostly.main.ui.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/16/14
 */
public class DeathState extends State
{
    Preferences preferences = Gdx.app.getPreferences("My Preferences"); // A standard set of preferences for holding high scores.
                                                                        // This will be saved every game.
    StateManager managerToRespondTo;
    OrthographicCamera camera;
    float position;
    int scoreToShow;

    Vector3 touchPos;
    Button replayButton;
    Button menuButton;

    BitmapFont scoreFont;
    int highScore;



    public DeathState(StateManager managerToRespondTo, OrthographicCamera cameraToUnproject, float position, int scoreToShow)
    {
        this.managerToRespondTo = managerToRespondTo;
        touchPos = new Vector3();
        this.camera = cameraToUnproject;
        this.position = position;

        // Create buttons and their actions.
        Action replayButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                replay();
                return true;
            }
        };
        replayButton = new Button(position, 500f, 256f, 128f, new Texture("sprites/ui/replay_button.png"), replayButtonAction);

        Action menuButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                goBackToMenu();
                return true;
            }
        };
        menuButton = new Button(position, 300f, 256f, 128f, new Texture("sprites/ui/main_menu_button.png"), menuButtonAction);

        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score_font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("fonts/score_font_0.png"))));
        highScore = preferences.getInteger("highScore");
        this.scoreToShow = scoreToShow;
    }

    private void replay()
    {
        managerToRespondTo.switchToGameState();
    }

    private void goBackToMenu()
    {
        managerToRespondTo.switchToMenuState();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        // Scale and draw font with score.
        scoreFont.setScale(3.5f, 3.5f);
        scoreFont.draw(batch, "Score: " + scoreToShow, position - 35f, 720f);

        // Draw buttons.
        batch.draw(replayButton.getTexture(), replayButton.getX(), replayButton.getY());
        batch.draw(menuButton.getTexture(), menuButton.getX(), menuButton.getY());

        // Display the high score if and only if the player does not beat their previous score or the preferences haven't been defined.
        scoreFont.setScale(2f, 2f);
        if (scoreToShow > highScore || !preferences.contains("highScore"))
        {
            setHighScore(scoreToShow);
            scoreFont.draw(batch, "New High Score!", position - 25f, 280f);
        }
        else
        {
            scoreFont.draw(batch, "High Score: " + preferences.getInteger("highScore"), position - 25f, 280f);
        }
    }

    @Override
    public void update()
    {
        handleInput(touchPos);
    }

    private void handleInput(Vector3 touchPos)
    {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        camera.unproject(touchPos);
        if (Gdx.input.isTouched() && replayButton.getBoundingRectangle().contains(touchPos.x, touchPos.y))
        {
            replayButton.doAction();
        }
        if (Gdx.input.isTouched() && menuButton.getBoundingRectangle().contains(touchPos.x, touchPos.y))
        {
            menuButton.doAction();
        }
    }

    private void setHighScore(int newScore)
    {
        preferences.putInteger("highScore", newScore);
        preferences.flush();
    }
}
