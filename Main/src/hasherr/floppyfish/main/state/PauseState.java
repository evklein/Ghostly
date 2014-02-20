package hasherr.floppyfish.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import hasherr.floppyfish.main.ui.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/17/14
 */
public class PauseState implements State
{
    Button replayButton;
    Button menuButton;

    public PauseState()
    {
        Action replayButtonAction = new Action()
        {
            @Override
            public boolean act(float delta)
            {

                return true;
            }
        };
        replayButton = new Button(100f, 100f, new Texture(Gdx.files.internal("sprites/ui/replay_button.png")), replayButtonAction);
    }

    @Override
    public void render(SpriteBatch batch)
    {

    }

    @Override
    public void update()
    {

    }
}
