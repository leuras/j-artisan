package br.com.leuras.jartisan.command;

import org.springframework.stereotype.Component;

import br.com.leuras.jartisan.command.core.AbstractjArtisanCommand;
import br.com.leuras.jartisan.command.core.jArtisanCommand;

@Component
public class JArtisanMakeServiceCommand extends AbstractjArtisanCommand implements jArtisanCommand {
	
	public JArtisanMakeServiceCommand() {
		super();
	}
	
	@Override
	public void run() {
		
	}

}
