package hasherr.ghostly.main.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public abstract class State
{
    public abstract void render(SpriteBatch batch);
    public abstract void update();
}

