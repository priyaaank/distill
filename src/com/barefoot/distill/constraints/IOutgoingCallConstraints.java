package com.barefoot.distill.constraints;

public interface IOutgoingCallConstraints {

  public String description();
  public boolean shouldDropCall();
  public boolean isApplicableByDefault();
  public Integer uniqueId();

}
