package hasherr.ghostly.main.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public abstract class Entity
{
    public Vector2 pos;
    public Rectangle boundingBox;
    public float width, height;
    Texture texture;


    public abstract void render(SpriteBatch batch);
    public abstract void update();

    protected void updateBoundingBox()
    {
        boundingBox.x = pos.x;
        boundingBox.y = pos.y;
        boundingBox.width = width;
        boundingBox.height = height;
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
