package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import hasherr.ghostly.main.core.Game;
import hasherr.ghostly.main.ui.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/17/14
 */
public class PauseState implements State
{
    Vector3 touchPos;
    Button replayButton;
    Button menuButton;
    OrthographicCamera camera;

    public PauseState(OrthographicCamera cameraToUnproject)
    {

        this.camera = cameraToUnproject;
        Action replayButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                Gdx.app.log("Debug1", "Replay button being pressed.");
                return false;
            }
        };
        Action menuButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                Gdx.app.log("Debug1", "Menu Button being pressed.");
                return false;
            }
        };
        replayButton = new Button(20f, 500f, 256f, 128f, new Texture(Gdx.files.internal("sprites/ui/replay_button.png")), replayButtonAction);
        menuButton = new Button(20f, 300f, 256f, 128f, new Texture(Gdx.files.internal("sprites/ui/main_menu_button.png")), menuButtonAction);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(replayButton.getTexture(), replayButton.getX(), replayButton.getY());
        batch.draw(menuButton.getTexture(), menuButton.getX(), menuButton.getY());
    }

    @Override
    public void update()
    {
        touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
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

    private void handleInput()
    {

    }

    // Unprojects the camera object for UI touch handling.
    private void unprojectCamera()
    {

    }
}
