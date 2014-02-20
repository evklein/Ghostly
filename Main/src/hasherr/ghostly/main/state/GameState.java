package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hasherr.ghostly.main.entity.Ghost;
import hasherr.ghostly.main.entity.Wall;
import hasherr.ghostly.main.entity.map.MapGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public class GameState implements State
{
    Ghost playerGhost;
    MapGenerator mapGenerator;
    Texture background;

    public GameState()
    {
        playerGhost = new Ghost(0f, 0f, 64f, 64f, new Texture(Gdx.files.internal("sprites/ghost.png")));
        mapGenerator = new MapGenerator();
        background = new Texture(Gdx.files.internal("sprites/background.png"));
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(background, playerGhost.pos.x - 125f, 0f);
        for (int i = 0; i < Wall.allWalls.size(); i++) // Render all existing walls.
        {
            Wall wall = Wall.allWalls.get(i);
            wall.render(batch);
        }
        playerGhost.render(batch);
    }

    @Override
    public void update()
    {
        for (int i = 0; i < Wall.allWalls.size(); i++) // Update all existing walls.
        {
            // Update the wall.
            Wall wall = Wall.allWalls.get(i);
            wall.update();

            // Check to see if the player has made it past a wall.
            updatePlayerScore(wall);
        }
        playerGhost.update();
    }

    private void updatePlayerScore(Wall wallToPass)
    {
        // Check to see if the player cleared the wall. If he did, add 1 to his score.
        if (playerGhost.pos.x + playerGhost.width > wallToPass.pos.x + wallToPass.width)
        {

            playerGhost.updateScore();
        }
    }
}
