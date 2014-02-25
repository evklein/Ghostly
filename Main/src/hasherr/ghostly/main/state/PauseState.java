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
public class PauseState extends State
{


    public PauseState(OrthographicCamera cameraToUnproject)
    {

    }

    @Override
    public void render(SpriteBatch batch)
    {

    }

    @Override
    public void update()
    {
        Gdx.app.log("Debug1", "PauseState is in use!!!");
    }

    @Override
    public void prepareForSwitchAway()
    {

    }
}
