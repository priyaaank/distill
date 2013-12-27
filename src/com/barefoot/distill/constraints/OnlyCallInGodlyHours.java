package com.barefoot.distill.constraints;

import android.content.Context;

import java.util.Calendar;

public class OnlyCallInGodlyHours implements IOutgoingCallConstraints {

  private Context context;

  private static int GODLY_HOUR_START = 10;
  private static int GODLY_HOUR_END = 8;
  public static final Integer ID = new Integer(1);

  public OnlyCallInGodlyHours(Context context) {
    this.context = context;
  }

  @Override
  public String description() {
    return "Allow calls only between 10am to 8pm";
  }

  @Override
  public boolean shouldDropCall() {
    Calendar today = Calendar.getInstance();
    int currentHour = today.get(Calendar.HOUR_OF_DAY);
    return (GODLY_HOUR_START <= currentHour && currentHour <= GODLY_HOUR_END);
  }

  @Override
  public boolean isApplicableByDefault() {
    return true;
  }

  @Override
  public Integer uniqueId() {
    return ID;
  }

}
