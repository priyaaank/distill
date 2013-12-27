package com.barefoot.distill;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallHistoryFinder {

  private Context context;

  public CallHistoryFinder(Context context) {
    this.context = context;
  }

  public List<OutgoingCallRecord> lookupHistoryForNumberSince(PhoneNumber phoneNumber, Date date) {
    return callLogs(phoneNumber, date);
  }

  private List<OutgoingCallRecord> callLogs(PhoneNumber phoneNumber, Date sinceDate) {
    List<OutgoingCallRecord> outgoingCallsToNumber = new ArrayList<OutgoingCallRecord>();
    String columns[]=new String[] { CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE };
    String selector = CallLog.Calls.NUMBER + " = ? AND "+ CallLog.Calls.TYPE + " = ? AND " + CallLog.Calls.DATE +" > ? ";
    List<String> selectorArgs = new ArrayList<String>();
    selectorArgs.add(phoneNumber.phoneNumberInRawFormat());
    selectorArgs.add(Integer.toString(CallLog.Calls.OUTGOING_TYPE));
    selectorArgs.add(String.valueOf(sinceDate.getTime()));

    Cursor cursor = context.getContentResolver().query(Uri.parse("content://call_log/calls"), columns, selector, selectorArgs.toArray(new String[selectorArgs.size()]), "Calls._ID DESC");
    while (cursor.moveToNext()) {
      Date dialedDate = new Date(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)));
      String number = cursor.getString(1);
      outgoingCallsToNumber.add(new OutgoingCallRecord(number, dialedDate));
    }

    return outgoingCallsToNumber;
  }

}
