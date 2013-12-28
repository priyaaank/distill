package com.barefoot.distill;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Distill extends Activity {

  private static final String APP_ENABLED = "distill_enabled";
  private SharedPreferences preferences;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    preferences = this.getPreferences(Context.MODE_PRIVATE);
    updateButtonStateBasedOnStoredData();
    attachClickListenerToButton();
  }

  private void attachClickListenerToButton() {
    Button configButton = (Button) this.findViewById(R.id.distill_config);
    configButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        preferences.edit().putBoolean(APP_ENABLED, !isConfigEnabled()).commit();
        updateButtonStateBasedOnStoredData();
      }
    });
  }

  private void updateButtonStateBasedOnStoredData() {
    boolean isAppEnabled = isConfigEnabled();
    Button appEnabledConfigButton = (Button) findViewById(R.id.distill_config);
    if(isAppEnabled) {
      appEnabledConfigButton.setText("Disable");
      appEnabledConfigButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.enabled_button));
    } else {
      appEnabledConfigButton.setText("Enable");
      appEnabledConfigButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.disabled_button));
    }
  }

  private boolean isConfigEnabled() {
    return preferences.getBoolean(APP_ENABLED, true);
  }

}
