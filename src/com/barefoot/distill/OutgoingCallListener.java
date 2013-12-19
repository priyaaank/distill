package com.barefoot.distill;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OutgoingCallListener extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    String outgoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
    new OutgoingCallManager(context, outgoingNumber).process();
  }

}