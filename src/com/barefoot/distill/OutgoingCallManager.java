package com.barefoot.distill;

import android.content.Context;
import android.widget.Toast;

public class OutgoingCallManager {

  private String currentOutgoingNumber;
  private Context context;

  public OutgoingCallManager(Context context, String outgoingNumber) {
    this.context = context;
    this.currentOutgoingNumber = outgoingNumber;
  }

  public void process() {
    Toast.makeText(context, "This call is going to be blocked for number " + currentOutgoingNumber, Toast.LENGTH_LONG).show();
  }
}
