package com.barefoot.distill;

import android.content.Context;

public class OnlyTwoCallsPerWeek implements IOutgoingCallConstraints {

  private Context context;

  public OnlyTwoCallsPerWeek(Context context) {
    this.context = context;
  }

  @Override
  public String description() {
    return "Only two successful outgoing calls per week";
  }

  @Override
  public boolean shouldDropCall() {
    return false;
  }
}
