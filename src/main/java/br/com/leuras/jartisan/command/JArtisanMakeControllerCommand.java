package br.com.leuras.jartisan.command;

import org.springframework.stereotype.Component;

import br.com.leuras.jartisan.command.core.AbstractjArtisanCommand;
import br.com.leuras.jartisan.command.core.jArtisanCommand;

@Component
public class JArtisanMakeControllerCommand extends AbstractjArtisanCommand implements jArtisanCommand {
	
	public JArtisanMakeControllerCommand() {
		super();
	}
	
	@Override
	public void run() {
		
	}

}
