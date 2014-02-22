package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import hasherr.ghostly.main.core.Game;
import hasherr.ghostly.main.ui.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/16/14
 */
public class DeathState extends State
{
    Button replayButton;
    Button menuButton;
    OrthographicCamera camera;
    Vector3 touchPos;

    public DeathState(OrthographicCamera cameraToUnproject, float position)
    {
        touchPos = new Vector3();
        this.camera = cameraToUnproject;
        Action replayButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                Gdx.app.log("Debug1", "Replay button being pressed.");
                return true;
            }
        };
        Action menuButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {
                Gdx.app.log("Debug1", "Menu Button being pressed.");
                return true;
            }
        };
        replayButton = new Button(position, 500f, 256f, 128f, new Texture(Gdx.files.internal("sprites/ui/replay_button.png")), replayButtonAction);
        menuButton = new Button(position, 300f, 256f, 128f, new Texture(Gdx.files.internal("sprites/ui/main_menu_button.png")), menuButtonAction);
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
        Gdx.app.log("Debug1", "LOL LOL LOL");
        resetTouchPosition();
        handleInput(touchPos);
    }

    private void resetTouchPosition()
    {
        touchPos.x = Gdx.input.getX();
        touchPos.y = Gdx.input.getY();
    }

    private void handleInput(Vector3 touchPos)
    {
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

    @Override
    public void prepareForSwitchAway()
    {

    }
}
