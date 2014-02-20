package hasherr.floppyfish.main.entity.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import hasherr.floppyfish.main.entity.Wall;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/11/14
 */
public class MapGenerator
{
    public static Timer wallSpawnTimer;
    Texture wallTexture;
    float wallPosition;
    Random wallMeasurementGenerator;

    public MapGenerator()
    {
        wallTexture = new Texture(Gdx.files.internal("sprites/wall.png"));
        wallPosition = 500f;
        wallMeasurementGenerator = new Random();

        Timer.Task wallSpawnTimerTask = new Timer.Task()
        {
            @Override
            public void run()
            {
                generateWall();
            }
        };
        wallSpawnTimer = new Timer();
        wallSpawnTimer.schedule(wallSpawnTimerTask, 1f, 1f); // Spawn a new wall every second.
        wallSpawnTimer.start();
    }

    // Uses a random number to assign the bottom wall's position.
    private float calculateBottomWallPosition(int minimumHeight, int maximumHeight)
    {
        float bottomWallHeight = (float) (minimumHeight + wallMeasurementGenerator.nextInt
                (maximumHeight - minimumHeight + 1));
        return bottomWallHeight;
    }

    public void generateWall()
    {
        float holeDistance = 170f;
        float gapDistance = 330f;
        float bottomWallPosition = calculateBottomWallPosition(-770, -300);

        wallPosition += gapDistance;

        // Instantiate the wall objects so that they go partially off the screen.
        new Wall(wallPosition, bottomWallPosition, 100f, 800f, wallTexture); // Bottom wall.
        new Wall(wallPosition, bottomWallPosition + 800 + holeDistance, 100f, 800f, wallTexture); // Top wall.
    }
}
