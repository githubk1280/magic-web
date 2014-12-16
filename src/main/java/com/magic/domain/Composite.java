package com.magic.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Composite implements Serializable {
	private static final long serialVersionUID = -1929372386462196151L;
	private String name;
	private int[] data;

	public Composite() {
	}
}
