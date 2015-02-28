package ru.ildev.libgdx.examples.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import ru.ildev.libgdx.examples.utils.GdxExample;
import ru.ildev.libgdx.examples.utils.GdxExamples;

public class GdxExampleActivity extends AndroidApplication {

	@Override
    protected void onCreate (Bundle bundle) {
        super.onCreate(bundle);

        // obtain the test info
        Bundle extras = getIntent().getExtras();
        String testName = (String) extras.get("example");
        GdxExample test = GdxExamples.newTest(testName);

        // and run the application...
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = true;
        config.useWakelock = true;
        this.initialize(test, config);
    }

}
