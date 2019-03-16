package com.leanix.simple.restservice.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestRepr {

	private long id;
	private String content;

	
	public TestRepr() {
		// Jackson deserialization
	}

	public TestRepr(long id, String content) {
		this.id = id;
		this.content = content;
	}

    @JsonProperty
	public long getId() {
		return id;
	}

    @JsonProperty
	public String getContent() {
		return content;
	}
}
