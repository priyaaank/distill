package com.barefoot.distill.constraints;

import com.barefoot.distill.PhoneNumber;

import java.util.*;

public class NumberRepository {

  private List<String> superNumbers = Arrays.asList(SuperNumbers.ANISH, SuperNumbers.ANJU,
                                                    SuperNumbers.PRIYANK, SuperNumbers.RADO, SuperNumbers.SANSKAR,
                                                    SuperNumbers.YOJANA_ONE, SuperNumbers.YOJANA_TWO);
  private Map<String, List<Integer>> numberConstraintMapping = new HashMap<String, List<Integer>>();
  private ConstraintRegistry registry;

  public NumberRepository(ConstraintRegistry registry) {
    numberConstraintMapping.put("111222", Arrays.asList(OnlyCallInGodlyHours.ID, OnlyTwoCallsPerWeek.ID));
    this.registry = registry;
  }

  public boolean isSuperNumber(PhoneNumber number) {
    return superNumbers.contains(number.phoneNumberInRawFormat());
  }

  public List<IOutgoingCallConstraints> constraintsForNumber(PhoneNumber phoneNumber) {
    List<Integer> constraintIdsForNumber = numberConstraintMapping.get(phoneNumber.phoneNumberInRawFormat());
    if(constraintIdsForNumber == null || constraintIdsForNumber.size() == 0) return registry.defaultSetOfConstraints();

    List<IOutgoingCallConstraints> constraints = new ArrayList<IOutgoingCallConstraints>();
    for(Integer constraintId : constraintIdsForNumber) {
      constraints.add(registry.constraintForId(constraintId));
    }
    return constraints;
  }

}
