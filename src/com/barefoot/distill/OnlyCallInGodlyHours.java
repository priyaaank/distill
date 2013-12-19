package com.barefoot.distill;

import android.content.Context;

public class OnlyCallInGodlyHours implements IOutgoingCallConstraints {

  private Context context;

  public OnlyCallInGodlyHours(Context context) {
    this.context = context;
  }

  @Override
  public String description() {
    return "Allow calls only between 10am to 8pm";
  }

  @Override
  public boolean shouldDropCall() {

    return false;
  }
}
