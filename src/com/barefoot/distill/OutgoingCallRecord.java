package com.barefoot.distill;

import java.util.Date;

public class OutgoingCallRecord {

  private String number;
  private Date callDate;

  public OutgoingCallRecord(String number, Date callDate) {
    this.number = number;
    this.callDate = callDate;
  }

  public String getNumber() {
    return number;
  }

  public Date getCallDate() {
    return callDate;
  }
}
