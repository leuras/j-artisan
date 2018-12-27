package br.com.leuras.jartisan.prompt;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class JArtisanDefaultPromptProvider implements PromptProvider {
	
	private static final String JARTISAN_PROMPT = "j-artisan:%s$ ";
	
	@Override
	public AttributedString getPrompt() {
		
		final String prompt = String.format(JARTISAN_PROMPT, System.getProperty("user.dir"));
		
		return new AttributedString(prompt);
	}

}
