package com.cantillana.Lienzo;

/*
Validador de color

Font: https://examples.javacodegeeks.com/core-java/util/regex/matcher/how-to-validate-hex-color-code-with-regular-expression/

*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColorValidator {

	


	// Son 6 numeros en hexadecimal. Minúsculas o mayúsculas
	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        private static Pattern pattern=Pattern.compile(HEX_PATTERN);
	private static Matcher matcher;
                    

	// Comprueba si la cadena es válida
	public static boolean validate(final String hexColorCode) {

		matcher = pattern.matcher(hexColorCode);
		return matcher.matches();

	}
}