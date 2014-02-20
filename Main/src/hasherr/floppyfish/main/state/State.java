package hasherr.floppyfish.main.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public interface State
{
    public void render(SpriteBatch batch);
    public void update();
}

