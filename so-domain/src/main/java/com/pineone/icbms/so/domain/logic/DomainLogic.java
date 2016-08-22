package com.pineone.icbms.so.domain.logic;

import com.pineone.icbms.so.domain.entity.Domain;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */

public interface DomainLogic {
    List<Domain> retrieveDomainList();
}
