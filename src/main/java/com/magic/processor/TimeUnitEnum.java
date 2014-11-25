package com.magic.processor;

public enum TimeUnitEnum {
	DAY(1), WEEK(7), HALF_MONTH(14), MONTH(31);
	private int value;

	private TimeUnitEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
