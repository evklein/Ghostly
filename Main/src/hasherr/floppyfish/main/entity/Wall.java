package hasherr.floppyfish.main.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public class Wall extends Entity
{
    public static List<Wall> allWalls = new ArrayList<Wall>();
    boolean isEligibleForGarbageCollection;

    public Wall(float x, float y, float width, float height, Texture sprite)
    {

        pos = new Vector2(x, y);
        boundingBox = new Rectangle(x, y, width, height);
        texture = sprite;

        this.width = width;
             this.height = height;

        allWalls.add(this);
        isEligibleForGarbageCollection = false;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, pos.x, pos.y, width, height);
    }

    @Override
    public void update()
    {
        updateBoundingBox();

        if (isEligibleForGarbageCollection)
        {
            Wall.allWalls.remove(this);
        }
    }



    public void setEligibleForGarbageCollection()
    {
        isEligibleForGarbageCollection = true;
    }
}
