package hasherr.ghostly.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    TextureRegion[] animationFrames;
    TextureRegion currentFrame;
    Animation animation;
    float stateTime;
    boolean canJump;
    float score;

    public Ghost(float x, float y, float width, float height, Texture sprite)
    {
        pos = new Vector2(x, y);
        boundingBox = new Rectangle(x, y, width, height);

        // Animation variables.
        setUpAnimationFrames(sprite);

        this.width = width;
        this.height = height;

        velocity = new Vector2(175f, 0f);
        gravity = -21f;

        canJump = true;
        score = 0f;
    }

    private void setUpAnimationFrames(Texture sprite)
    {
        // Split the texture into multiple pieces and distribute them into an array for animation use.
        TextureRegion[][] region = TextureRegion.split(sprite, sprite.getWidth() / 2, sprite.getHeight());
        animationFrames = new TextureRegion[2];
        animationFrames[0] = region[0][0];
        animationFrames[1] = region[0][1];

        // Create the new animation and give it a time frame of animating ever 1/4 seconds.
        animation = new Animation(0.25f, animationFrames);
        stateTime = 0f;
        currentFrame = animation.getKeyFrame(stateTime, true);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(currentFrame, pos.x, pos.y);
    }

    @Override
    public void update()
    {
        currentFrame = animation.getKeyFrame(stateTime, true);
        stateTime += Gdx.graphics.getDeltaTime();

        updateBoundingBox();
        handlePlayerInput();

        velocity.y += gravity; // Force y-axis gravity onto player at all times.
        pos.x += velocity.x * Gdx.graphics.getDeltaTime(); // Push the player forward at a constant rate.
        pos.y += velocity.y * Gdx.graphics.getDeltaTime();
        handleFloorAndCeilingCollision();
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

}
