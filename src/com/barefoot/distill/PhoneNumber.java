package com.barefoot.distill;

public class PhoneNumber {

  private String numberInRawFormat;

  public PhoneNumber(String number) {
    this.extractNumber(number);
  }

  public String phoneNumberInRawFormat() {
    return numberInRawFormat;
  }

  private void extractNumber(String number) {
    if(number == null) this.numberInRawFormat = null;
    this.numberInRawFormat = number.replace(" ", "");
  }

}
