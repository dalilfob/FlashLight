package edu.tqnguyen.mylight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	private boolean isOn;
	private Camera camera;
	private Button toggle;
	private Context ctx = MainActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toggle = (Button) findViewById(R.id.toggleBtn);

		toggle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String info = "/facebook-android-sdk-3.6.0/LICENSE.txt";
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				URL url = classLoader.getResource("C:/Users/Toan/Desktop/test");

				try {
					Log.i("license: ", url.toURI().toString());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!isOn) {
					startCamera();
					toggle.setText("Turn Off");
					isOn = !isOn;
				} else {
					stopCamera();
					toggle.setText("Turn On");
					isOn = !isOn;
				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStop() {
		super.onStop();

		if (camera != null) {
			camera.release();
		}
	}

	private void startCamera() {
		camera = Camera.open();
		Parameters p = camera.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(p);
		camera.startPreview();
	}

	private void stopCamera() {
		camera.release();
	}

}
