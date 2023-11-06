package com.tales.talesapi.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BearerTokenWrapper {
	private String token;
}
