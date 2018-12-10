package br.com.leuras.jartisan.service;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

import br.com.leuras.jartisan.constant.AnsiColor;

@Service
public class ConsoleService {
	
	private final PrintStream out = System.out;
	
	public void write (final String msg, final String... args) {
		this.out.printf(msg, (Object[]) args);
		this.out.println();
	}

	public void printInGreen(final String msg, final String... args) {
		this.out.print(AnsiColor.ANSI_GREEN);
		this.write(msg, args);
		this.out.print(AnsiColor.ANSI_RESET);
	}

	public void printInYellow(final String msg, final String... args) {
		this.out.print(AnsiColor.ANSI_YELLOW);
		this.write(msg, args);
		this.out.print(AnsiColor.ANSI_RESET);
	}

	public void printInRed(final String msg, final String... args) {
		this.out.print(AnsiColor.ANSI_RED);
		this.write(msg, args);
		this.out.print(AnsiColor.ANSI_RESET);		
	}
}
