package com.barefoot.distill.constraints;

import com.barefoot.distill.PhoneNumber;

import java.util.*;

public class NumberRepository {

  public static List<String> superNumbers = Arrays.asList(SuperNumbers.ANISH_ONE, SuperNumbers.ANISH_TWO, SuperNumbers.ANISH_THREE,
                                                    SuperNumbers.ANJU_ONE, SuperNumbers.ANJU_TWO, SuperNumbers.ANJU_THREE,
                                                    SuperNumbers.HOME_ONE, SuperNumbers.HOME_TWO,
                                                    SuperNumbers.PRIYANK_ONE, SuperNumbers.PRIYANK_TWO, SuperNumbers.PRIYANK_THREE,
                                                    SuperNumbers.RADO_ONE, SuperNumbers.RADO_TWO, SuperNumbers.RADO_THREE,
                                                    SuperNumbers.SANSKAR_ONE, SuperNumbers.SANSKAR_TWO, SuperNumbers.SANSKAR_THREE,
                                                    SuperNumbers.YOJANA_ONE, SuperNumbers.YOJANA_TWO, SuperNumbers.YOJANA_THREE,
                                                    SuperNumbers.YOJANA_FOUR, SuperNumbers.YOJANA_FIVE, SuperNumbers.YOJANA_SIX);
  private Map<String, List<Integer>> numberConstraintMapping = new HashMap<String, List<Integer>>();
  private ConstraintRegistry registry;

  public NumberRepository(ConstraintRegistry registry) {
    numberConstraintMapping.put("111222", Arrays.asList(AlwaysBlockCall.ID));
    this.registry = registry;
  }

  public boolean isSuperNumber(PhoneNumber dialledNumber) {
    return superNumbers.contains(dialledNumber.phoneNumberInRawFormat());
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
