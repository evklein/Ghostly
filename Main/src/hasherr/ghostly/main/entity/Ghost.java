package hasherr.ghostly.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public class Ghost extends Entity
{
    Vector2 velocity;
    float gravity;
    boolean canJump;
    float score;

    public Ghost(float x, float y, float width, float height, Texture sprite)
    {
        pos = new Vector2(x, y);
        boundingBox = new Rectangle(x, y, width, height);
        texture = sprite;

        this.width = width;
        this.height = height;

        velocity = new Vector2(175f, 0f);
        gravity = -21f;

        canJump = true;
        score = 0f;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, pos.x, pos.y);
    }

    @Override
    public void update()
    {
        updateBoundingBox();
        handlePlayerInput();

        velocity.y += gravity; // Force y-axis gravity onto player at all times.
        pos.x += velocity.x * Gdx.graphics.getDeltaTime(); // Push the player forward at a constant rate.
        pos.y += velocity.y * Gdx.graphics.getDeltaTime();

        handleFloorAndCeilingCollision();
        handleWallCollision();
    }

    private void handlePlayerInput()
    {
        if (Gdx.input.isTouched() && canJump)
        {
            canJump = false;
            velocity.y = 400f;
        }
        else if (!Gdx.input.isTouched())
        {
            canJump = true;
        }
    }

    private void handleFloorAndCeilingCollision()
    {
        float collisionBuffer = 30f;
        if (pos.y <= 0f) // Floor collision.
        {
            pos.y = 0f;
            velocity.y = 0f;
        }

        if (pos.y >= 800f - height - collisionBuffer) // Ceiling collision
        {
            pos.y = 800f - height - collisionBuffer;
        }
    }

    private void handleWallCollision()
    {
        for (int i=0; i<Wall.allWalls.size(); i++)
        {
            Wall wall = Wall.allWalls.get(i);
            if (boundingBox.overlaps(wall.boundingBox))
            {
                gravity = -120f; // Sink the player.
                velocity.x = 0f;
            }
        }
    }

    public void reset()
    {
        score = 0;
    }

    public void updateScore()
    {
        score += 1f;
    }
}
