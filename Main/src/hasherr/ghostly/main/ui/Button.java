package hasherr.ghostly.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/19/14
 */
public class Button extends Sprite
{
    Action buttonAction;
    Vector2 pos;

    public Button(float x, float y, Texture texture, Action buttonAction)
    {
        pos = new Vector2(x, y);
        this.setTexture(texture);
        this.buttonAction = buttonAction;
    }

    public void doAction()
    {
        buttonAction.act(Gdx.graphics.getDeltaTime());
    }
}
