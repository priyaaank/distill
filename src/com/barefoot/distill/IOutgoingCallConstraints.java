package com.barefoot.distill;

public interface IOutgoingCallConstraints {

  public String description();
  public boolean shouldDropCall();

}
