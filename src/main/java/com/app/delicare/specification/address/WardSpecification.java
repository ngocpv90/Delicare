package com.app.delicare.specification.address;

import com.app.delicare.entitys.address.Ward;
import com.app.delicare.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

public class WardSpecification extends BaseSpecification<Ward> {
    public static Specification<Ward> hasDistrictId(Long districtId, String condition) {
        return hasColumnCondition("district","id", districtId, condition);
    }
}
