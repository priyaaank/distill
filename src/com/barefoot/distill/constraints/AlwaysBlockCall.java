package com.barefoot.distill.constraints;

import android.content.Context;

public class AlwaysBlockCall implements IOutgoingCallConstraints {

  public static final Integer ID = new Integer(3);
  private Context context;

  public AlwaysBlockCall(Context context) {
    this.context = context;
  }

  @Override
  public String description() {
    return "Never allow a call to go out to this number";
  }

  @Override
  public boolean shouldDropCall() {
    return true;
  }

  @Override
  public boolean isApplicableByDefault() {
    return false;
  }

  @Override
  public Integer uniqueId() {
    return ID;
  }

}
