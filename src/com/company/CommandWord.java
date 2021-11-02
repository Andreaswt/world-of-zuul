package com.company;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), STATS("stats"), FIGHT("Fight"), FLEE("Flee"), GIVE("Give"), MERGE("Merge"), EXILE("Exile");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
