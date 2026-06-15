package com.memento.commands;

/**
 * Classe Comando base.
 */
public interface Command {

    String getName();

    void execute();
}
