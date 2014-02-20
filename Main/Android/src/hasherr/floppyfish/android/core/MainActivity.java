package hasherr.floppyfish.android.core;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import hasherr.ghostly.main.core.Game;

public class MainActivity extends AndroidApplication
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useGL20 = true;
        configuration.useAccelerometer = false;
        configuration.useCompass = false;

        initialize(new Game(), configuration);

    }
}
