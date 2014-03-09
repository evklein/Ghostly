package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import hasherr.ghostly.main.ui.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/17/14
 */
public class MenuState extends State
{
    StateManager managerToRespondTo;
    OrthographicCamera cameraToUnproject;
    Texture background;
    Texture logo;
    Button playButton;
    Vector3 touchPos;

    public MenuState(StateManager managerToRespondTo, OrthographicCamera cameraToUnproject)
    {
        this.managerToRespondTo = managerToRespondTo;
        this.cameraToUnproject = cameraToUnproject;

        this.background = new Texture(Gdx.files.internal("sprites/background.png"));
        this.logo = new Texture(Gdx.files.internal("sprites/ui/ghostly_logo.png"));

        playButton = new Button(cameraToUnproject.position.x - 310f, 500f, 400f, 64f, new Texture(Gdx.files.internal("sprites/ui/play.png")), new Action()
        {
            @Override
            public boolean act(float delta)
            {
                play();
                return true;
            }
        });
        touchPos = new Vector3();
    }

    private void play()
    {
        managerToRespondTo.switchToGameState();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        float cameraPosition = cameraToUnproject.position.x - 310f;
        batch.draw(background, cameraToUnproject.position.x - 255f, 0f);
        batch.draw(playButton.getTexture(), cameraPosition + 100f, 500f);
        batch.draw(logo, cameraPosition, 600f);
    }

    @Override
    public void update()
    {
        handleMenuInput();
    }

    private void handleMenuInput()
    {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        cameraToUnproject.unproject(touchPos);
        if (Gdx.input.isTouched() && playButton.getBoundingRectangle().contains(touchPos.x, touchPos.y))
        {
            playButton.doAction();
        }
    }
}
