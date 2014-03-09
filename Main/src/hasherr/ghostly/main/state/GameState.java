package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import hasherr.ghostly.main.entity.Ghost;
import hasherr.ghostly.main.entity.Wall;
import hasherr.ghostly.main.entity.map.MapGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public class GameState extends State
{
    StateManager managerToRespondTo;
    boolean isReadyForSwitchAway;
    Ghost playerGhost;
    BitmapFont scoreFont;
    MapGenerator mapGenerator;
    Texture background;
    int score;

    float lastPosition;

    public GameState(StateManager managerToRespondTo)
    {
        this.managerToRespondTo = managerToRespondTo;
        isReadyForSwitchAway = false;

        // Create game assets and objects.
        playerGhost = new Ghost(0f, 0f, 64f, 64f, new Texture(Gdx.files.internal("sprites/ghost_sheet.png")));
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score_font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("fonts/score_font_0.png"))));
        mapGenerator = new MapGenerator();
        background = new Texture(Gdx.files.internal("sprites/background.png"));
        score = 0;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(background, playerGhost.pos.x - 125f, 0f);
        renderWalls(batch);
        renderScore(batch);
    }

    private void renderWalls(SpriteBatch batch)
    {
        for (int i = 0; i < Wall.allWalls.size(); i++) // Render all existing walls.
        {
            Wall wall = Wall.allWalls.get(i);
            wall.render(batch);
        }
    }

    private void renderScore(SpriteBatch batch)
    {
        int scoreToShow = score / 2;
        scoreFont.setScale(4f, 4f);
        scoreFont.draw(batch, "" + scoreToShow, (float) Math.floor(playerGhost.pos.x + 100f), 600f);
        playerGhost.render(batch);
    }

    @Override
    public void update()
    {
        updateAllExistingWalls();
        playerGhost.update();
        checkForPlayerDeath();
        updatePlayerScore();
    }

    private void updateAllExistingWalls()
    {
        for (int i = 0; i < Wall.allWalls.size(); i++) // Update all existing walls.
        {
            // Update the wall.
            Wall wall = Wall.allWalls.get(i);
            wall.update();
        }
    }

    public void checkForPlayerDeath()
    {
        for (Wall wall : Wall.allWalls)
        {
            if (playerGhost.boundingBox.overlaps(wall.boundingBox))
            {
                lastPosition = playerGhost.pos.x;
                mapGenerator.stopGeneration();
                managerToRespondTo.switchToDeathState();
            }
        }
    }

    private void updatePlayerScore()
    {
        // Check to see if the player cleared the wall. If he did, add 1 to his score.
        for (int i=0; i<Wall.wallsNotPassed.size(); i++)
        {
            Wall wallToPass = Wall.wallsNotPassed.get(i);
            if (playerGhost.pos.x + playerGhost.width > wallToPass.pos.x + wallToPass.width)
            {
                score++;
                Wall.wallsNotPassed.remove(i);
            }
        }
    }

    public int getScore()
    {
        return score / 2;
    }

    public float getSetXPosition()
    {
        return playerGhost.pos.x;
    }
}
