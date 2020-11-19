package ru.job4j.io;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
       if (args.length != 3) {
           throw new IllegalArgumentException("There are 3 parameter's in the program");
       }
        return true;
    }

    public String directory() {
        valid();
        return args[0].split("=")[1];
    }

    public String exclude() {
        valid();
        return args[1].split("=")[1];
    }

    public String output() {
        valid();
        return args[2].split("=")[1];
    }
}
