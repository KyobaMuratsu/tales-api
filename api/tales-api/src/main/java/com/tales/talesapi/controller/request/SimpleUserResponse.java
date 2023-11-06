package com.tales.talesapi.controller.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SimpleUserResponse {
	private String nome;
	private String email;
	private String picUrl;
}
