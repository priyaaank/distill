package com.barefoot.distill;

import android.content.Context;
import android.widget.Toast;
import com.barefoot.distill.constraints.*;

import java.util.List;

public class OutgoingCallManager {

  private PhoneNumber currentOutgoingNumber;
  private Context context;
  private NumberRepository numberRepository;
  private ConstraintRegistry registry;

  public OutgoingCallManager(Context context, String outgoingNumber) {
    this.context = context;
    this.currentOutgoingNumber = new PhoneNumber(outgoingNumber);
    registry = ConstraintRegistry.Instance();
    registry.RegisterConstraints(new OnlyTwoCallsPerWeek(context, this.currentOutgoingNumber));
    this.numberRepository = new NumberRepository(registry);
  }

  public void process(OutgoingCallListener outgoingCallListener) {
    if(numberRepository.isSuperNumber(currentOutgoingNumber))
      allowCallToGoThrough(outgoingCallListener);
    else
      blockCallIfConstraintsApplicable(outgoingCallListener);
  }

  private void allowCallToGoThrough(OutgoingCallListener outgoingCallListener) {
    outgoingCallListener.setNumberToBeDialled(currentOutgoingNumber);
  }

  private void blockCallIfConstraintsApplicable(OutgoingCallListener outgoingCallListener) {
    List<IOutgoingCallConstraints> constraints = numberRepository.constraintsForNumber(currentOutgoingNumber);
    if(constraints != null  && constraints.size() > 0) {
      if(anyConstraintApplicable(constraints)) outgoingCallListener.setNumberToBeDialled(null);
    }
    Toast.makeText(context, "This call is going to be blocked for number " + currentOutgoingNumber, Toast.LENGTH_LONG).show();
  }

  private boolean anyConstraintApplicable(List<IOutgoingCallConstraints> constraints) {
    for(IOutgoingCallConstraints constraint : constraints) {
      if(constraint.shouldDropCall()) {
        return true;
      }
    }
    return false;
  }
}
