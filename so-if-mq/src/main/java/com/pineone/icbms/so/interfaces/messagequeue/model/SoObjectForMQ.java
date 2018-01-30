package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class SoObjectForMQ {
	ContextModelForMQ context;
	OrchestrationServiceForMQ os;
	CompositeVirtualObjectForMQ gcvo;
	CompositeVirtualObjectForMQ cvo;
	VirtualObjectForMQ vo;
	DeviceControlForMQ dc;
}
