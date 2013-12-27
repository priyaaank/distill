package com.barefoot.distill.constraints;

import java.util.*;

public class ConstraintRegistry {

  private Map<Integer, IOutgoingCallConstraints> constaintMap;
  private static ConstraintRegistry singleInstance = new ConstraintRegistry();

  private ConstraintRegistry() {
    this.constaintMap = new HashMap<Integer, IOutgoingCallConstraints>();
  }

  public static ConstraintRegistry Instance() {
    return singleInstance;
  }

  public void RegisterConstraints(IOutgoingCallConstraints...constraints) {
    if(constraints == null || constraints.length == 0) return;
    for (IOutgoingCallConstraints constraint : constraints) {
      constaintMap.put(constraint.uniqueId(), constraint);
    }
  }

  public IOutgoingCallConstraints constraintForId(Integer constraintId) {
    return constaintMap.get(constraintId);
  }

  public List<IOutgoingCallConstraints> defaultSetOfConstraints() {
    List<IOutgoingCallConstraints> defaultConstraints = new ArrayList<IOutgoingCallConstraints>();
    Set<Integer> ids = constaintMap.keySet();
    for(Integer constraintId : ids) {
      if(constaintMap.get(constraintId).isApplicableByDefault()) defaultConstraints.add(constaintMap.get(constraintId));
    }

    return defaultConstraints;
  }
}
