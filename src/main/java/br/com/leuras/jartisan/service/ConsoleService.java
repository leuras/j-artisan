package br.com.leuras.jartisan.service;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

@Service
public class ConsoleService {
	
	private final PrintStream out = System.out;
	
	public void write (final String msg, final String... args) {
		this.out.printf(msg, (Object[]) args);
		this.out.println();
	}
}
