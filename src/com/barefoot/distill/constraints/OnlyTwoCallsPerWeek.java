package com.barefoot.distill.constraints;

import android.content.Context;
import com.barefoot.distill.CallHistoryFinder;
import com.barefoot.distill.OutgoingCallRecord;
import com.barefoot.distill.PhoneNumber;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OnlyTwoCallsPerWeek implements IOutgoingCallConstraints {

  private Context context;
  private CallHistoryFinder historyFinder;
  private PhoneNumber phoneNumber;
  public static final Integer ID = new Integer(2);

  public OnlyTwoCallsPerWeek(Context context, PhoneNumber phoneNumber) {
    this.context = context;
    this.phoneNumber = phoneNumber;
    this.historyFinder = new CallHistoryFinder(context);
  }

  @Override
  public String description() {
    return "Only two successful outgoing calls per week";
  }

  @Override
  public boolean shouldDropCall() {
    List<OutgoingCallRecord> outgoingCallRecords = historyFinder.lookupHistoryForNumberSince(phoneNumber, dateSevenDaysAgo());
    return (outgoingCallRecords != null && outgoingCallRecords.size() >= 2);
  }

  private Date dateSevenDaysAgo() {
    Calendar calendarDate = Calendar.getInstance();
    calendarDate.add(Calendar.DAY_OF_MONTH, -7);
    return calendarDate.getTime();
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
